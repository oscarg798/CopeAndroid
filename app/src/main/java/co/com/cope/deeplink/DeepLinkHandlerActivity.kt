package co.com.cope.deeplink

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.com.cope.R
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.cope.copelist.deeplink.CopeListDeepLinkModule
import com.cope.copelist.deeplink.CopeListDeepLinkModuleLoader
import com.cope.core.constants.SIGNUP_DEEPLINK
import com.cope.login.LoginDeepLinkModule
import com.cope.login.LoginDeepLinkModuleLoader
import com.google.android.play.core.splitinstall.SplitInstallHelper
import com.nequi.copecontentdetail.deeplink.CopeContentDetailDeepLinkModule
import com.nequi.copecontentdetail.deeplink.CopeContentDetailDeepLinkModuleLoader
import com.nequi.copedetail.deeplink.CopeDetailDeepLinkModule
import com.nequi.copedetail.deeplink.CopeDetailDeepLinkModuleLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@DeepLinkHandler(
    LoginDeepLinkModule::class,
    CopeListDeepLinkModule::class,
    CopeDetailDeepLinkModule::class,
    CopeContentDetailDeepLinkModule::class
)
class DeepLinkHandlerActivity : AppCompatActivity() {

    //TODO: Inject this one
    private val dynamicModuleInstallerInteractor = DynamicModuleInstallerInteractor(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.data != null && intent.data.toString() == SIGNUP_DEEPLINK) {
            navigateToDynamicFeatureModule()
            finish()
            return
        }

        val deepLinkDelegate = DeepLinkDelegate(
            LoginDeepLinkModuleLoader(),
            CopeListDeepLinkModuleLoader(),
            CopeDetailDeepLinkModuleLoader(),
            CopeContentDetailDeepLinkModuleLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }

    //TODO: put this on a presenter
    private fun navigateToDynamicFeatureModule() {
        CoroutineScope(Dispatchers.Main).launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    dynamicModuleInstallerInteractor(getString(R.string.title_dynamicsignup))
                }
            }.fold({
                navigateToFeature()
            }, {
                Toast.makeText(
                    this@DeepLinkHandlerActivity,
                    "Soemthing went wrong with dynamic feature",
                    Toast.LENGTH_LONG
                ).show()
            })
        }
    }

    private fun navigateToFeature() {
        val newContext = createPackageContext(packageName, 0)
        SplitInstallHelper.updateAppInfo(newContext)
        val intent = Intent().setClassName(
            packageName,
            "co.com.cope.SignUpActivity"
        )
        startActivity(intent)
    }
}
