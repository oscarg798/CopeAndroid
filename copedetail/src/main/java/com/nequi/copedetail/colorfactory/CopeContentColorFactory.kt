package com.nequi.copedetail.colorfactory

import android.graphics.Color
import com.cope.core.constants.BackgroundColor
import kotlin.random.Random

/**
 * @author Oscar Gallon on 2019-06-12.
 */

object CopeContentColorFactory {

    private val colors = listOf(
        Color.parseColor("#0d47a1"),
        Color.parseColor("#1b5e20"),
        Color.parseColor("#f9a825"),
        Color.parseColor("#bf360c")
    )

    private val random = Random(colors.size)

    fun getBackgroundColor(): BackgroundColor {
        return colors[(0 until colors.size).random()]
    }
}
