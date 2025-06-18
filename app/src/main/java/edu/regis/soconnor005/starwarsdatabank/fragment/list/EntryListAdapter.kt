package edu.regis.soconnor005.starwarsdatabank.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.regis.soconnor005.starwarsdatabank.database.Entry
import edu.regis.soconnor005.starwarsdatabank.databinding.EntryListItemBinding
import edu.regis.soconnor005.starwarsdatabank.fragment.list.EntryListAdapter.EntryListViewHolder

class EntryListAdapter(val onClickListener: (Entry) -> Unit) :
    ListAdapter<Entry, EntryListViewHolder>(EntryDiffItemCallback()) {
    class EntryListViewHolder(val binding: EntryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): EntryListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return EntryListViewHolder(
                    EntryListItemBinding.inflate(
                        layoutInflater, parent, false
                    )
                )
            }
        }

        fun bind(entry: Entry, onClickListener: (Entry) -> Unit) {
            binding.title.text = entry.name
            binding.title.setCompoundDrawablesWithIntrinsicBounds(
                entry.category.getDrawable(
                    itemView.context
                ), null, null, null
            )
            itemView.setOnClickListener { view -> onClickListener(entry) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EntryListViewHolder = EntryListViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(
        holder: EntryListViewHolder,
        position: Int,
    ) {
        val entry = getItem(position)
        holder.bind(entry, onClickListener)
    }
}
