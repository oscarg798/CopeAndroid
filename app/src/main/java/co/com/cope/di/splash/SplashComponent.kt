/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.com.cope.di.splash

import co.com.cope.splash.SplashActivity
import com.cope.core.di.CoreComponent
import com.cope.core.di.FeatureScope
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
