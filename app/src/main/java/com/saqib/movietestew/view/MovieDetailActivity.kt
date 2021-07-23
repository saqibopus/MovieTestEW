package com.saqib.movietestew.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.saqib.movietestew.R
import com.saqib.movietestew.databinding.ActivityMovieDetailBinding
import com.saqib.movietestew.helper.Logs
import com.saqib.movietestew.helper.loadImage
import com.saqib.movietestew.model.ResultsModel

class MovieDetailActivity : AppCompatActivity() {

    private var model: ResultsModel? = null
    private lateinit var binding:ActivityMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_detail)
        getIntentData()
        setupToolbarTitle(model?.display_title)
        setUpUI()
    }

    private fun getIntentData() {
        model = intent.getParcelableExtra("model")
        Logs.p("getIntentData - $model")
    }
    private fun setupToolbarTitle(title: String?) {
        binding.tb.title.text = title
        binding.tb.imgBack.setOnClickListener {
            finish()
        }
    }
    private fun setUpUI(){
        model?.let {
            binding.imgPoster.loadImage(this, it.multimedia.src)
            binding.tvTitle.text = it.display_title
            binding.tvHeadline.text = it.headline
            binding.tvSummary.text = it.summary_short
            binding.tvBy.text = it.byline
            binding.tvOpeningDate.text = it.opening_date
            binding.tvPublicationDate.text = it.publication_date
            binding.tvMPAARating.text = it.mpaa_rating
        }
    }
}