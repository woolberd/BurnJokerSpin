package bolli.adom.desa.white.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import bolli.adom.desa.R
import bolli.adom.desa.databinding.FragmentHomeBinding
import bolli.adom.desa.white.utils.PreferenceHelper

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val preferenceHelper = PreferenceHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        click()
        getData()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        preferenceHelper.unit(requireContext())
        if (preferenceHelper.saveNum?.isEmpty() == true) {
            binding?.balance?.text = "2000"
        } else if (this.preferenceHelper.saveNum!!.toInt() == 0) {
            binding?.balance?.text = "200"
        } else {
            binding?.balance?.text = preferenceHelper.saveNum
            preferenceHelper.saveNum = binding?.balance?.text.toString()
        }
    }

    private fun click() {
        binding?.btnInfo?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_coinFragment)
        }
        binding?.btnExit?.setOnClickListener {
            requireActivity().finish()
        }
        binding?.btnPlay?.setOnClickListener {
            val balance = binding?.balance?.text.toString().toInt()
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToBidFragment(
                    balance
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}