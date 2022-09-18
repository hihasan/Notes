package com.hihasan.notes.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.hihasan.notes.R

class CustomLoadingDialog(context: Context, title: String) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_loading_layout)

        setCancelable(true)
    }

    override fun show() {
        super.show()
    }

    override fun dismiss() {
        super.dismiss()
    }
}