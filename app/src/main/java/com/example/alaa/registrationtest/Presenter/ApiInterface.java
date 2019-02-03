package com.example.alaa.registrationtest.Presenter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("registrationMockService?")
    Call<Register> authenticate(@Path("emailKey") String email, @Path("passwordKey") String password);

}