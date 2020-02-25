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
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class ShareDialogPresenter(
    private val emailValidator: (String) -> Boolean,
    private val shareCopeInteractor: Interactor<Unit, ShareCopeParams>,
    override val coroutinesContextProvider: CoroutineContextProvider
) :
    ShareDialogContract.Presenter {

    override val parentJob: Job = Job()
    override var view: ShareDialogContract.View? = null

    override fun onSharePressed(email: String, id: String) {
        if (!emailValidator.invoke(email)) {
            view?.showError(R.string.invalid_email_error)
            return
        }

        launchJobOnMainDispatchers {
            runCatching {
                withContext(coroutinesContextProvider.backgroundContext) {
                    shareCopeInteractor(
                        ShareCopeParams(
                            id,
                            email
                        )
                    )
                }
            }.fold({
                view?.dismiss()
            }, {
                view?.showError(R.string.share_error)
            })
        }

    }
}