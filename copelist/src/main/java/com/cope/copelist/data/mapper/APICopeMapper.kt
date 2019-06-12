package com.cope.copelist.data.mapper

import com.cope.copelist.data.entities.APICope
import com.cope.core.DateParser
import com.cope.core.models.Cope

/**
 * @author Oscar Gallon on 2019-06-11.
 */
object APICopeMapper {

    fun map(apiCope: APICope): Cope {
        return Cope(
            apiCope.id,
            apiCope.url,
            apiCope.title,
            DateParser.getCopeDateFromBackendDate(apiCope.createdAt),
            DateParser.getCopeDateFromBackendDate(apiCope.updatedAt),
            apiCope.content.map { APICopeContentMapper.map(it)},
            apiCope.icon
        )
    }
}
