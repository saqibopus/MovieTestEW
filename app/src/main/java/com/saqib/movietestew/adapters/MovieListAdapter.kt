package com.saqib.movietestew.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.saqib.movietestew.databinding.AdapterMovieListBinding
import com.saqib.movietestew.helper.Logs
import com.saqib.movietestew.helper.loadImage
import com.saqib.movietestew.model.ResultsModel
import com.saqib.movietestew.view.MovieDetailActivity

class MovieListAdapter(
    private val context: Context,
    private val searchList: ArrayList<ResultsModel>
) :
    RecyclerView.Adapter<MovieListAdapter.Holder>(), Filterable {

    private var filterSearchList = ArrayList<ResultsModel>()

    init {
        filterSearchList = searchList
    }

    class Holder(private val binding: AdapterMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, model: ResultsModel) {
            binding.model = model

            binding.cardMain.setOnClickListener {
                val intent = Intent(context, MovieDetailActivity::class.java)
                val bundle = Bundle().apply {
                    this.putParcelable("model", model)
                }
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            AdapterMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return filterSearchList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(context, filterSearchList[position])
    }

    fun addNewItems(newList: ArrayList<ResultsModel>) {
        filterSearchList.clear()
        filterSearchList.addAll(newList)
        notifyDataSetChanged()
    }

    fun getAllItems(): ArrayList<ResultsModel> {
        return searchList
    }

    fun updateSortedList(list: List<ResultsModel>) {
        Logs.p("updateSortedList : $list")
        filterSearchList.clear()
        filterSearchList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString()
                if (charString.isEmpty()) {
                    filterSearchList = searchList
                } else {
                    val list = ArrayList<ResultsModel>()

                    for (model: ResultsModel in searchList) {
                        if (model.display_title.toLowerCase().contains(charString.toLowerCase())) {
                            list.add(model)
                        }
                    }
                    filterSearchList = list
                }
                val filterResult = FilterResults()
                filterResult.values = filterSearchList
                return filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filterSearchList = p1?.values as ArrayList<ResultsModel>
                notifyDataSetChanged()
            }

        }
    }
}