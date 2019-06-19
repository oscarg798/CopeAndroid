package com.cope.copelist.data.mapper

import com.cope.copelist.data.entities.APICopeContent
import com.cope.core.DateParser
import com.cope.core.models.CopeContent

/**
 * @author Oscar Gallon on 2019-06-12.
 */
object APICopeContentMapper {

    fun map(apiCopeContent: APICopeContent): CopeContent {
        return CopeContent(
            apiCopeContent.id, apiCopeContent.text,
            DateParser.getCopeDateFromBackendDate(apiCopeContent.createdAt),
            DateParser.getCopeDateFromBackendDate(apiCopeContent.updatedAt)
        )
    }
}
