package co.com.sharedialog

import co.com.sharedialog.entities.ShareCopeParams
import co.com.sharedialog.interactor.ShareCopeInteractor
import com.cope.core.repositories.CopeRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ShareCopeInteractorTest {

    @MockK(relaxed = true)
    lateinit var repository: CopeRepository

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should share cope`() {
        val interactor =
            ShareCopeInteractor(repository)

        runBlocking {
            interactor(ShareCopeParams("1", "2"))
        }

        coVerify {
            repository.shareCope("1","2")
        }
    }
}
