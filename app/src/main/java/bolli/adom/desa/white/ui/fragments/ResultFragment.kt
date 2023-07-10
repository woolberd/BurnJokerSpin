package bolli.adom.desa.white.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bolli.adom.desa.R
import bolli.adom.desa.databinding.FragmentResultBinding
import bolli.adom.desa.white.utils.PreferenceHelper

class ResultFragment : Fragment() {

    private var binding: FragmentResultBinding? = null
    private val args by navArgs<ResultFragmentArgs>()
    private val preferenceHelper = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        getData()
        getResult()
        clickBtn()
    }

    private fun clickBtn() {
        preferenceHelper.unit(requireContext())
        binding?.btnHome?.setOnClickListener {
            val currentCount = binding?.currentBalance1?.text.toString()
            preferenceHelper.saveNum = currentCount
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
        binding?.btnRestart?.setOnClickListener {
            val balance = binding?.currentBalance1?.text.toString().toInt()
            findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToBidFragment(
                    balance
                )
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getResult() {
        val playWon = args.win
        val bid = args.bid
        var balance = args.balance
        binding?.result?.visibility = View.VISIBLE
        if (playWon) {
            balance += bid * 2
            binding?.result?.text = "WIN"
            binding?.currentBalance1?.text = balance.toString()
        } else {
            balance -= bid
            binding?.result?.text = "LOSE"
            binding?.currentBalance1?.text = balance.toString()
        }
    }

    private fun getData() {
        binding?.bid1?.text = args.bid.toString()
        binding?.pastBalance1?.text = args.balance.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}