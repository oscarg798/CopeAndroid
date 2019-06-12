package com.cope.di

import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
import com.cope.splash.SplashActivity
import dagger.Component

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@FeatureScope
@Component(
    modules = [SplashModule::class],
    dependencies = [CoreComponent::class]
)
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)
}