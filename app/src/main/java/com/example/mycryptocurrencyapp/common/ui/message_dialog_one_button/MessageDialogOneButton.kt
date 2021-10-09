package com.example.mycryptocurrencyapp.common.ui.message_dialog_one_button

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.mycryptocurrencyapp.databinding.FragmentMessageDialogOneBtnBinding
import java.io.Serializable


class MessageDialogOneButton : DialogFragment() {

    lateinit var binding : FragmentMessageDialogOneBtnBinding

    companion object {
        const val MESSAGE_DIALOG_ONE_BUTTON_FRAGMENT_TAG = "MESSAGE_DIALOG"
        private const val MESSAGE_ARG = "MESSAGE"
        private const val BUTTON_TEXT_ARG = "positiveBtnText"
        private const val BUTTON_CALLBACK_ARG = "positiveBtnCallback"

        fun newInstance(
            message: String?,
            btnText: String,
            callbackButton: (dialog: DialogFragment) -> Unit,
        ): MessageDialogOneButton {
            val messageDialog = MessageDialogOneButton()
            val args = Bundle(3)
            args.apply {
                putString(MESSAGE_ARG, message)
                putString(BUTTON_TEXT_ARG, btnText)
                putSerializable(BUTTON_CALLBACK_ARG, callbackButton as Serializable)
            }
            messageDialog.arguments = args
            return messageDialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMessageDialogOneBtnBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(arguments!!) {
            binding.apply {
                tvMessage.text = getString(MESSAGE_ARG)
                btnFirst.text = getString(BUTTON_TEXT_ARG)
                val positiveButtonCallback =
                    getSerializable(BUTTON_CALLBACK_ARG) as (dialog: DialogFragment) -> Unit
                btnFirst.setOnClickListener {
                    positiveButtonCallback.invoke(this@MessageDialogOneButton)
                }
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (isVisible) return
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    override fun dismiss() {
        super.dismissAllowingStateLoss()
    }

    fun show(manager: FragmentManager) {
        show(manager, MESSAGE_DIALOG_ONE_BUTTON_FRAGMENT_TAG)
    }
}
