package com.example.dealcart_cricket.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bitorbits.bridge.BridgeHandles
import com.example.dealcart_cricket.databinding.FragmentCricketGameBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.dealcart_cricket.data.LeaderboardListUiData
import com.example.dealcart_cricket.data.LeaderboardUiData

@AndroidEntryPoint
class CricketFragment : Fragment() {
    private val cricketViewModel: CricketViewModel by viewModels()

    private lateinit var binding: FragmentCricketGameBinding

    private val bridgeHandles: BridgeHandles = mapOf(
        "android.game_over" to {
            // send score to server
//            cricketViewModel.addUserCricketScore(requireContext(), data?.toInt() ?: 0)
        },
        "android.game_leaderboard" to {
            val lead = LeaderboardUiData(
                firstRankName = "Saad",
                firstRankScore = 2,
                secondRankName = "Rohan",
                secondRankScore = 4,
                thirdRankName = "Zain",
                thirdRankScore = 4,
                userRankScore = 2,
                userRank = 5,
                leaderboardList = listOf(
                    LeaderboardListUiData(
                        5, "Tatheer", 3
                    )
                )
            )
//            findNavController().navigate(
//                CricketFragmentDirections.actionCricketGameFragmentToCricketLeaderboardDialogFragment(
//                    lead
//                )
//            )
            cricketViewModel.leaderboardLiveData.value?.let {
            }
        },
        "android.game_quit" to {
            findNavController().navigateUp()
        },
        "android.game_restart" to {

        },
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCricketGameBinding.inflate(inflater, container, false)

        binding.bridgeView.connect(
            savedInstanceState = savedInstanceState,
            scope = lifecycleScope,
            url = "cricket/index.html",
            fromAssets = true,
            handles = bridgeHandles
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        cricketViewModel.getLeaderboard(requireContext())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        try {
            binding.bridgeView.saveState(outState)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}