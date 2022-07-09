package dev.marconymous.gui.annotations

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.PROPERTY

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Marks a JComponent as Enabled inside a [Frame] class
 *
 * @property [value] The value of the enabled property
 */
@MustBeDocumented
@Target(FIELD, PROPERTY)
@Retention(RUNTIME)
annotation class IsEnabled(val value: Boolean = true)



