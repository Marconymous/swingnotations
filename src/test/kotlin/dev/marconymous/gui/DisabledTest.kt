package dev.marconymous.gui

import dev.marconymous.gui.annotations.*
import dev.marconymous.gui.annotations.CloseOp.EXIT
import dev.marconymous.gui.enums.EventType.BUTTON_ONCLICK
import javax.swing.JButton

@Frame(title = "Enabled?", width = 300, height = 300)
@Visible(true)
@CloseOperation(EXIT)
class DisabledTest {
    @Component
    @IsEnabled(false)
    val btn = JButton("Should be Disabled")

    @EventHandler("btn", BUTTON_ONCLICK) fun onClick() {
        println("Clicked")
    }
}

fun main() {
    val dt = SwingNotations.generate(DisabledTest::class)
}