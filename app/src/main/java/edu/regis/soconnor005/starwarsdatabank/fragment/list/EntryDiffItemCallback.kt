package edu.regis.soconnor005.starwarsdatabank.fragment.list

import androidx.recyclerview.widget.DiffUtil
import edu.regis.soconnor005.starwarsdatabank.database.Entry

class EntryDiffItemCallback : DiffUtil.ItemCallback<Entry>() {
    override fun areItemsTheSame(
        oldItem: Entry,
        newItem: Entry,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Entry,
        newItem: Entry,
    ): Boolean {
        return oldItem == newItem
    }
}
