package com.nequi.copecontentdetail.di

import android.util.Log
import com.cope.core.di.CoreComponent
import com.cope.core.di.injector.InjectorProcessor
import com.nequi.copecontentdetail.CopeContentDetailActivity
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class CopeContentDetailInjectorProcessor : InjectorProcessor {

    override val supportedFields: Set<KClass<*>> = setOf(CopeContentDetailActivity::class)

    private lateinit var component: WeakReference<CopeContentDetailComponent>

    override fun buildComponent(coreComponent: CoreComponent) {
        component = WeakReference(
            DaggerCopeContentDetailComponent.builder()
                .copeContentDetailModule(CopeContentDetailModule)
                .coreComponent(coreComponent).build()
        )
    }

    override fun inject(field: Any) {
        component.get()!!.inject(field as CopeContentDetailActivity)
    }

    override fun destroy(field: Any) {
        component.clear()
    }
}