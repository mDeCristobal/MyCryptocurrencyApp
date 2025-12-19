//package com.example.mycryptocurrencyapp.features.coins.presentation.coin_list
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.mycryptocurrencyapp.R
//import com.example.mycryptocurrencyapp.common.CoinDetailExtras
//import com.example.mycryptocurrencyapp.common.ui.base_fragment.BaseFragment
//import com.example.mycryptocurrencyapp.common.ui.loadingDialog.hideDialog
//import com.example.mycryptocurrencyapp.databinding.FragmentCoinListBinding
//import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin
//import com.example.mycryptocurrencyapp.features.coins.presentation.coin_list.adapters.CoinListAdapter
//import kotlinx.coroutines.flow.collect
//
//class CoinListFragment : BaseFragment(), CoinListAdapter.Interaction {
//
//    private val viewModel: CoinListViewModel by viewModels()
//    lateinit var binding: FragmentCoinListBinding
//    lateinit var  coinListAdapter : CoinListAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentCoinListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initCoinsRecycler()
//        subscribeToUiStates()
//    }
//
//    private fun initCoinsRecycler() {
//        binding.rvCoins.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            coinListAdapter = CoinListAdapter(
//                interaction = this@CoinListFragment
//            )
//            adapter = coinListAdapter
//        }
//    }
//
//    private fun subscribeToUiStates() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.state.collect { state ->
//                when (state) {
//                    is CoinListState.Success -> showCoinList(state.coinList)
//                    is CoinListState.Error -> handleError(state.message)
//                    is CoinListState.IsLoading -> handleLoading(state.boolean)
//                }
//            }
//        }
//    }
//
//    private fun showCoinList(coinList: List<Coin>) {
//        loadingDialogFragment.hideDialog()
//        coinListAdapter.submitList(coinList)
//    }
//
//    override fun navigateToCoinDetail(coinId: String) {
//        val bundle = Bundle()
//        bundle.putString(CoinDetailExtras.COIN_ID, coinId)
//        findNavController().navigate(
//            R.id.action_coinListFragment_to_coinDetailFragment,
//            bundle
//        )
//    }
//}