package dev.marconymous.gui.processors

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.Frame
import dev.marconymous.gui.processors.impl.klass.*

/**
 * @author marconymous
 * @since SNAPSHOT-1.0.0
 *
 * @param T the type of provided Object
 * @property obj the provided object
 * @constructor internal constructor to prevent instantiation outside of [dev.marconymous.gui.SwingNotations.generate]
 */
class AnnotationProcessor<T : Any> internal constructor(private val obj: T) {
    private val cls = obj::class
    private val frame: GeneratedFrame<T> = GeneratedFrame(obj)

    /**
     * @return the generated frame
     */
    fun process(): GeneratedFrame<T> {
        checkFrameAnnotation()

        val processors = arrayOf(
            RequiredOnClassProcessor(),
            AllowedOnProcessor(),
            LayoutProcessor(),
            CloseOperationProcessor<T>(),
            AllComponentsProcessor(),
            FrameAnnotation(),
            VisibleProcessor()
        )

        for (processor in processors) {
            processor.process(cls, obj, frame)
        }

        return frame
    }

    private fun checkFrameAnnotation(): Frame {
        val frame = cls.annotations.find { it is Frame }
            ?: throw IllegalArgumentException("Class ${cls.simpleName} does not have a @CGui annotation")

        return frame as Frame
    }
}