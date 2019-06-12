package com.cope.core.contract

import com.cope.core.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface BasePresenter<View : BaseView> {

    var view: View?

    var parentJob: Job

    val coroutinesContextProvider: CoroutineContextProvider

    fun bind(view: View) {
        this.view = view
        parentJob = Job()
    }

    fun unBind(){
        view = null
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }



    fun launchJobOnMainDispatchers(job: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(coroutinesContextProvider.mainContext + parentJob).launch {
            job()
        }
    }

}
