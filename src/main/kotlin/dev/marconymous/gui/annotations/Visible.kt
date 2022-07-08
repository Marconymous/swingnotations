package dev.marconymous.gui.annotations

/**
 * Marks JFrame as Visible if [value] is true, otherwise it is not visible
 *
 * @property value if true, the frame is visible, otherwise it is not visible
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Visible(val value: Boolean = true)