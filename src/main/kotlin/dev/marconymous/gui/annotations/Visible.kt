package dev.marconymous.gui.annotations

import javax.swing.JComponent

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Marks JFrame as Visible if [value] is true, otherwise it is not visible
 *
 * @property value if true, the frame is visible, otherwise it is not visible
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Requires(Frame::class)
@AllowedOn([JComponent::class])
annotation class Visible(val value: Boolean = true)