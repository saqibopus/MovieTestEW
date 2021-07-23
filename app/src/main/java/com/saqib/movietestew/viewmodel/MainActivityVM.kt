package com.saqib.movietestew.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saqib.movietestew.base.BaseVM
import com.saqib.movietestew.networking.ApiClient
import com.saqib.movietestew.helper.Constants
import com.saqib.movietestew.model.PicksModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivityVM : BaseVM() {

    fun getMoviesLists(): LiveData<PicksModel> {
        val model: MutableLiveData<PicksModel> = MutableLiveData()
        showLoading()
        ApiClient
            .getClient()
            .requestPicksList(Constants.KEYS.KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Response<PicksModel>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    getDisposable().add(d)
                }

                override fun onNext(t: Response<PicksModel>) {
                    hideLoading()
                    when {
                        t.code() == Constants.ErrorCodes.OK200 -> {
                            model.postValue(t.body())
                        }
                        t.code() == Constants.ErrorCodes.ERROR401 -> {
                            hasError(Constants.ErrorMessage.COMMON_UN_IDENTIFIED)
                        }
                        else -> {
                            hasError(Constants.ErrorMessage.COMMON_UN_IDENTIFIED)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    hideLoading()
                    hasError("${e.cause}")
                }
            })
        return model
    }
}