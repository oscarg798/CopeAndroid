package com.cope.copelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLink
import com.cope.copelist.fragment.CopeListFragment
import com.cope.core.constants.COPE_LIST_DEEP_LINK

@DeepLink(COPE_LIST_DEEP_LINK)
class CopeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cope_list)

        supportFragmentManager.beginTransaction()
            .replace(R.id.clMain, CopeListFragment.newInstance(), CopeListFragment::class.java.name)
            .commitAllowingStateLoss()
    }
}
