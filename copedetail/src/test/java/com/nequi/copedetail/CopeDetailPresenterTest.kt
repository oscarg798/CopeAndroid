package com.nequi.copedetail

import com.cope.core.CoroutineContextProvider
import com.cope.core.models.ViewCope
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Test
import java.util.*

class CopeDetailPresenterTest {

    @MockK
    lateinit var coroutineContextProvider: CoroutineContextProvider

    @MockK(relaxed = true)
    lateinit var view: CopeDetailContract.View

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when cope url is clicked then webpage should be open`() {
        val presenter = CopePresenter(coroutineContextProvider)

        presenter.bind(view)
        presenter.onViewCreated(viewCope)
        presenter.onCopeSourceClicked()

        verify {
            view.openCopeSource(COPE_SOURCE_URL)
        }
    }

    companion object {
        const val COPE_SOURCE_URL = "www.google.com"
        private val viewCope = ViewCope("1", COPE_SOURCE_URL, "2", Date(), Date(), listOf())
    }
}