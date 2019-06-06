package com.cope.core.interactor

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface Interactor<Response, Params> where Response : Any {

    suspend operator fun invoke(
        params: Params
    ): Response
}
