package com.example.mycryptocurrencyapp.common.ui.loadingDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.mycryptocurrencyapp.R

class LoadingDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false
        return  inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {
        }
    }
}

fun LoadingDialogFragment.showDialog(fragmentManager: FragmentManager) = run {
    if (!this.isAdded){ this.show(fragmentManager, "loader") }
}

fun LoadingDialogFragment.hideDialog() = run {
    if (this.isAdded) { this.dismissAllowingStateLoss() }
}