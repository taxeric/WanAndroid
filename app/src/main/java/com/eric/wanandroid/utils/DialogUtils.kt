package com.eric.wanandroid.utils

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText

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

        fun showEditIdDialog(
            context: Context,
            hint: String,
            listener: EditIDListener
        ): Dialog{
            val view = EditText(context)
            view.hint = hint
            val dialog = AlertDialog.Builder(context)
                .setTitle("设定显示头像账号")
                .setView(view)
                .setPositiveButton("OK"
                ) { _, _ -> listener.id(view.text.toString()) }
            return dialog.create()
        }

        fun disDialog(dialog: Dialog?){
            if (dialog != null && dialog.isShowing){
                dialog.dismiss()
            }
        }
    }

    interface EditIDListener{
        fun id(content: String)
    }
}