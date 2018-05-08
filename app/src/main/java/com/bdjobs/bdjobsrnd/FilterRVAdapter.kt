package com.bdjobs.bdjobsrnd

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.nio.file.Files.size



class FilterRVAdapter(var itemList: ArrayList<String>, private val context: Context) : RecyclerView.Adapter<FilterRVAdapter.ViewHolder>(), Filterable {

    var filteredItems: ArrayList<String>? =null

    init {
        filteredItems= itemList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTV.text = filteredItems!![position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_filterlist, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return filteredItems?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV = itemView.findViewById<TextView>(R.id.textView6)
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {

                Log.d("aaa","Size:e")
                val charString = charSequence.toString()
                filteredItems = if (charString.isEmpty()) {
                    itemList
                } else {
                    val filteredList = ArrayList<String>()
                    for (row in itemList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.toLowerCase().contains(charString.toLowerCase()) || row.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredItems
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filteredItems = filterResults.values as ArrayList<String>
                Log.d("aaa","Size: ${filterResults.count}")

                notifyDataSetChanged()
            }
        }
    }

}