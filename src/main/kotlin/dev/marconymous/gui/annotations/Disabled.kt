package dev.marconymous.gui.annotations

/**
 * Marks a JComponent as Disabled
 *
 * @property [value] The value of the disabled property
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Disabled(val value: Boolean = true)



