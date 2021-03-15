package com.megaache.mvvmdemo.ui.login;

import com.megaache.mvvmdemo.base.BaseViewModel;
import com.megaache.mvvmdemo.MyApplication;
import com.megaache.mvvmdemo.model.ErrorData;
import com.megaache.mvvmdemo.network.request.LoginRequest;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ohos.aafwk.abilityjet.activedata.ActiveData;

public class LoginViewModel extends BaseViewModel<LoginViewState, LoginRepo> {

    private static final int MIN_PASSWORD_LENGTH = 6;

    public ActiveData<Boolean> emailValid = new ActiveData<>();
    public ActiveData<Boolean> passwordValid = new ActiveData<>();
    public ActiveData<Boolean> loginState = new ActiveData<>();

    public LoginViewModel() {
        super(new LoginRepo());
    }

    public void login(String email, String password) {

        boolean isEmailValid = isEmailValid(email);
        emailValid.setData(isEmailValid);
        if (!isEmailValid)
            return;

        boolean isPasswordValid = isPasswordValid(email);
        passwordValid.setData(isPasswordValid);
        if (!isPasswordValid)
            return;

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        super.subscribe(sendLoginRequest(loginRequest));
    }

    private Observable<LoginViewState> sendLoginRequest(LoginRequest loginRequest) {
        return MyApplication.createRetrofitClient()
                .login(loginRequest)
                .doOnError(Throwable::printStackTrace)
                .map(LoginViewState.LoggedIn::new)
                .cast(LoginViewState.class)
                .onErrorReturn(throwable -> {
                    ErrorData errorData = new ErrorData();
                    if (throwable.getMessage() != null)
                        errorData.setMessage(throwable.getMessage());
                    else
                        errorData.setMessage(" No internet! ");
                    return new LoginViewState.Error(errorData);
                })
                .subscribeOn(Schedulers.io())
                .startWith(new LoginViewState.Loading());
    }

    private boolean isEmailValid(String email) {
        return email != null && !email.isEmpty() && email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.length() > MIN_PASSWORD_LENGTH;
    }
}
