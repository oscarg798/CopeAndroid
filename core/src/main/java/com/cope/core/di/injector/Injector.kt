package com.cope.core.di.injector

interface Injector {

    fun inject(field: Any)

    fun destroy(field: Any)

}