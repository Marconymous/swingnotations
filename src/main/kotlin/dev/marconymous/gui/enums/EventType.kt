package dev.marconymous.gui.enums

import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JComponent
import kotlin.reflect.KFunction2
import kotlin.reflect.KClass

enum class EventType(val target: KClass<out JComponent>, val setter: KFunction2<JButton, ActionListener, Unit>) {
    BUTTON_ONCLICK(JButton::class, JButton::addActionListener)
}