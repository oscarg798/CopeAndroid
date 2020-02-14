package co.com.cope.deeplink.staticfeatures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import com.cope.copelist.deeplink.CopeListDeepLinkModule
import com.cope.copelist.deeplink.CopeListDeepLinkModuleLoader
import com.cope.login.LoginDeepLinkModule
import com.cope.login.LoginDeepLinkModuleLoader
import com.nequi.copecontentdetail.deeplink.CopeContentDetailDeepLinkModule
import com.nequi.copecontentdetail.deeplink.CopeContentDetailDeepLinkModuleLoader
import com.nequi.copedetail.deeplink.CopeDetailDeepLinkModule
import com.nequi.copedetail.deeplink.CopeDetailDeepLinkModuleLoader
import kotlinx.coroutines.*

/**
 * @author Oscar Gallon on 2019-06-06.
 */
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@DeepLinkHandler(
    LoginDeepLinkModule::class,
    CopeListDeepLinkModule::class,
    CopeDetailDeepLinkModule::class,
    CopeContentDetailDeepLinkModule::class
)
class DeepLinkHandlerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val deepLinkDelegate = DeepLinkDelegate(
            LoginDeepLinkModuleLoader(),
            CopeListDeepLinkModuleLoader(),
            CopeDetailDeepLinkModuleLoader(),
            CopeContentDetailDeepLinkModuleLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}
