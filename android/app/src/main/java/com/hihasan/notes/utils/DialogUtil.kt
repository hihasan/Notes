package com.hihasan.notes.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hihasan.notes.R


class DialogUtil (context: Context?) {
    private var context: Context? = null
    private var progressDialog: ProgressDialog? = null

    init {
        this.context = context
        progressDialog = ProgressDialog(context)
    }

//    fun DialogUtil(context: Context?) {

//    }

    //different type of method is available for alert dialog
    fun showProgressDialog() {
        progressDialog!!.setMessage(context!!.getString(R.string.loading))
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }


    fun showProgressDialog(msg: String?) {
        //    progressDialog = new ProgressDialog(context);
        var msg = msg
        if (msg == null || msg.isEmpty()) {
            msg = "Please wait..."
        }
        progressDialog!!.setMessage(msg)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }


    fun dismissProgress() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }


    fun showDialogYesNo(
        msg: String?,
        yesListener: DialogInterface.OnClickListener?,
        noListener: DialogInterface.OnClickListener?
    ) {
        val dialog = context?.let { MaterialAlertDialogBuilder(it) }
        dialog!!.setMessage(msg)
        dialog.setCancelable(false)
        dialog.setPositiveButton(R.string.yes, yesListener)
        dialog.setNegativeButton(R.string.no, noListener)
        dialog.show()
    }
}