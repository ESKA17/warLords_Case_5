package kz.jusan.tinderformentors.presentation.fragments.tinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.yuyakaido.android.cardstackview.*
import kz.jusan.tinderformentors.databinding.FragmentTinderBinding
import kz.jusan.tinderformentors.presentation.adapters.CardStackAdapter
import kz.jusan.tinderformentors.util.Constants.INITIAL_CARDS_LIST

class TinderFragment : Fragment() {

    private var _binding: FragmentTinderBinding? = null
    private val binding get() = _binding!!

    lateinit var cardStackLayoutManager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTinderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        cardStackLayoutManager = CardStackLayoutManager(activity)
        cardStackLayoutManager.setStackFrom(StackFrom.Top)

        val cardStackAdapter = CardStackAdapter()
        cardStackAdapter.cards = INITIAL_CARDS_LIST

        binding.csvMenteesAndMentors.apply {
            layoutManager = cardStackLayoutManager
            adapter = cardStackAdapter
        }

        setupButtons()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupButtons() {
        binding.skipButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackLayoutManager.setSwipeAnimationSetting(setting)
            binding.csvMenteesAndMentors.swipe()
        }

        binding.rewindButton.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            cardStackLayoutManager.setRewindAnimationSetting(setting)
            binding.csvMenteesAndMentors.rewind()
        }

        binding.likeButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackLayoutManager.setSwipeAnimationSetting(setting)
            binding.csvMenteesAndMentors.swipe()
        }
    }
}