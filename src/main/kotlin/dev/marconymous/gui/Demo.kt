package dev.marconymous.gui

import dev.marconymous.gui.annotations.*
import dev.marconymous.gui.enums.EventType.BUTTON_ONCLICK
import dev.marconymous.gui.processors.AnnotationProcessor
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
    @Component(NORTH) private val north = JLabel("North")
    @Component(CENTER) private val center = JTextField("Center")

    @Component(SOUTH)
    @Distinct("south_button")
    private val south = JButton("South")

    val processed = AnnotationProcessor(this)

    @EventHandler("south_button", BUTTON_ONCLICK) fun onSouthClick() {
        println("South clicked")
    }

    init {
        AnnotationProcessor(this).process()
    }
}

fun main() {
    val gui = Gui()
}