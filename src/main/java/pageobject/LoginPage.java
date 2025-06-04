package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement emailInput = $("input[type='text']");
    private final SelenideElement passwordInput = $("input[type='password']");
    public static final SelenideElement loginButton = $("button.button_button__33qZ0");
    private final SelenideElement registerLink = $("a[href='/register']");
    private final SelenideElement forgotPasswordLink = $("a[href='/forgot-password']");
    private final SelenideElement loginFromForgottenPasswordLink = $("a[href='/login']");
    private final SelenideElement loginFromRegisterLink = $("a[href='/login']");

    @Step("Ввод email: {email}")
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Нажать кнопку входа")
    public void clickLogin() {
        loginButton.click();
    }

    @Step("Вход с email: {email}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLogin();
    }
    @Step("Клик по ссылке регистрации")
    public void clickRegisterLink() {
        registerLink.click();
    }

    @Step("Клик по ссылке восстановления пароля")
    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }
    @Step("Клик по кнопке входа на странице восстановления пароля")
    public void clickLoginFromForgottenPasswordLink() {
        loginFromForgottenPasswordLink.click();
    }

    @Step("Клик по кнопке входа на странице регистрации")
    public void clickLoginFromRegisterLink() {
        loginFromRegisterLink.click();
    }
}


