package com.cope.navigatordefinitions.login

import com.cope.navigatordefinitions.login.impl.LoginNavigatorImpl
import dagger.Module
import dagger.Provides

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@Module
class NavigatorModule {

    @Provides
    fun provideLoginNavigator(): LoginNavigator {
        return LoginNavigatorImpl()
    }
}
