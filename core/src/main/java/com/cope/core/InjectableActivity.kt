package com.cope.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cope.core.di.injector.InjectorProvider

abstract class InjectableActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as InjectorProvider).getInjector().inject(this)
    }
}