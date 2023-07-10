package bolli.adom.desa.white.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import bolli.adom.desa.databinding.FragmentBidBinding

class BidFragment : Fragment() {

    private var binding: FragmentBidBinding? = null
    private val args by navArgs<BidFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBidBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        click()
        getData()
        click2()
    }

    private fun click() {
        binding?.continueBtn?.setOnClickListener {
            val bid = binding?.bid?.text.toString().toInt()
            if (bid == 0) {
                Toast.makeText(requireContext(), "Rate cannot be zero", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val textBid = binding?.bid?.text.toString()
                binding?.bidInThrow?.text = textBid

                binding?.continueBtn?.visibility = View.INVISIBLE
                binding?.plusBtn?.visibility = View.INVISIBLE
                binding?.minusBtn?.visibility = View.INVISIBLE
                binding?.bid?.visibility = View.INVISIBLE

                binding?.continueBtn2?.visibility = View.VISIBLE
                binding?.plusBtn2?.visibility = View.VISIBLE
                binding?.minusBtn2?.visibility = View.VISIBLE
                binding?.bid2?.visibility = View.VISIBLE
                binding?.textView?.visibility = View.VISIBLE
            }
        }

        binding?.bid?.text = currentNumber.toString()
        binding?.plusBtn?.setOnClickListener {
            if (currentNumber <= args.balance - 50) {
                currentNumber += 50
            }
            binding?.bid?.text = currentNumber.toString()
            if (currentNumber == args.balance) {
                Toast.makeText(requireContext(), "You have reached the limit", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding?.minusBtn?.setOnClickListener {
            if (currentNumber != 0) {
                currentNumber -= 50
            }
            binding?.bid?.text = currentNumber.toString()
        }
    }

    private fun click2() {
        binding?.continueBtn2?.setOnClickListener {
            val bid = binding?.bidInThrow?.text.toString().toInt()
            val balance = binding!!.balanceTh.text.toString().toInt()
            val spin = binding?.bid2?.text.toString().toInt()
            if (spin == 0) {
                Toast.makeText(requireContext(), "Set the number of shots", Toast.LENGTH_SHORT)
                    .show()
            } else if (currentNumber2 < 3) {
                Toast.makeText(requireContext(), "Must be more than three", Toast.LENGTH_SHORT)
                    .show()
            } else {
                findNavController().navigate(
                    BidFragmentDirections.actionBidFragmentToGameFragment(
                        bid, balance, spin
                    )
                )
            }
        }

        binding?.plusBtn2?.setOnClickListener {
            binding?.bid2?.text = currentNumber2.toString()
            if (currentNumber2 == 0 || currentNumber2 < 20) {
                ++currentNumber2
                binding?.bid2?.text = currentNumber2.toString()
            } else if (currentNumber2 == 20) {
                Toast.makeText(requireContext(), "You have reached the limit", Toast.LENGTH_SHORT)
                    .show()
                binding?.bid2?.text = currentNumber2.toString()
            }
        }
        binding?.minusBtn2?.setOnClickListener {
            if (currentNumber2 != 0) {
                --currentNumber2
            }
            binding?.bid2?.text = currentNumber2.toString()
        }
    }

    private fun getData() {
        binding?.balanceTh?.text = args.balance.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        var currentNumber = 0
        var currentNumber2 = 0
    }
}