package dev.marconymous.gui.processors.impl

import dev.marconymous.gui.annotations.Distinct
import dev.marconymous.gui.annotations.EventHandler
import dev.marconymous.gui.processors.ComponentProcessor
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JComponent
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf

/**
 * Processor for [EventHandler] annotation.
 *
 * @param T the type of the GUI
 * @property cls the class of the GUI
 * @property obj the object of the GUI
 */
class EventListenerProcessor<T : Any>(private val cls: KClass<out T>, private val obj: Any) : ComponentProcessor<T>(EventHandler::class) {
    /**
     * Will check if there is a [EventHandler] set on a function for the given [JComponent].
     *
     * @param property the property of the GUI
     * @param component the component of property of the GUI
     */
    override fun handle(property: KProperty1<T, *>, component: JComponent) {
        // Find the function that is annotated with EventHandler and has the same name as the property
        val eventHandler = cls.declaredFunctions.filter {
            it.findAnnotation<EventHandler>() != null
        }.find {
            val name = it.findAnnotation<EventHandler>()?.setOn ?: return@find false

            if (name == property.name || name == property.findAnnotation<Distinct>()?.value) {
                return@find true
            }

            false
        } ?: return

        val eventHandlerAnnotation = eventHandler.findAnnotation<EventHandler>() ?: return

        val eventType = eventHandlerAnnotation.eventType

        if (eventHandler.parameters.isEmpty() || eventHandler.parameters.size > 2 || (eventHandler.parameters.size == 2 && eventHandler.parameters[1].type.toString() != ActionEvent::class.qualifiedName)) {
            println("param: ${eventHandler.parameters[1].type} -> ${ActionEvent::class} -> ${eventHandler.parameters[1].type == ActionEvent::class}")
            throw IllegalArgumentException("EventHandler ${eventHandler.name} must have either 0 or 1 parameters (of type ${ActionEvent::class.qualifiedName})")
        }

        if (component::class == eventType.target || component::class.isSubclassOf(eventType.target)) {
            eventType.setter.call(component, ActionListener {
                if (eventHandler.parameters.size == 1) {
                    eventHandler.call(obj)
                    return@ActionListener
                }

                if (eventHandler.parameters.size == 2) {
                    eventHandler.call(obj, it)
                }
            })
        }
    }

}