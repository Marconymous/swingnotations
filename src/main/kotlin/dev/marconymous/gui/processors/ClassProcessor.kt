package dev.marconymous.gui.processors

import dev.marconymous.gui.GeneratedFrame
import kotlin.reflect.KClass

abstract class ClassProcessor<T : Any>(val annotation: KClass<out Annotation>) {
    private val annotations: ArrayList<KClass<out Annotation>> = arrayListOf()
    constructor(vararg annotations: KClass<out Annotation>) : this(annotations.first()) {
        this.annotations.addAll(annotations)
    }

    fun validate(klass: KClass<out T>): Annotation? {
        if (annotations.isNotEmpty()) return validateAllGetFirst(klass)
        return klass.annotations.find { it.annotationClass == annotation }
    }

    fun validateAllGetFirst(klass: KClass<out T>): Annotation? {
        return klass.annotations.find {
            this.annotations.contains(it.annotationClass)
        }
    }

    abstract fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>)
}