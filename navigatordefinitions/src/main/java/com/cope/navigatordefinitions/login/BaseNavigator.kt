package com.cope.navigatordefinitions.login

import androidx.appcompat.app.AppCompatActivity

/**
 * @author Oscar Gallon on 2019-06-06.
 */
interface BaseNavigator{

    var activity: AppCompatActivity?

    fun attach(activity: AppCompatActivity){
        this.activity = activity
    }

    fun deattach(){
        activity = null
    }

    fun onBackPressed()

}
