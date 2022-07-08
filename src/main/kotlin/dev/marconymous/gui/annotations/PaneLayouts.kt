package dev.marconymous.gui.annotations

/**
 * Defines that the LayoutManager should be a BorderPane
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class BorderPane

/**
 * Defines that the LayoutManager should be a GridPane
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GridPane(val rows: Int, val columns: Int)

/**
 * Defines that the LayoutManager should be a FlowPane
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class FlowPane

