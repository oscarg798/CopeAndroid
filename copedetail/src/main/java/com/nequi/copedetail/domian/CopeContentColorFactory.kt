package com.nequi.copedetail.domian

import android.graphics.Color
import com.cope.core.constants.BackgroundColor
import kotlin.random.Random

/**
 * @author Oscar Gallon on 2019-06-12.
 */

object CopeContentColorFactory {

    private val colors = listOf(
        Color.parseColor("#03a9f4"),
        Color.parseColor("#ffc107"),
        Color.parseColor("#f44336"),
        Color.parseColor("#cddc39")
    )

    private val random = Random(colors.size)

    fun getBackgroundColor(): BackgroundColor {
        return colors[(0 until colors.size).random()]
    }
}
