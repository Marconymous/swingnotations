package dev.marconymous.gui.annotations

import javax.swing.JComponent

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Component annotation used to specify a component inside a [Frame] annotated class.
 *
 * @property constraints constraints used to specify the position of the component inside the frame.
 * @see [Frame]
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@AllowedOn([JComponent::class])
annotation class Component(val constraints: String = "")