package bolli.adom.desa.white.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bolli.adom.desa.R
import bolli.adom.desa.databinding.FragmentGameBinding
import bolli.adom.desa.white.ui.fragments.BidFragment.Companion.currentNumber
import bolli.adom.desa.white.ui.fragments.BidFragment.Companion.currentNumber2
import kotlin.random.Random

class GameFragment : Fragment() {

    private var binding: FragmentGameBinding? = null
    private val args by navArgs<GameFragmentArgs>()

    private var click = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    @SuppressLint("SetTextI18n")
    private fun initialize() {
        getData()
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim)
        val listOfBalls = listOf(
            binding?.ball1,
            binding?.ball2,
            binding?.ball3,
            binding?.ball4,
            binding?.ball5
        )
        val listOfCards = listOf(
            binding?.card1,
            binding?.card2,
            binding?.card3,
            binding?.card4,
            binding?.card5,
            binding?.card6,
            binding?.card7,
            binding?.card8
        )
        listOfCards.forEach {
            it?.text = (1..100).random().toString()
            it?.tag = "not done"
        }
        binding?.throwInThrow?.text = click.toString()
        binding?.btnThrow?.setOnClickListener {
            if (click < currentNumber2) {
                ++click
                if (click == currentNumber2) {
                    gameOver(false)
                }
                binding?.throwInThrow?.text = click.toString()

                listOfBalls.forEachIndexed { _, it ->
                    val value = Random.nextInt(1, 100)
                    it?.startAnimation(animation)
                    it?.visibility = View.VISIBLE
                    it?.text = value.toString()
                }

                listOfBalls.forEach { balls ->
                    listOfCards.forEach { card ->
                        if (balls?.text.toString() == card?.text.toString()) {
                            card?.animate()?.scaleX(0f)?.scaleY(0f)?.duration = 500
                            card?.tag = "done"
                        }
                    }
                }
                var countWin = 0
                listOfCards.forEach {
                    if (it?.tag.toString() == "done") {
                        countWin++
                    }
                }
                if (countWin == 8) {
                    gameOver(true)
                }
            }
        }
    }

    private fun gameOver(isWin: Boolean) {
        binding?.btnThrow?.text = "Result"
        binding?.btnThrow?.setOnClickListener {
            val balance = binding?.balanceInThrow?.text.toString().toInt()
            val bid = binding?.bidInThrow?.text.toString().toInt()
            findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToResultFragment(
                    balance, bid, isWin
                )
            )
        }

    }

    private fun getData() {
        binding?.throwInThrow?.text = args.bid.toString()
        binding?.balanceInThrow?.text = args.balance.toString()
        binding?.bidInThrow?.text = args.spin.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        currentNumber = 0
        currentNumber2 = 0
        click = 0
    }
}