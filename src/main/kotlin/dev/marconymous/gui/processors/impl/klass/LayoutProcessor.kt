package dev.marconymous.gui.processors.impl.klass

import dev.marconymous.gui.GeneratedFrame
import dev.marconymous.gui.annotations.BorderPane
import dev.marconymous.gui.annotations.FlowPane
import dev.marconymous.gui.annotations.GridPane
import dev.marconymous.gui.processors.ClassProcessor
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import kotlin.reflect.KClass

class LayoutProcessor<T : Any> : ClassProcessor<T>(GridPane::class, FlowPane::class, BorderPane::class) {
    override fun process(klass: KClass<out T>, obj: T, frame: GeneratedFrame<T>) {
        val layout = when(val foundLayout = validateAllGetFirst(klass)) {
            GridPane::class -> {
                val gp = foundLayout as GridPane
                GridLayout(gp.columns, gp.rows)
            }
            FlowPane::class -> FlowLayout()
            BorderPane::class -> BorderLayout()
            else -> return
        }

        frame.layout = layout
    }
}