package dev.marconymous.gui.annotations

import dev.marconymous.gui.enums.EventType

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Set a name for a component
 *
 * @property value the distinct name of the component
 * @constructor Create empty Distinct
 */
annotation class Distinct(val value: String)

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Function to use as an event handler for a component
 *
 * @property setOn the name of the component to set the event handler on
 * @constructor Create empty Event handler
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventHandler(val setOn: String, val eventType: EventType)