package dev.marconymous.gui.annotations

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Defines that the LayoutManager should be a BorderPane
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class BorderPane

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Defines that the LayoutManager should be a GridPane
 *
 * @property rows The number of rows in the GridPane
 * @property columns The number of columns in the GridPane
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GridPane(val rows: Int, val columns: Int)

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Defines that the LayoutManager should be a FlowPane
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class FlowPane()

