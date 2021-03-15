package com.megaache.mvvmdemo.ui.login;

import com.megaache.mvvmdemo.base.BaseViewState;
import com.megaache.mvvmdemo.model.ErrorData;
import com.megaache.mvvmdemo.network.response.LoginResponse;

public class LoginViewState extends BaseViewState {
    public static class Loading extends LoginViewState {
    }

    public static class Error extends LoginViewState {
        private ErrorData message;

        public Error(ErrorData message) {
            this.message = message;
        }

        public void setMessage(ErrorData message) {
            this.message = message;
        }

        public ErrorData getMessage() {
            return message;
        }
    }

    public static class LoggedIn extends LoginViewState {
        private LoginResponse userDataResponse;

        public LoggedIn(LoginResponse userDataResponse) {
            this.userDataResponse = userDataResponse;
        }

        public LoginResponse getUserDataResponse() {
            return userDataResponse;
        }

        public void setUserDataResponse(LoginResponse userDataResponse) {
            this.userDataResponse = userDataResponse;
        }
    }

}
