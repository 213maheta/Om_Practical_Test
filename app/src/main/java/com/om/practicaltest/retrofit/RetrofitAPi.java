package com.om.practicaltest.retrofit;

import com.om.practicaltest.model.DummyData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPi {

    @GET("v1/employees")
    Call<DummyData> getEmployeeList();
}
