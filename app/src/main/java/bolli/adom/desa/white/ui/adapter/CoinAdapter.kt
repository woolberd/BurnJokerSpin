package bolli.adom.desa.white.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bolli.adom.desa.databinding.ItemBinding
import bolli.adom.desa.white.model.CoinModel

class CoinAdapter(
    val onItemClick: (model: CoinModel) -> Unit
) : ListAdapter<CoinModel, CoinAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(adapterPosition)?.let { li -> onItemClick(li) }
            }
        }

        fun onBind(model: CoinModel?) {
            binding.tvNum.text = model?.num.toString()
            binding.tvDollars.text = model?.num2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinAdapter.ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<CoinModel>() {
            override fun areItemsTheSame(
                oldItem: CoinModel,
                newItem: CoinModel
            )
                    : Boolean {
                return oldItem.num == newItem.num
            }

            override fun areContentsTheSame(
                oldItem: CoinModel,
                newItem: CoinModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}