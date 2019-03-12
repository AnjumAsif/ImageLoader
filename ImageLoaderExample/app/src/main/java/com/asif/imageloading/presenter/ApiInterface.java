package com.asif.imageloading.presenter;

import com.asif.imageloading.data.network.model.ResultResponse;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiInterface {
    @GET()
    Observable<ResultResponse> getResultData(@Url() String url);
}
