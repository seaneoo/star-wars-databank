package edu.regis.soconnor005.starwarsdatabank.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.regis.soconnor005.starwarsdatabank.database.Entry
import edu.regis.soconnor005.starwarsdatabank.databinding.EntryListItemBinding

class EntryListAdapter(
    val entries: List<Entry>,
    val onClickListener: (Entry) -> Unit,
) : RecyclerView.Adapter<EntryListAdapter.EntryListViewHolder>() {
    class EntryListViewHolder(val binding: EntryListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EntryListViewHolder {
        return EntryListViewHolder(
            EntryListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: EntryListViewHolder,
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
