package com.nequi.copecontentdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.core.constants.ARGUMENT_COPE_CONTENT
import com.cope.core.constants.COPE_CONTENT_DETAIL_DEEP_LINK
import com.cope.core.di.CoreComponentProvider
import com.cope.core.models.ViewCopeContent
import com.nequi.copecontentdetail.di.CopeContentDetailModule
import com.nequi.copecontentdetail.di.DaggerCopeContentDetailComponent
import com.nequi.copecontentdetail.exceptions.CopeContentArgumentNotFoundException
import kotlinx.android.synthetic.main.activity_cope_content_detail.*
import javax.inject.Inject

@DeepLink(COPE_CONTENT_DETAIL_DEEP_LINK)
class CopeContentDetailActivity : AppCompatActivity(), CopeContentDetailContract.View {

    @Inject
    lateinit var presenter: CopeContentDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_content_detail)

        DaggerCopeContentDetailComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .copeContentDetailModule(CopeContentDetailModule())
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
        val copeContent = intent?.extras?.getParcelable<ViewCopeContent>(ARGUMENT_COPE_CONTENT)
            ?: throw CopeContentArgumentNotFoundException
        presenter.onViewCreated(copeContent)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBind()
    }

    override fun showCopeContent(content: String) {
        tvText?.text = content
    }
}
