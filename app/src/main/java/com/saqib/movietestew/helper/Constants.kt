package com.saqib.movietestew.helper

object Constants {

    object KEYS {
        const val KEY = "KFGwd6UWM3swGhIvDbst4I9c8jt0r5vR"
    }
    object ENDPOINTS {
        const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/reviews/"
        const val PICKS = "picks.json"
    }
    object ErrorCodes{
        const val OK200 = 200
        const val ERROR401 = 401
    }
    object ErrorMessage{
        const val COMMON_UN_IDENTIFIED = "Oops..Something went wrong"
        const val NO_DESC_AVAILABLE = "No description available"
        const val NO_TITLE_AVAILABLE = "No title available"
    }
}