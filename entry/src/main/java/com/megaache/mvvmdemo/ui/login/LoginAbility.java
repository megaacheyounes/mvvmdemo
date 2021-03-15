package com.megaache.mvvmdemo.ui.login;

import com.megaache.mvvmdemo.ResourceTable;
import com.megaache.mvvmdemo.utils.UiObserver;
import com.megaache.mvvmdemo.model.ErrorData;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

public class LoginAbility extends Ability {

    private LoginViewModel loginViewModel;

    private TextField emailTF;
    private Text emailInvalidT;

    private TextField passwordTF;
    private Text passwordInvalidT;


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        loginViewModel = new LoginViewModel();

        initUI();
        observeData();
    }

    private void initUI() {
        super.setUIContent(ResourceTable.Layout_ability_login);

        Button loginButton = (Button) findComponentById(ResourceTable.Id_btn_login);
        loginButton.setClickedListener(c -> attemptLogin());

        emailTF = (TextField) findComponentById(ResourceTable.Id_tf_email);
        emailInvalidT = (Text) findComponentById(ResourceTable.Id_t_email_invalid);

        passwordTF = (TextField) findComponentById(ResourceTable.Id_tf_password);
        passwordInvalidT = (Text) findComponentById(ResourceTable.Id_t_password_invalid);

    }

    private void observeData() {
        loginViewModel.emailValid.addObserver(new UiObserver<Boolean>(this) {
            @Override
            public void onValueChanged(Boolean aBoolean) {
                emailInvalidT.setVisibility(aBoolean ? Component.VISIBLE : Component.HIDE);
            }
        }, false);

        loginViewModel.passwordValid.addObserver(new UiObserver<Boolean>(this) {
            @Override
            public void onValueChanged(Boolean aBoolean) {
                passwordInvalidT.setVisibility(aBoolean ? Component.VISIBLE : Component.HIDE);
            }
        }, false);

        loginViewModel.getStates().addObserver(new UiObserver<LoginViewState>(this) {
            @Override
            public void onValueChanged(LoginViewState loginState) {
                if (loginState instanceof LoginViewState.Loading) {
                    toggleLoadingDialog(true);
                } else if (loginState instanceof LoginViewState.Error) {
                    toggleLoadingDialog(false);
                    manageError(((LoginViewState.Error) loginState).getMessage());
                } else if (loginState instanceof LoginViewState.LoggedIn) {
                    toggleLoadingDialog(false);
                    showToast("logging successful!");
                }
            }
        }, false);
    }

    private void attemptLogin() {
        loginViewModel.login(emailTF.getText(), passwordTF.getText());
    }

    private void toggleLoadingDialog(boolean show) {
        //todo: show/hide loading dialog
    }

    private void manageError(ErrorData errorData) {
        showToast(errorData.getMessage());
    }

    private void showToast(String message) {
        new ToastDialog(this)
                .setText(message)
                .show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginViewModel.unbind();
    }
}
