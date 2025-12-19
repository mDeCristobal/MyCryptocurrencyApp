//package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.mycryptocurrencyapp.R
//import com.example.mycryptocurrencyapp.common.ui.base_fragment.BaseFragment
//import com.example.mycryptocurrencyapp.common.ui.loadingDialog.hideDialog
//import com.example.mycryptocurrencyapp.databinding.FragmentCoinDetailBinding
//import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail
//import com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail.adapters.TagsAdapter
//import com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail.adapters.TeamMembersAdapter
//import kotlinx.coroutines.flow.collect
//
//class CoinDetailFragment : BaseFragment() {
//
//    private lateinit var tagsAdapter: TagsAdapter
//    private lateinit var teamAdapter: TeamMembersAdapter
//    lateinit var binding: FragmentCoinDetailBinding
//    private val viewModel: CoinDetailViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initTagsRecycler()
//        initTeamRecycler()
//        subscribeToUiStates()
//    }
//
//    private fun subscribeToUiStates() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.state.collect { state ->
//                when (state) {
//                    is CoinDetailState.Success -> showCoinDetail(state.coinDetail)
//                    is CoinDetailState.Error -> handleError(state.message)
//                    is CoinDetailState.IsLoading -> handleLoading(state.boolean)
//                }
//            }
//        }
//    }
//
//    private fun showCoinDetail(coinDetail: CoinDetail) {
//        loadingDialogFragment.hideDialog()
//        binding.apply {
//            tvName.text = coinDetail.name
//            tvActive.text = if (coinDetail.isActive) {
//                context?.resources?.getString(
//                    R.string.coin_is_active,
//                )
//            } else {
//                context?.resources?.getString(
//                    R.string.coin_is_not_active,
//                )
//            }
//
//            if(coinDetail.name.isEmpty()) {
//                tvName.visibility = View.GONE
//            } else {
//                tvName.text = coinDetail.name
//            }
//            if(coinDetail.description.isEmpty()) {
//                tvDescription.visibility = View.GONE
//            } else {
//                tvDescription.text = coinDetail.description
//            }
//            if(coinDetail.tags.isNullOrEmpty()) {
//                tvTagsTitle.visibility = View.GONE
//                rvTags.visibility = View.GONE
//            } else {
//                tvTagsTitle.visibility = View.VISIBLE
//                tagsAdapter.submitList(coinDetail.tags)
//            }
//            if(coinDetail.team.isNullOrEmpty()) {
//                tvTeamMembersTitle.visibility = View.GONE
//                rvMembers.visibility = View.GONE
//            } else {
//                tvTeamMembersTitle.visibility = View.VISIBLE
//                teamAdapter.submitList(coinDetail.team)
//            }
//        }
//    }
//
//    private fun initTagsRecycler() {
//        binding.rvTags.apply {
//            layoutManager = LinearLayoutManager(
//                requireActivity(),
//                LinearLayoutManager.HORIZONTAL,
//                false)
//            tagsAdapter = TagsAdapter()
//            adapter = tagsAdapter
//        }
//    }
//
//    private fun initTeamRecycler() {
//        binding.rvMembers.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            teamAdapter = TeamMembersAdapter()
//            adapter = teamAdapter
//        }
//    }
//
//}