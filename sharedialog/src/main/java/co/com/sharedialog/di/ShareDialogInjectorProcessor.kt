/*
 *
 *  * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 *  * This file is part of Cope.
 *  * Cope is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package co.com.sharedialog.di

import co.com.sharedialog.ShareDialogFragment
import com.cope.core.di.CoreComponent
import com.cope.core.di.injector.InjectorProcessor
import java.lang.ref.WeakReference
import kotlin.reflect.KClass

class ShareDialogInjectorProcessor : InjectorProcessor {

    override val supportedFields: Set<KClass<*>> = setOf(ShareDialogFragment::class)

    private  var component: WeakReference<ShareDialogComponent> = WeakReference<ShareDialogComponent>(null)

    override fun buildComponent(coreComponent: CoreComponent) {
        component = WeakReference(DaggerShareDialogComponent.builder()
            .shareDialogModule(ShareDialogModule)
            .coreComponent(coreComponent)
            .build())
    }

    override fun inject(field: Any) {
       component.get()!!.inject(field as ShareDialogFragment)
    }

    override fun destroy(field: Any) {
        component.clear()
    }
}