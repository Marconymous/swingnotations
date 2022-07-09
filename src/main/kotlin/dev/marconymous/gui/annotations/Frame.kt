package dev.marconymous.gui.annotations

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * This annotation is used to mark a class as a GUI
 * The [dev.marconymous.gui.processors.AnnotationProcessor] will then process this class and generate a JFrame
 *
 * @property title The title of the JFrame
 * @property height The height of the JFrame
 * @property width The width of the JFrame
 *
 * @see dev.marconymous.gui.processors.AnnotationProcessor
 * @see javax.swing.JFrame
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Frame(val title: String, val height: Int, val width: Int)
