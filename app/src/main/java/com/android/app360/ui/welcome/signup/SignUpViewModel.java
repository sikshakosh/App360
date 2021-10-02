package com.android.app360.ui.welcome.signup;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.app360.ui.welcome.account.LoginUser;

public class SignUpViewModel extends AndroidViewModel {
    public SignUpViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<SignUpUser> userMutableLiveData;

    public MutableLiveData<SignUpUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public void onClick(View view) {

        SignUpUser loginUser = new SignUpUser(EmailAddress.getValue(), Password.getValue());

        userMutableLiveData.setValue(loginUser);

    }
}
