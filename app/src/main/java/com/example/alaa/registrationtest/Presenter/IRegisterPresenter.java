package com.example.alaa.registrationtest.Presenter;

public interface IRegisterPresenter {
    void doRegister(String name, String password, String email);
    boolean isEmailValid(String email);
    boolean isPasswordValid(String password, String ConfirmPassword);
    ApiInterface getInterfaceService();
}
