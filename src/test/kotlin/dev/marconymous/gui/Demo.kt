package dev.marconymous.gui

import dev.marconymous.gui.annotations.*
import dev.marconymous.gui.enums.EventType.BUTTON_ONCLICK
import java.awt.BorderLayout.*
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.WindowConstants.EXIT_ON_CLOSE

@Frame(
    title = "Test",
    width = 500,
    height = 400,
)
@CloseOperation(EXIT_ON_CLOSE)
@BorderPane
class Gui {
    @Component(NORTH)
    private val north = JLabel("North")

    @Component(CENTER)
    private val center = JTextField("Center")

    @Component(SOUTH)
    @IsEnabled(false)
    @Distinct("south_button")
    private val south = JButton("South")

    @EventHandler("south_button", BUTTON_ONCLICK)
    private fun onSouthClick() {
        println("South clicked")
    }
}

fun main() {
    val gui = SwingNotations.generate(Gui::class)
}