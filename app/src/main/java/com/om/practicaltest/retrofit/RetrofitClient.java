package com.om.practicaltest.retrofit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitClient {

    @Inject
    public RetrofitClient() {
    }

    Retrofit getRetrofitClient()
        {
            String BASE_URL = "https://dummy.restapiexample.com/api/";
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        public RetrofitAPi getAPI()
        {
            return getRetrofitClient().create(RetrofitAPi.class);
        }
}
