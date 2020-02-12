package com.cope.copelist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cope.copelist.R
import com.cope.copelist.di.CopeListModule
import com.cope.copelist.di.DaggerCopeListComponent
import com.cope.copelist.fragment.adapter.CopeAdapter
import com.cope.copelist.fragment.adapter.CopeViewHolder
import com.cope.copelist.fragment.adapter.viewholderfactory.CopeItem2ViewHolderFactory
import com.cope.copelist.fragment.adapter.viewholderfactory.CopeItemViewHolderFactory
import com.cope.copelist.fragment.adapter.viewholderfactory.ViewHolderFactory
import com.cope.core.constants.ARGUMENT_COPE
import com.cope.core.constants.COPE_DETAIL_DEEP_LINK
import com.cope.core.constants.FEATURE_FLAG_HANDLER
import com.cope.core.constants.StringResourceId
import com.cope.core.di.CoreComponentProvider
import com.cope.core.extensions.startDeepLinkIntent
import com.cope.core.featureflags.FeatureFlagHandler
import com.cope.core.models.Cope
import com.cope.core.models.ViewCope
import kotlinx.android.synthetic.main.fragment_cope_list.*
import javax.inject.Inject
import javax.inject.Named

class CopeListFragment : Fragment(), CopeListContract.View {

    @Inject
    lateinit var presenter: CopeListContract.Presenter

    @Inject
    lateinit var viewHolderFactories: List<@JvmSuppressWildcards ViewHolderFactory<@JvmSuppressWildcards CopeViewHolder>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCopeListComponent.builder()
            .coreComponent((activity!!.application as CoreComponentProvider).getCoreComponent())
            .copeListModule(CopeListModule())
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cope_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context ?: return
        rvCopes?.layoutManager = LinearLayoutManager(context)
        rvCopes?.setHasFixedSize(true)
        rvCopes?.adapter =
            CopeAdapter(
                presenter, viewHolderFactories = viewHolderFactories
            )

        srMain?.setOnRefreshListener(presenter)

        presenter.onViewCreated()
    }

    override fun openCopeDetails(viewCope: ViewCope) {
        (activity as? AppCompatActivity)?.startDeepLinkIntent(
            COPE_DETAIL_DEEP_LINK,
            Bundle().apply {
                putParcelable(ARGUMENT_COPE, viewCope)
            })
    }

    override fun showCopes(copes: List<Cope>) {
        val adapter = rvCopes?.adapter as? CopeAdapter
            ?: return
        adapter.add(copes)
    }

    override fun showError(error: String) {
        val context = context ?: return
        Toast.makeText(
            context, error,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showError(error: StringResourceId) {
        showError(getString(error))
    }

    override fun showProgressDialog() {
        srMain?.isRefreshing = true
    }

    override fun hideProgressDialog() {
        srMain?.isRefreshing = false
    }

    companion object {
        fun newInstance(): CopeListFragment {
            return CopeListFragment()
        }
    }
}
