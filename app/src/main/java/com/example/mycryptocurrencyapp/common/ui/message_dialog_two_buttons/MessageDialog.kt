package com.example.mycryptocurrencyapp.common.ui.message_dialog_two_buttons

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.mycryptocurrencyapp.databinding.FragmentMessageDialogBinding
import java.io.Serializable


class MessageDialog : DialogFragment() {

   lateinit var binding : FragmentMessageDialogBinding

    companion object {
        const val MESSAGE_DIALOG_FRAGMENT_TAG = "MESSAGE_DIALOG"
        private const val MESSAGE_ARG = "MESSAGE"
        private const val POSITIVE_BUTTON_TEXT_ARG = "positiveBtnText"
        private const val NEGATIVE_BUTTON_TEXT_ARG = "negativeBtnText"
        private const val POSITIVE_BUTTON_CALLBACK_ARG = "positiveBtnCallback"
        private const val NEGATIVE_BUTTON_CALLBACK_ARG = "negativeBtnCallback"

        fun newInstance(
            message: String?,
            positiveBtnText: String,
            negativeBtnText: String,
            callbackFirstButton: (dialog: DialogFragment) -> Unit,
            callbackSecondButton: (dialog: DialogFragment) -> Unit
        ): MessageDialog {
            val messageDialog = MessageDialog()
            val args = Bundle(5)
            args.apply {
                putString(MESSAGE_ARG, message)
                putString(POSITIVE_BUTTON_TEXT_ARG, positiveBtnText)
                putString(NEGATIVE_BUTTON_TEXT_ARG, negativeBtnText)
                putSerializable(POSITIVE_BUTTON_CALLBACK_ARG, callbackFirstButton as Serializable)
                putSerializable(NEGATIVE_BUTTON_CALLBACK_ARG, callbackSecondButton as Serializable)
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
        binding = FragmentMessageDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(requireArguments()) {
            binding.apply {
                tvMessage.text = getString(MESSAGE_ARG)
                btnFirst.text = getString(POSITIVE_BUTTON_TEXT_ARG)
                val positiveButtonCallback =
                    getSerializable(POSITIVE_BUTTON_CALLBACK_ARG) as (dialog: DialogFragment) -> Unit
                btnFirst.setOnClickListener {
                    positiveButtonCallback.invoke(this@MessageDialog)
                }

                val negativeButtonCallback =
                    getSerializable(NEGATIVE_BUTTON_CALLBACK_ARG) as (dialog: DialogFragment) -> Unit
                btnThird.setOnClickListener {
                    negativeButtonCallback.invoke(this@MessageDialog)
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
        show(manager, MESSAGE_DIALOG_FRAGMENT_TAG)
    }
}
