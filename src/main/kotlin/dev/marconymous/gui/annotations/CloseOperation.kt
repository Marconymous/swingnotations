package dev.marconymous.gui.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class CloseOperation(val value: Int)