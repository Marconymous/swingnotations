package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.CloseOperation
import dev.marconymous.gui.processors.ClassProcessor
import kotlin.reflect.KClass

class CloseOperationProcessor<T : Any> : ClassProcessor<T>(CloseOperation::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        val closeOperation = validate(klass) as? CloseOperation ?: return

        frame.defaultCloseOperation = closeOperation.value
    }
}