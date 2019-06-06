package com.cope.login.di

import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
import com.cope.login.LoginActivity
import dagger.Component

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@FeatureScope
@Component(
    modules = [LoginModule::class],
    dependencies = [CoreComponent::class]
)
interface LoginComponent {

    fun inject(loginActivity: LoginActivity)
}
