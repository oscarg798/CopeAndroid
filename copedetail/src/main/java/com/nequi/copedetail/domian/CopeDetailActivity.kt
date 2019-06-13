package com.nequi.copedetail.domian

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.constants.ARGUMENT_COPE
import com.cope.core.constants.COPE_DETAIL_DEEP_LINK
import com.cope.core.di.CoreComponentProvider
import com.cope.core.models.ViewCope
import com.cope.core.models.ViewCopeContent
import com.nequi.copedetail.R
import kotlinx.android.synthetic.main.activity_cope_detail.*
import javax.inject.Inject

@DeepLink(COPE_DETAIL_DEEP_LINK)
class CopeDetailActivity : AppCompatActivity(), CopeDetailContract.View {

    @Inject
    lateinit var presenter: CopeDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_detail)

        DaggerCopeDetailComponent.builder()
            .copeModule(CopeModule())
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .build()
            .inject(this)

        initComponents()
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
        val intentExtras = intent?.extras ?: throw RuntimeException()
        val viewCope = intentExtras.getParcelable<ViewCope>(ARGUMENT_COPE) ?: throw RuntimeException()
        presenter.onViewCreated(viewCope)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun showCopeContent(viewCopeContents: List<ViewCopeContent>) {
        (rvCopeContent?.adapter as? CopeDetailAdapter)?.add(viewCopeContents)
    }

    override fun showCopeTitle(title: String) {
        tvCopeTitle?.text = title
    }

    override fun showCopeUrl(url: String) {
        tvCopeUrl?.text = url
    }

    private fun initComponents() {
        rvCopeContent?.setHasFixedSize(false)
        rvCopeContent?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvCopeContent?.adapter = CopeDetailAdapter()
    }
}
