package com.example.alaa.registrationtest.Presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.alaa.registrationtest.ProfileActivity;
import com.example.alaa.registrationtest.RegistrationActivity;
import com.example.alaa.registrationtest.Viewer.IRegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterPresenter implements IRegisterPresenter {

    IRegisterView iRegisterView;

    final String BASE_URL ="https://demo4649152.mockable.io/";

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;

    }

    @Override
    public void doRegister(String name, String password, String email) {
        ApiInterface mApiService = this.getInterfaceService();
        Call<Register> mService = mApiService.authenticate(email, password);
        mService.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Register mRegisterObject = response.body();
                String returnedResponse = mRegisterObject.isRegistered;
                //showProgress(false);
                if (returnedResponse.trim().equals("Submitted")) {
                    // redirect to Main Activity page

                    iRegisterView.onRegisterResult(true,1);

                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.v("Failure", t.getMessage());
            }
        });
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @Override
    public boolean isPasswordValid(String password, String confirm_password) {
        if(password.equals(confirm_password) && password.length() > 4){return true;}
        else return false;
    }

    @Override
    public ApiInterface getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL+"registrationMockService")
                .build();
        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }
}
