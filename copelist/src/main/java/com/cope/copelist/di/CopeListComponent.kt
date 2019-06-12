package com.cope.copelist.di

import com.cope.copelist.fragment.CopeListFragment
import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
import dagger.Component

/**
 * @author Oscar Gallon on 2019-06-11.
 */
@FeatureScope
@Component(
    modules = [CopeListModule::class],
    dependencies = [CoreComponent::class]
)
interface CopeListComponent {

    fun inject(copeListFragment: CopeListFragment)
}
