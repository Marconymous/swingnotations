package dev.marconymous.gui.annotations

import kotlin.reflect.KClass

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Requires another Annotation to be present on the same Object.
 *
 * @property required
 * @constructor Create empty Requires
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class Requires(vararg val required: KClass<out Annotation>)
