package com.saqib.movietestew.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saqib.movietestew.R
import com.saqib.movietestew.adapters.MovieListAdapter
import com.saqib.movietestew.databinding.ActivityMainBinding
import com.saqib.movietestew.helper.Logs
import com.saqib.movietestew.model.ResultsModel
import com.saqib.movietestew.viewmodel.MainActivityVM

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainActivityVM
    private lateinit var binding: ActivityMainBinding
    private val layoutManager = LinearLayoutManager(this)

    private var movieListAdapter: MovieListAdapter? = null
    private var onPage = 1
    private var isSorted = false
    private var oldMovieList = ArrayList<ResultsModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUtil()
        initToolbar("Movie Picks")
        initViewModelUtils()
        callMoviePicks()
        handleClicks()
    }

    private fun handleClicks() {
        binding.btSortByName.setOnClickListener {
            if (isSorted) {
                sortToOldValues()
                binding.btSortByName.text = "A-Z"
            } else {
                sortAtoZ()
                binding.btSortByName.text = "Z-A"
            }
        }

        binding.etSearchQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                movieListAdapter?.filter?.filter(p0)
            }
        })
    }

    private fun initUtil() {
        viewmodel = ViewModelProvider(this).get(MainActivityVM::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    /**
     * getError() receives errors form ViewModel.
     * getLoading() receives progress values from ViewModel.
     **/
    private fun initViewModelUtils() {
        viewmodel.getError().observe(this, Observer {
            showError(it)
        })
        viewmodel.getLoading().observe(this, Observer {
            if (it)
                showProgress()
            else
                hideProgress()
        })
    }

    /**
     * Update Title in toolbar.
     * */
    private fun initToolbar(title: String) {
        setSupportActionBar(binding.tb.toolbar)
        binding.tb.toolbar.title = title
    }


    /**
     * LiveData observer for Api Response
     * **/
    private fun callMoviePicks() {
        viewmodel.getMoviesLists().observe(this, Observer { pickModel ->
            Logs.p("List : $pickModel")
            pickModel?.let {
                Logs.p("not null")
                initUpdateFactsAdapter(it.results as ArrayList<ResultsModel>)
            } ?: kotlin.run {
                Logs.p("null")
            }
        })
    }


    private fun initUpdateFactsAdapter(moviesList: ArrayList<ResultsModel>) {
        if (movieListAdapter == null) {
            oldMovieList = moviesList
            binding.rvMovieList.layoutManager = layoutManager
            movieListAdapter = MovieListAdapter(this, oldMovieList)
            binding.rvMovieList.adapter = movieListAdapter
        } else {
            movieListAdapter?.addNewItems(moviesList)
        }
    }

    /**
     * Following method updated data page wise, Due to not availibility of paging
     * in data API  method is un-used.
     * */
    private fun updateMovieList(moviesList: ArrayList<ResultsModel>) {
        movieListAdapter?.let {
            movieListAdapter?.addNewItems(moviesList)
        }
    }

    private fun showProgress() {
        binding.rvMovieList.visibility = View.GONE
        binding.pbProgress.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE
    }

    private fun hideProgress() {
        binding.rvMovieList.visibility = View.VISIBLE
        binding.pbProgress.visibility = View.GONE
        binding.tvError.visibility = View.GONE
    }

    private fun showError(error: String) {
        binding.rvMovieList.visibility = View.GONE
        binding.pbProgress.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = error
    }

    private fun sortAtoZ() {
        isSorted = true
        val sortedList = oldMovieList.sortedBy {
            it.display_title
        }
        movieListAdapter?.updateSortedList(sortedList)

    }

    private fun sortToOldValues() {
        isSorted = false
        Logs.p("sortToOldValues : $oldMovieList")
        val sortedList = oldMovieList.sortedByDescending {
            it.display_title
        }
        movieListAdapter?.updateSortedList(sortedList)
    }
}