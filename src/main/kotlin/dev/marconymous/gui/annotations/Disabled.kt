package dev.marconymous.gui.annotations

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Marks a JComponent as Disabled inside a [Frame] class
 *
 * @property [value] The value of the disabled property
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Disabled(val value: Boolean = true)



