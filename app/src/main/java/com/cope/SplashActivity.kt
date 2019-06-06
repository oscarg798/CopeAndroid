package com.cope

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cope.core.DeepLinkHandlerImpl
import com.cope.core.DeepLinkProcessor
import com.cope.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    val processors = HashSet<DeepLinkProcessor>()
    val deepLinkerHandler = DeepLinkHandlerImpl(processors)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        processors.add(SignUpDeepLinkProcessor(this))

        ivLogo?.postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 3000)
    }


    override fun onNewIntent(intent: Intent?) {
       // super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    private fun handleIntent(intent: Intent) {
        intent.data?.toString()?.let { link ->
            deepLinkerHandler.processors.firstOrNull {
                it.matches(link)
            }?.execute(link)
        }
        //finish()
    }
}
