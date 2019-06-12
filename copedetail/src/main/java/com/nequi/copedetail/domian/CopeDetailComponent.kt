package com.nequi.copedetail.domian

import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
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
