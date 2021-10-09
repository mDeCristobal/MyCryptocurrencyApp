package com.example.mycryptocurrencyapp.common.ui.base_fragment

import androidx.fragment.app.Fragment
import com.example.mycryptocurrencyapp.R
import com.example.mycryptocurrencyapp.common.CustomErrorsEnum
import com.example.mycryptocurrencyapp.common.ui.loadingDialog.LoadingDialogFragment
import com.example.mycryptocurrencyapp.common.ui.loadingDialog.hideDialog
import com.example.mycryptocurrencyapp.common.ui.loadingDialog.showDialog
import com.example.mycryptocurrencyapp.common.ui.message_dialog_one_button.MessageDialogOneButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var loadingDialogFragment: LoadingDialogFragment

    protected fun handleError(message: String) {
        when(message) {
            CustomErrorsEnum.NO_INTERNET.name -> { showNoConnectionDialog() }
            else -> { showGeneralErrorDialog() }
        }
    }

    protected open fun showGeneralErrorDialog() {
        MessageDialogOneButton.newInstance(
            getString(R.string.cant_access_data_error),
            getString(R.string.accept_dialog_btn)
        ) { dialog -> dialog.dismiss() }.show(parentFragmentManager)
    }

    protected open fun showNoConnectionDialog() {
        MessageDialogOneButton.newInstance(
            getString(R.string.no_internet_error),
            getString(R.string.accept_dialog_btn)
        ) { dialog -> dialog.dismiss() }.show(parentFragmentManager)
    }

    protected open fun handleLoading(loading: Boolean) {
        if(loading) loadingDialogFragment.showDialog(parentFragmentManager)
        else loadingDialogFragment.hideDialog()
    }

}