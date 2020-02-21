package com.cope.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.cope.core.di.injector.InjectorProvider
import com.nequi.core.R

abstract class InjectableFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as InjectorProvider).getInjector().inject(this)
    }

}