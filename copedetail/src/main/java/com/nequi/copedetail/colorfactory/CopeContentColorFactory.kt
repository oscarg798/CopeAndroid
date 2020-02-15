/*
 * Copyright (C) 2020 Oscar Gallon <oscarg798@gmail.com>
 * This file is part of Cope.
 * Cope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
