package com.cope.core.di.injector

import com.cope.core.di.CoreComponent
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

interface InjectorProcessor {

    val supportedFields: Set<KClass<*>>

    fun buildComponent(coreComponent: CoreComponent)

    fun inject(field: Any)

}
