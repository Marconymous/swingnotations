package dev.marconymous.gui.processors.impl

import dev.marconymous.gui.annotations.Disabled
import dev.marconymous.gui.processors.ComponentProcessor
import javax.swing.JComponent
import kotlin.reflect.KProperty1

/**
 * Processor for [Disabled] annotation.
 *
 * @param T the type of the GUI
 */
class DisabledProcessor<T : Any> : ComponentProcessor<T>(Disabled::class) {
    /**
     * Will disable the component according to the [Disabled.value] property.
     *
     * @param property the property to process
     * @param component the component to process
     */
    override fun handle(property: KProperty1<T, *>, component: JComponent) {
        val annotation = validate(property) ?: return
        val disabled = annotation as Disabled
        component.isEnabled = !disabled.value
    }
}