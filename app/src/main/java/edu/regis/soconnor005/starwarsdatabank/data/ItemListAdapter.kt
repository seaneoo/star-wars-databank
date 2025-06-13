package edu.regis.soconnor005.starwarsdatabank.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.regis.soconnor005.starwarsdatabank.database.Entry
import edu.regis.soconnor005.starwarsdatabank.databinding.ListItemBinding

class ItemListAdapter(
    val entries: List<Entry>,
    val onClickListener: (Entry) -> Unit,
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemViewHolder {
        return ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
    ) {
        val entry = entries[position]
        holder.binding.title.text = entry.name
        holder.binding.title.setCompoundDrawablesWithIntrinsicBounds(
            entry.category.getDrawable(holder.itemView.context), null, null, null
        )
        holder.itemView.setOnClickListener { view ->
            onClickListener(entry)
        }
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}
