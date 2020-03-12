package com.company.doria.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var preferences: SharedPreferences

    fun showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message,
                duration)

        if(duration == Snackbar.LENGTH_INDEFINITE){
            snackBar.setAction("OK") { snackBar.dismiss() }
        }

        snackBar.show()
    }

    fun showSnackBar(message: Int, duration: Int = Snackbar.LENGTH_SHORT) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message,
                duration)

        if(duration == Snackbar.LENGTH_INDEFINITE){
            snackBar.setAction("OK",null)
        }

        snackBar.show()
    }
}