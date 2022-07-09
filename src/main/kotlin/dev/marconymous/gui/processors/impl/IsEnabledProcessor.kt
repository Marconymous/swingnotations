package dev.marconymous.gui.processors.impl

import dev.marconymous.gui.annotations.IsEnabled
import dev.marconymous.gui.processors.ComponentProcessor
import javax.swing.JComponent
import kotlin.reflect.KProperty1

/**
 * Processor for [IsEnabled] annotation.
 *
 * @param T the type of the GUI
 */
class IsEnabledProcessor<T : Any> : ComponentProcessor<T>(IsEnabled::class) {
    /**
     * Will disable the component according to the [IsEnabled.value] property.
     *
     * @param property the property to process
     * @param component the component to process
     */
    override fun handle(property: KProperty1<T, *>, component: JComponent) {
        val annotation = validate(property) ?: return
        val isEnabled = annotation as IsEnabled
        component.isEnabled = isEnabled.value
    }
}