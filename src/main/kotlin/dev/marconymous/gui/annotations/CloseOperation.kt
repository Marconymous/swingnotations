package dev.marconymous.gui.annotations

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