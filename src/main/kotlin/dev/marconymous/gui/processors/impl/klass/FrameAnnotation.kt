package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.Frame
import dev.marconymous.gui.processors.ClassProcessor
import kotlin.reflect.KClass

class FrameAnnotation<T : Any> : ClassProcessor<T>(Frame::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        val cGui = validate(klass) as? Frame ?: return

        frame.setSize(cGui.width, cGui.height)
        frame.title = cGui.title
    }

}
