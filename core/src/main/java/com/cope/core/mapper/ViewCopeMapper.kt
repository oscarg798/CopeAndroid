package com.cope.core.mapper

import com.cope.core.models.Cope
import com.cope.core.models.ViewCope

/**
 * @author Oscar Gallon on 2019-06-12.
 */
object ViewCopeMapper {

    fun map(cope: Cope): ViewCope {
        return ViewCope(cope.id, cope.url, cope.title, cope.createdAt, cope.updateAt, cope.content.map {
            ViewCopeContentMapper.map(it)
        }, cope.icon)
    }
}
