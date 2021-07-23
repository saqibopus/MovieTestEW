package com.saqib.movietestew.networking

import com.saqib.movietestew.helper.Constants
import com.saqib.movietestew.model.PicksModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface {
    @GET(Constants.ENDPOINTS.PICKS)
    fun requestPicksList(
        @Query("api-key") apiKey: String
    ): Observable<Response<PicksModel>>
}