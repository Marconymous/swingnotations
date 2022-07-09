package dev.marconymous.gui.annotations

import javax.swing.WindowConstants

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Annotation used to set the default close operation for a [Frame] annotated class.
 *
 * @property value the default close operation for the frame.
 * @see Frame
 * @see javax.swing.JFrame.setDefaultCloseOperation
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class CloseOperation(val value: Int)

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Close operation constants for the [CloseOperation] annotation.
 *
 * @constructor Create empty Close op
 * @see WindowConstants
 */
@Suppress("unused")
object CloseOp {
    const val NOTHING = WindowConstants.DO_NOTHING_ON_CLOSE
    const val HIDE = WindowConstants.HIDE_ON_CLOSE
    const val DISPOSE = WindowConstants.DISPOSE_ON_CLOSE
    const val EXIT = WindowConstants.EXIT_ON_CLOSE
}