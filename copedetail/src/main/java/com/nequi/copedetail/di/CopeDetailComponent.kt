package com.nequi.copedetail.di

import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
import com.nequi.copedetail.CopeDetailActivity
import dagger.Component

/**
 * @author Oscar Gallon on 2019-06-12.
 */
@FeatureScope
@Component(
    modules = [CopeModule::class],
    dependencies = [CoreComponent::class]
)
interface CopeDetailComponent {

    fun inject(copeDetailActivity: CopeDetailActivity)
}
