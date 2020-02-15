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

package com.cope.logger

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CopeLoggerTest {


    @MockK(relaxed = true)
    private lateinit var processor1: LoggerProcessor

    @MockK(relaxed = true)
    private lateinit var processor2: LoggerProcessor

    private val viewEvent = LogEvent.View(MyView::class.java)

    lateinit var logger: CopeLogger

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { processor1.isEventSupported(viewEvent) } answers { true }
        every { processor2.isEventSupported(viewEvent) } answers { false }

        logger = CopeLogger(listOf(processor1, processor2))
    }

    @Test
    fun `when a event arrived to the logger then all the processor that can handle it should be called`() {

        logger.log(viewEvent)

        verify {
            processor1.isEventSupported(viewEvent)
            processor2.isEventSupported(viewEvent)
            processor1.log(viewEvent)
        }

        verify(exactly = 0) {
            processor2.log(viewEvent)
        }
    }

    companion object {
        class MyView
    }
}