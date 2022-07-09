package dev.marconymous.gui

import dev.marconymous.gui.processors.AnnotationProcessor
import kotlin.reflect.KClass

/**
 * Public Object for creating a new [AnnotationProcessor] & [GeneratedFrame]
 */
object SwingNotations {
    /**
     * Generates a new [GeneratedFrame] from the given Object[T]
     *
     * @param T The Type to generate the [GeneratedFrame] from
     * @param obj The Object to generate the [GeneratedFrame] from
     * @return The [GeneratedFrame]
     */
    fun <T : Any> generate(obj: T): GeneratedFrame<T> = AnnotationProcessor(obj).process()

    /**
     * Generates a new [GeneratedFrame] from the given KClass<[T]> and the provided arguments [args]
     *
     * @param T The Type to generate the [GeneratedFrame] from
     * @param klass The KClass to generate the [GeneratedFrame] from
     * @param args The Arguments to use when creating the instance of [klass]
     * @return The [GeneratedFrame]
     */
    fun <T : Any> generate(klass: KClass<out T>, vararg args: Any): GeneratedFrame<T> {
        val constructor = klass.constructors
            .find {
                it.parameters.size == args.size
                        && it.parameters.map { p -> p.type.toString() }
                    .zip(args.map { c -> c::class })
                    .all { p -> p.first == p.second.qualifiedName }
            }
            ?: throw IllegalArgumentException("No constructor found for ${klass.qualifiedName} with [${args.size}] arguments of types ${args.map { it::class.qualifiedName }}")

        val obj = constructor.call(*args)

        return generate(obj)
    }
}