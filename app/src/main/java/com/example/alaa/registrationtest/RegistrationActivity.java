package com.example.alaa.registrationtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alaa.registrationtest.Presenter.IRegisterPresenter;
import com.example.alaa.registrationtest.Presenter.RegisterPresenter;
import com.example.alaa.registrationtest.Viewer.IRegisterView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity implements IRegisterView {

    @BindView(R.id.reg_nameET) AutoCompleteTextView reg_name;
    @BindView(R.id.reg_emailET) AutoCompleteTextView reg_email;
    @BindView(R.id.reg_passwordET) AutoCompleteTextView reg_password;
    @BindView(R.id.reg_confirmPasswordET) AutoCompleteTextView reg_confirm_password;

    IRegisterPresenter iRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        iRegisterPresenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.reg_submit) void submit() {
        // TODO call server...
        String name = reg_name.getText().toString();
        String email = reg_email.getText().toString();
        String password = reg_password.getText().toString();
        String confirm_password = reg_confirm_password.getText().toString();
        if(iRegisterPresenter.isEmailValid(email) && iRegisterPresenter.isPasswordValid(password,confirm_password)){
        iRegisterPresenter.doRegister(name,password,email);}
        else {
            Toast.makeText(this, "Check the entered data!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClearText() {
        reg_name.setText("");
        reg_email.setText("");
        reg_password.setText("");
        reg_confirm_password.setText("");
    }

    @Override
    public void onRegisterResult(Boolean result, int code) {

        if(result){
            onClearText();
            Intent profileIntent = new Intent(RegistrationActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}
