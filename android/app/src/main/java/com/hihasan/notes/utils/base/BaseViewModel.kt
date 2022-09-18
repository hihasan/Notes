package com.hihasan.notes.utils.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.hihasan.notes.constant.DatabaseConstants

abstract class BaseViewModel(context : Context) : ViewModel(){


    var database: BaseDatabase? = let {
        Room.databaseBuilder(context, BaseDatabase::class.java, DatabaseConstants.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

    fun hideKeyboard(context: Context, edt: AppCompatEditText) {
        val imm = context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edt.windowToken, 0)
    }

}