package co.cope.di

import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
import co.cope.SignUpActivity
import dagger.Component

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@FeatureScope
@Component(
    modules = [SignUpModule::class],
    dependencies = [CoreComponent::class]
)
interface SignUpComponent {

    fun inject(signUpActivity: SignUpActivity)
}
