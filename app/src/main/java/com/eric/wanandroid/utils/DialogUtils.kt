package com.eric.wanandroid.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context

/**
 * Created by eric on 20-10-12
 */
class DialogUtils {

    companion object{
        fun showLoadingDialog(
            context: Context,
            msg: String
        ): Dialog{
            val dialog = ProgressDialog(context)
            dialog.setCancelable(false)
            dialog.setMessage(msg)
            return dialog
        }

        fun disDialog(dialog: Dialog?){
            if (dialog != null && dialog.isShowing){
                dialog.dismiss()
            }
        }
    }
}