package com.cope.copelist.di

import com.cope.copelist.CopeListActivity
import com.cope.copelist.fragment.CopeListFragment
import com.cope.core.di.CoreComponent
import com.cope.core.di.injector.InjectorProcessor
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class CopeListInjectorProcessor : InjectorProcessor {

    override val supportedFields: Set<KClass<*>> =
        setOf(CopeListActivity::class, CopeListFragment::class)

    private var component: WeakReference<CopeListComponent> = WeakReference<CopeListComponent>(null)

    override fun buildComponent(coreComponent: CoreComponent) {
        if (component.get() == null) {
            component = WeakReference(
                DaggerCopeListComponent.builder()
                    .copeListModule(CopeListModule)
                    .coreComponent(coreComponent).build()
            )
        }
    }

    override fun inject(field: Any) {
        when (field) {
            is CopeListActivity -> component.get()!!.inject(field)
            is CopeListFragment -> component.get()!!.inject(field)
            else -> throw IllegalArgumentException("Not supported field, availables fields are $supportedFields")
        }
    }
}