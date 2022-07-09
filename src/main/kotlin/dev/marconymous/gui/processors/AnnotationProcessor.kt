package dev.marconymous.gui.processors

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.*
import dev.marconymous.gui.processors.impl.ComponentAnnotationProcessor
import dev.marconymous.gui.processors.impl.EventListenerProcessor
import dev.marconymous.gui.processors.impl.IsEnabledProcessor
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JComponent
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.jvm.isAccessible

/**
 * @author marconymous
 * @since SNAPSHOT-1.0.0
 *
 * @param T the type of provided Object
 * @property obj the provided object
 * @constructor internal constructor to prevent instantiation outside of [dev.marconymous.gui.SwingNotations.generate]
 */
class AnnotationProcessor<T : Any> internal constructor(private val obj: T) {
    private val cls = obj::class
    private val cGui = checkForCGUI()
    private val frame: GeneratedFrame<T> = GeneratedFrame(obj)

    /**
     * @return the generated frame
     */
    fun process(): GeneratedFrame<T> {
        // Set Correct Layout to Frame
        handleLayoutAnnotation()

        // Set Correct Close Operation to Frame
        handleCloseOperationAnnotation()

        // Get all Components and add them to the Frame
        handleComponentAnnotation()

        // Set Correct Title & Size to Frame
        handleCGuiAnnotation()

        // Set Correct Visibility to Frame
        handleVisibleAnnotation()
        return frame
    }

    private fun handleVisibleAnnotation() {
        val visible = cls.annotations.find { it is Visible }

        if (visible != null) {
            val v = visible as Visible
            frame.isVisible = v.value
        }

        frame.isVisible = true
    }

    private fun handleCloseOperationAnnotation() {
        val closeOpAnnot = cls.annotations.find { it is CloseOperation }
        if (closeOpAnnot != null) {
            val closeOp = closeOpAnnot as CloseOperation
            frame.defaultCloseOperation = closeOp.value
        }
    }

    private fun handleComponentAnnotation() {
        val processors = arrayOf(
            IsEnabledProcessor(),
            EventListenerProcessor(cls, obj),

            // finaly add the component to the frame
            ComponentAnnotationProcessor(frame)
        )

        val components = cls.declaredMembers.filter {
            println("${it.name}: \n Annotations: [${it.annotations.size}] ${it.annotations.map { ac -> ac.annotationClass.simpleName }} \n Type: ${it.returnType} \n Visibility: ${it.visibility}")

            it.annotations.any { cmp -> cmp is Component }
        }
        components.forEach {
            @Suppress("UNCHECKED_CAST") // Has to be casted to KProperty1<T, *> because of the way the compiler works
            val i2 = it as KProperty1<T, *>
            i2.isAccessible = true
            val component = i2.get(obj)
            if (component !is JComponent) throw IllegalArgumentException(
                "@Component annotation can only be used on JComponent: ${it.name}"
            )

            processors.forEach { proc ->
                proc.handle(i2, component)
            }

            val distinctName = i2.annotations.firstOrNull { an -> an is Distinct }
                ?.let { an -> an as Distinct }
                ?.value ?: i2.name

            component.name = distinctName

            frame.addComponent(component)
        }
    }

    private fun handleLayoutAnnotation() {
        val layoutAnnotation =
            cls.annotations.firstOrNull { it is BorderPane || it is GridPane || it is FlowPane }
                ?: return

        println("LayoutAnnotation: ${layoutAnnotation.annotationClass.simpleName}")
        when (layoutAnnotation) {
            is BorderPane -> frame.layout = BorderLayout()
            is GridPane -> frame.layout = GridLayout(
                layoutAnnotation.rows,
                layoutAnnotation.columns
            )
            is FlowPane -> frame.layout = FlowLayout()
        }
    }

    private fun checkForCGUI(): Frame {
        val frame = cls.annotations.find { it is Frame }
            ?: throw IllegalArgumentException("Class ${cls.simpleName} does not have a @CGui annotation")

        return frame as Frame
    }

    private fun handleCGuiAnnotation() {
        frame.setSize(cGui.width, cGui.height)
        frame.title = cGui.title
    }
}