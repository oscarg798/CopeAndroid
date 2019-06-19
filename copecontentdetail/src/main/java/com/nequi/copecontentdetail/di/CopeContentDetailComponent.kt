package com.nequi.copecontentdetail.di

import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
import com.nequi.copecontentdetail.CopeContentDetailActivity
import dagger.Component

/**
 * @author Oscar Gallon on 2019-06-13.
 */
@FeatureScope
@Component(
    modules = [CopeContentDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface CopeContentDetailComponent {

    fun inject(copeContentDetailActivity: CopeContentDetailActivity)

}
