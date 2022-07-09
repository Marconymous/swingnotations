package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.Visible
import dev.marconymous.gui.processors.ClassProcessor
import kotlin.reflect.KClass

class VisibleProcessor<T : Any> : ClassProcessor<T>(Visible::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        val visibleAnnotation = validate(klass) as? Visible ?: Visible(true)

        frame.isVisible = visibleAnnotation.value
    }

}
