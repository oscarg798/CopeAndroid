package co.com.cope.deeplink.dynamicfeature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.cope.di.DaggerDynamicFeatureHandlerComponent
import co.com.cope.di.dynamicfeature.DynamicFeatureHandlerModule
import com.cope.core.di.CoreComponentProvider
import javax.inject.Inject

class DynamicFeaturesDeepLinkHandlerActivity : AppCompatActivity(),
    DynamicFeatureDeepLinkHandlerContract.View {

    @Inject
    lateinit var presenter: DynamicFeatureDeepLinkHandlerContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.data == null) {
            finish()
            return
        }

        DaggerDynamicFeatureHandlerComponent.builder()
            .coreComponent((application as CoreComponentProvider).getCoreComponent())
            .dynamicFeatureHandlerModule(DynamicFeatureHandlerModule)
            .build()
            .inject(this)

        presenter.bind(this)

        presenter.onDeepLinkObtained(intent.data!!.toString())
    }

    override fun openDynamicFeature(dynamicFeaturePackageName: String) {
        val intent = Intent().setClassName(
            packageName,
            dynamicFeaturePackageName
        )

        startActivity(intent)
        finish()
    }
}