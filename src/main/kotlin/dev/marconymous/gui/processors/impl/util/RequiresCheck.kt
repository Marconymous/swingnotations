package dev.marconymous.gui.processors.impl.util

import dev.marconymous.gui.annotations.Requires
import kotlin.reflect.KAnnotatedElement
import kotlin.reflect.full.findAnnotations

object RequiresCheck {
    operator fun invoke(element: KAnnotatedElement) {
        val allWithRequires = element.annotations.filter {
            it.annotationClass.annotations.find { an -> an is Requires } != null
        }

        allWithRequires.forEach {
            check(it.annotationClass, element)
        }
    }

    private fun check(annotationClass: KAnnotatedElement, obj: KAnnotatedElement) {
        val requires = annotationClass.annotations.find { it is Requires } as? Requires ?: return

        val required = requires.required

        if (required.isEmpty()) {
            throw IllegalArgumentException("No required Annotation found on $annotationClass please remove the @Requires annotation or add required Annotation")
        }

        required.all {
            val found = obj.findAnnotations(it)

            if (found.isEmpty()) {
                throw IllegalArgumentException("Required Annotation > $it not found on > $obj required by > $annotationClass")
            }

            true
        }
    }
}