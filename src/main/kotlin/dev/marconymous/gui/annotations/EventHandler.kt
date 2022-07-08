package dev.marconymous.gui.annotations

import dev.marconymous.gui.enums.EventType

/**
 * Set a name for a component
 *
 * @property value
 * @constructor Create empty Distinct
 */
annotation class Distinct(val value: String)

/**
 * Function to use as an event handler for a component
 *
 * @property setOn the name of the component to set the event handler on
 * @constructor Create empty Event handler
 */
annotation class EventHandler(val setOn: String, val eventType: EventType)