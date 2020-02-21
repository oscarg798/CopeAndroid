package com.cope.login.di

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cope.core.di.CoreComponent
import com.cope.core.di.injector.InjectorProcessor
import com.cope.login.LoginActivity
import dagger.Component
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class LoginInjectorProcessor : InjectorProcessor {

    override val supportedFields: Set<KClass<*>> = setOf(LoginActivity::class)

    private lateinit var loginComponent: WeakReference<LoginComponent>

    override fun buildComponent(coreComponent: CoreComponent) {
        loginComponent = WeakReference(
            DaggerLoginComponent.builder()
                .loginModule(LoginModule)
                .coreComponent(coreComponent)
                .build()
        )
    }

    override fun inject(field: Any) {
        loginComponent.get()!!.inject(field as LoginActivity)

    }

    override fun destroy(field: Any) {
        loginComponent.clear()
    }
}