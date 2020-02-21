package com.nequi.copedetail.di

import com.cope.core.di.CoreComponent
import com.cope.core.di.injector.InjectorProcessor
import com.nequi.copedetail.CopeDetailActivity
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class CopeDetailInjectorProcessor : InjectorProcessor {

    override val supportedFields: Set<KClass<*>> = setOf(CopeDetailActivity::class)

    private lateinit var component: WeakReference<CopeDetailComponent>

    override fun buildComponent(coreComponent: CoreComponent) {
        component = WeakReference(
            DaggerCopeDetailComponent.builder()
                .coreComponent(coreComponent)
                .copeModule(CopeModule).build()
        )

    }

    override fun inject(field: Any) {
        component.get()!!.inject(field as CopeDetailActivity)
    }

    override fun destroy(field: Any) {
        component.clear()
    }

}