package com.cope.core.di.injector

import com.cope.core.di.CoreComponent

class ApplicationInjector(
    private val processors: List<InjectorProcessor>,
    private val coreComponent: CoreComponent
) : Injector {

    override fun inject(field: Any) {
        val processor = processors.first {
            it.supportedFields.contains(field::class)
        }

        processor.buildComponent(coreComponent)
        processor.inject(field)
    }

    override fun destroy(field: Any) {
        processors.first {
            it.supportedFields.contains(field::class)
        }.destroy(field)
    }
}
