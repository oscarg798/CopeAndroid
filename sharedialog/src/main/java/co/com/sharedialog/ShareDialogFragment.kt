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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.cope.core.InjectableDialogFragment
import kotlinx.android.synthetic.main.fragment_share_dialog.*
import javax.inject.Inject

class ShareDialogFragment : InjectableDialogFragment(), ShareDialogContract.View {

    @Inject
    lateinit var presenter: ShareDialogContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ShareDialogStyle);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)

        btnShare?.setOnClickListener {
            val copeId = arguments?.getString(ARGUMENT_COPE_ID)
                ?: throw IllegalArgumentException("You must provide cope Id")

            presenter.onSharePressed(etEmail.text.toString(), copeId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unBind()
    }

    override fun showError(errorStringId: Int) {
        Toast.makeText(context, getString(errorStringId), Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val ARGUMENT_COPE_ID = "ARGUMENT_COPE_ID"

        fun newInstance(copeId: String) = ShareDialogFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_COPE_ID, copeId)
            }
        }
    }

}
