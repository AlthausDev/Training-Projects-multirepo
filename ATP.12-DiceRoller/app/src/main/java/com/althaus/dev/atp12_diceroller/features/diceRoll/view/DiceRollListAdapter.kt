import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.althaus.dev.atp12_diceroller.databinding.ItemDiceRollBinding
import com.althaus.dev.atp12_diceroller.model.DiceRoll

class DiceRollListAdapter : ListAdapter<DiceRoll, DiceRollListAdapter.DiceRollViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<DiceRoll>() {
        override fun areItemsTheSame(oldItem: DiceRoll, newItem: DiceRoll): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DiceRoll, newItem: DiceRoll): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceRollViewHolder {
        return DiceRollViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DiceRollViewHolder, position: Int) {
        val diceRoll = getItem(position)
        holder.bind(diceRoll)
    }

    class DiceRollViewHolder private constructor(private val binding: ItemDiceRollBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diceRoll: DiceRoll) {
            binding.diceRoll = diceRoll
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DiceRollViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDiceRollBinding.inflate(layoutInflater, parent, false)
                return DiceRollViewHolder(binding)
            }
        }
    }
}
