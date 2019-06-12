package com.cope.core.mapper

import com.cope.core.models.CopeContent
import com.cope.core.models.ViewCopeContent

/**
 * @author Oscar Gallon on 2019-06-12.
 */
object ViewCopeContentMapper {

    fun map(copeContent: CopeContent): ViewCopeContent {
        return ViewCopeContent(copeContent.id, copeContent.text)
    }
}
