package dev.marconymous.gui.processors.impl.component

import dev.marconymous.gui.annotations.Component
import dev.marconymous.gui.processors.ComponentProcessor
import javax.swing.JComponent
import javax.swing.JFrame
import kotlin.reflect.KProperty1

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Processor for [Component] annotation.
 *
 * @param T The type of the GUI
 * @property frame The frame to add the component to
 */
class ComponentAnnotationProcessor<T : Any>(private val frame: JFrame) : ComponentProcessor<T>(Component::class) {
    /**
     * Will add the Component to the [JFrame]
     * @param property The property of the GUI
     * @param component The component to add to the frame
     */
    override fun handle(property: KProperty1<T, *>, component: JComponent) {
        val componentAnnotations = (validate(property) ?: return) as Component
        if (componentAnnotations.constraints.isNotBlank()) {
            frame.add(component, componentAnnotations.constraints)
            return
        }

        frame.add(component)
    }


}
