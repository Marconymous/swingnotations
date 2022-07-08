package dev.marconymous.gui.processors

import dev.marconymous.gui.annotations.*
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent
import javax.swing.JFrame
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.isAccessible

class AnnotationProcessor<T : Any>(private val obj: T) {
    private val cls = obj::class
    private val cGui = checkForCGUI()
    private val frame = JFrame()

    fun process(): JFrame {
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
        val components = cls.declaredMembers.filter {
            println("${it.name}: \n Annotations: ${it.annotations.map { ac -> ac.annotationClass.simpleName }} \n Type: ${it.returnType} \n Visibility: ${it.visibility}")

            it.annotations.any { cmp -> cmp is Component }
        }
        components.forEach {
            val i2 = it as KProperty1<T, *>
            i2.isAccessible = true
            val component = i2.get(obj)
            if (component !is JComponent) throw IllegalArgumentException(
                "@Component annotation can only be used on JComponent: ${it.name}"
            )

            val componentAnnotations =
                it.annotations.find { i3 -> i3 is Component } as Component

            val disabledAnnotation = it.annotations.find { i3 -> i3 is Disabled }
            if (disabledAnnotation != null) {
                component.isEnabled = !(disabledAnnotation as Disabled).value
            }

            eventListenerAnnotation(i2, component)

            if (componentAnnotations.constraints.isNotBlank()) {
                frame.add(component, componentAnnotations.constraints)
                return@forEach
            }

            frame.add(component)
        }
    }

    private fun eventListenerAnnotation(property: KProperty1<T, *>, component: JComponent) {
        val eventHandler = cls.declaredFunctions.filter {
            it.findAnnotation<EventHandler>() != null
        }.find {
            val name = it.findAnnotation<EventHandler>()?.setOn ?: return@find false

            if (name == property.name || name == property.findAnnotation<Distinct>()?.value) {
                return@find true
            }

            false
        } ?: return

        val eventHandlerAnnotation = eventHandler.findAnnotation<EventHandler>() ?: return

        val eventType = eventHandlerAnnotation.eventType

        if (eventHandler.parameters.isEmpty() || eventHandler.parameters.size > 2 || (eventHandler.parameters.size == 2 && eventHandler.parameters[1].type.toString() != ActionEvent::class.qualifiedName)) {
            println("param: ${eventHandler.parameters[1].type} -> ${ActionEvent::class} -> ${eventHandler.parameters[1].type == ActionEvent::class}")
            throw IllegalArgumentException("EventHandler ${eventHandler.name} must have either 0 or 1 parameters (of type ${ActionEvent::class.qualifiedName})")
        }

        if (component::class == eventType.target || component::class.isSubclassOf(eventType.target)) {
            eventType.setter.call(component, ActionListener {
                if (eventHandler.parameters.size == 1) {
                    eventHandler.call(obj)
                    return@ActionListener
                }

                if (eventHandler.parameters.size == 2) {
                    eventHandler.call(obj, it)
                }
            })
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