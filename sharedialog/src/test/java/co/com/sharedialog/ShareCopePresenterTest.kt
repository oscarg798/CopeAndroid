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

package co.com.sharedialog

import co.com.sharedialog.entities.ShareCopeParams
import com.cope.core.CoroutineContextProvider
import com.cope.core.interactor.Interactor
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test

class ShareCopePresenterTest {

    @MockK
    lateinit var interactor: Interactor<Unit, ShareCopeParams>

    @MockK(relaxed = true)
    lateinit var view: ShareDialogContract.View

    private val coroutineContextProvider =
        CoroutineContextProvider(Dispatchers.Unconfined, Dispatchers.Unconfined)

    private val emailValidator: (String) -> Boolean = {
        it == "1"
    }

    init {
        MockKAnnotations.init(this)
    }

    @Before
    fun setup() {
        coEvery { interactor(any()) } answers { Unit }
    }

    @Test
    fun `should share cope`() {
        val presenter = ShareDialogPresenter(emailValidator, interactor, coroutineContextProvider)

        presenter.bind(view)

        presenter.onSharePressed("1", "2")

        coVerify {
            interactor(match {
                it.email == "1" && it.id == "2"
            })
            view.dismiss()
        }
    }

    @Test
    fun `if email not valid should show error`() {
        val presenter = ShareDialogPresenter(emailValidator, interactor, coroutineContextProvider)

        presenter.bind(view)

        presenter.onSharePressed("2", "2")

        verify {
            view.showError(any())
            interactor wasNot Called
        }

    }

    @Test
    fun `should show error`() {
        coEvery { interactor(any()) } answers { throw Exception() }

        val presenter = ShareDialogPresenter(emailValidator, interactor, coroutineContextProvider)

        presenter.bind(view)

        presenter.onSharePressed("1", "2")

        coVerify {
            view.showError(any())
        }
    }
}