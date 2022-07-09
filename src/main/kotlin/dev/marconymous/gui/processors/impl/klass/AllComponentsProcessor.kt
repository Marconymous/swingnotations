package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.Component
import dev.marconymous.gui.annotations.Distinct
import dev.marconymous.gui.processors.ClassProcessor
import dev.marconymous.gui.processors.impl.component.ComponentAnnotationProcessor
import dev.marconymous.gui.processors.impl.component.EventListenerProcessor
import dev.marconymous.gui.processors.impl.component.IsEnabledProcessor
import javax.swing.JComponent
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.jvm.isAccessible

class AllComponentsProcessor<T : Any> : ClassProcessor<T>(Component::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        val processors = arrayOf(
            IsEnabledProcessor(),
            EventListenerProcessor(klass, obj),

            // finaly add the component to the frame
            ComponentAnnotationProcessor(frame)
        )

        val components = klass.declaredMembers.filter {
            println("${it.name}: \n Annotations: [${it.annotations.size}] ${it.annotations.map { ac -> ac.annotationClass.simpleName }} \n Type: ${it.returnType} \n Visibility: ${it.visibility}")

            it.annotations.any { cmp -> cmp is Component }
        }
        components.forEach {
            @Suppress("UNCHECKED_CAST") // Has to be casted to KProperty1<T, *> because of the way the compiler works
            val i2 = it as KProperty1<T, *>
            i2.isAccessible = true
            val component = i2.get(obj)
            if (component !is JComponent) throw IllegalArgumentException(
                "@Component annotation can only be used on JComponent but is used on : ${it.returnType} ${it.name}"
            )

            processors.forEach { proc ->
                proc.handle(i2, component)
            }

            val distinctName = i2.annotations.firstOrNull { an -> an is Distinct }
                ?.let { an -> an as Distinct }
                ?.value ?: i2.name

            component.name = distinctName

            frame.addComponent(component)
        }

    }

}
