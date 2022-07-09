package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.Requires
import dev.marconymous.gui.processors.ClassProcessor
import dev.marconymous.gui.processors.impl.util.RequiresCheck
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMembers

class RequiredOnClassProcessor<T : Any> : ClassProcessor<T>(Requires::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        RequiresCheck(klass)

        (klass.declaredMembers + klass.declaredFunctions).forEach {
            RequiresCheck(it)
        }
    }
}
