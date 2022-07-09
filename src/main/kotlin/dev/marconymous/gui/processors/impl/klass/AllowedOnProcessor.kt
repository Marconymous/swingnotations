package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.AllowedOn
import dev.marconymous.gui.processors.ClassProcessor
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.isSubclassOf

class AllowedOnProcessor<T : Any> : ClassProcessor<T>(AllowedOn::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        klass.declaredMembers.forEach {
            validateMember(it)
        }
    }

    private fun validateMember(member: KCallable<*>) {
        val annotationsWithAllowedOn = member.annotations.filter {
            it.annotationClass.annotations.any { a -> a is AllowedOn }
        }


        annotationsWithAllowedOn.forEach {
            val allowedOn = it.annotationClass.annotations.first { a -> a is AllowedOn } as AllowedOn

            if (allowedOn.allowed.isEmpty()) {
                throw IllegalArgumentException("@AllowedOn annotation on $it must have at least one allowed value")
            }

            val type = member.returnType.classifier as KClass<*>

            for (klass in allowedOn.allowed) {
                println("comparing $klass with $type")
                if (type.isSubclassOf(klass)) {
                    return
                }
            }

            throw IllegalArgumentException("$member is not a subclass of ${allowedOn.allowed.map { c -> c.simpleName }} required by $it")
        }
    }
}