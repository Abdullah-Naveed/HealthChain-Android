package com.abdullahnaveed.fyp_health_chain.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

    private HealthChainApi healthChainApi;

    public HealthChainApi getHealthChainApi() {
        return healthChainApi;
    }

    public void setHealthChainApi(HealthChainApi healthChainApi) {
        this.healthChainApi = healthChainApi;
    }

    public HealthChainApi getServiceClass(String serverURL) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        setHealthChainApi(retrofit.create(HealthChainApi.class));
        return getHealthChainApi();

    }

}
