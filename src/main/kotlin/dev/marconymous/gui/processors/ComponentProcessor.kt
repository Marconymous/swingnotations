package dev.marconymous.gui.processors

import javax.swing.JComponent
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotations

/**
 * @author Marconymous
 * @since SNAPSHOT-1.0.0
 *
 * Component Processor is used to process all annotations on a component.
 *
 * @param T The type of the GUI
 * @property clas The class of the Annotation
 */
abstract class ComponentProcessor<T>(private val clas: KClass<out Annotation>) {
    /**
     * Will handle the annotation on the component.
     *
     * @param property the property of the type T
     * @param component the component to process
     */
    abstract fun handle(property: KProperty1<T, *>, component: JComponent)

    /**
     * Will check if the annotation is valid for the component.
     *
     * @param prop the property of the type T
     * @return the Annotation if valid, null otherwise
     */
    protected fun validate(prop: KProperty1<T, *>): Annotation? {
        return prop.findAnnotations(clas).firstOrNull()
    }
}
