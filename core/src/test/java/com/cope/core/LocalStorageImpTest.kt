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

package com.cope.core

import android.content.Context
import android.content.SharedPreferences
import com.cope.core.exceptions.DataNoFoundOnLocalStorageException
import com.cope.core.repositories.LocalStorageRepositoryImpl
import com.google.gson.Gson
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2019-06-11.
 */
class LocalStorageImpTest : MockableTest {

    @MockK
    lateinit var context: Context

    @MockK
    lateinit var sharedPreferences: SharedPreferences

    @MockK
    lateinit var sharedPreferenceEditor: SharedPreferences.Editor

    @Before
    override fun setup() {
        super.setup()

        every {
            sharedPreferences.getString("myStringKey", null)
        }.answers {
            "oscar"
        }

        every {
            sharedPreferences.getString("myIntKey", null)
        }.answers {
            "12"
        }

        every {
            sharedPreferences.getString("myLongKey", null)
        }.answers {
            "11"
        }

        every {
            sharedPreferences.getString("myDoubleKey", null)
        }.answers {
            "10"
        }

        every {
            sharedPreferences.getString("myObjectKey", null)
        }.answers {
            Gson().toJson(SomeObject("1", 2))
        }

        every {
            sharedPreferences.edit()
        }.answers {
            sharedPreferenceEditor
        }

        every {
            sharedPreferenceEditor.putString(any(), any())
        }.answers {
            sharedPreferenceEditor
        }

        every {
            sharedPreferenceEditor.apply()
        }.answers {
            Unit
        }

        every {
            sharedPreferences.getString("myMissingKey", null)
        }.answers {
            null
        }
    }

    @Test
    fun `should get an string`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myStringKey", String::class.java)
        }

        then {
            result shouldEqual "oscar"
        }
    }

    @Test
    fun `should get an int`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myIntKey", Int::class.java)
        }

        then {
            result shouldEqual 12
        }
    }

    @Test
    fun `should get a Long`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myLongKey", Long::class.java)
        }

        then {
            result shouldEqual 11.toLong()
        }
    }

    @Test
    fun `should get a double`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myDoubleKey", Double::class.java)
        }

        then {
            result shouldEqual 10.toDouble()
        }
    }

    @Test
    fun `should get some object`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        val result = whenever {
            repository.getData("myObjectKey", SomeObject::class.java)
        }

        then {
            result shouldEqual SomeObject("1", 2)
        }
    }

    @Test
    fun `should save a value`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        whenever {
            repository.saveData("myObjectKey", 123)
        }

        then {
            verify {
                sharedPreferenceEditor.putString("myObjectKey", "123")
            }
        }
    }

    @Test(expected = DataNoFoundOnLocalStorageException::class)
    fun `should get an exception if no exception is found`() {
        val repository = given {
            LocalStorageRepositoryImpl(context) {
                sharedPreferences
            }
        }

        whenever {
            repository.getData("myMissingKey", String::class.java)
        }
    }
}
