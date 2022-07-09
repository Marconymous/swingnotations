package dev.marconymous.gui.annotations

import kotlin.reflect.KClass

/**
 * Only allows a certain Annotation on members with a certain type.
 *
 * @property allowed The type of the member.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class AllowedOn(val allowed: Array<KClass<out Any>>)
