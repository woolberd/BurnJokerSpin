package bolli.adom.desa.white.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import bolli.adom.desa.R
import bolli.adom.desa.databinding.FragmentCoinBinding
import bolli.adom.desa.white.model.CoinModel
import bolli.adom.desa.white.repository.CoinRepository
import bolli.adom.desa.white.ui.adapter.CoinAdapter

class CoinFragment : Fragment() {

    private var binding: FragmentCoinBinding? = null
    private val adapterC = CoinAdapter(this::onItemClick)

    private val repo = CoinRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        binding?.recView.apply {
            adapterC.submitList(repo.getListIfData())
            binding?.recView?.adapter = adapterC
        }
        binding?.btnHome?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun onItemClick(model: CoinModel) {
        Toast.makeText(requireContext(), model.num2, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}