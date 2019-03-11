package com.abdullahnaveed.fyp_health_chain.Retrofit;

import com.abdullahnaveed.fyp_health_chain.User;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HealthChainApi {

    @GET("/user/getAllUsers")
    Call<List<User>> getUsers();

    @GET("/patients/getSecretAndPps")
    Call<ArrayList<String>> getSecretAndPps(@Query("userName") String userName);

    @GET("/patients/getDecryptedRecord")
    Call<String> getRecord(@Query("name") String name, @Query("encryptedRecord") String encryptedRecord);


}
