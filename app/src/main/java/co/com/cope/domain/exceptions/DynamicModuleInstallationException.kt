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

package co.com.cope.domain.exceptions

sealed class DynamicModuleInstallationException(
    open val featureName: String,
    override val cause: Throwable? = null
) :
    Exception("Error installing feature ${featureName}") {
    class InstallationFailed(
        override val featureName: String,
        override val cause: Throwable? = null
    ) :
        DynamicModuleInstallationException(featureName, cause)

    class NetworkError(
        override val featureName: String,
        override val cause: Throwable? = null
    ) :
        DynamicModuleInstallationException(featureName, cause)
}
