package dev.marconymous.gui

import javax.swing.JComponent
import javax.swing.JFrame

/**
 * @author marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Used to create a window with a title and a content. Using [dev.marconymous.gui.processors.AnnotationProcessor]
 *
 * @param T The type of the provided Object
 * @property instance The instance of the provided Object
 * @constructor Create empty Generated frame
 */
class GeneratedFrame<T> internal constructor(val instance: T) : JFrame() {
    private val components: ArrayList<JComponent> = ArrayList()

    fun getComponent(name: String) = components.find { it.name == name }

    operator fun get(name: String) = getComponent(name)

    internal fun addComponent(component: JComponent) {
        components.add(component)
    }
}