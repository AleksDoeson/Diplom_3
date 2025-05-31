package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement emailInput = $x("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");
    private final SelenideElement passwordInput = $x("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");
    private final SelenideElement loginButton = $x("//*[@id='root']/div/main/div/form/button");
    private final SelenideElement registerLink = $x("//*[@id='root']/div/main/div/div/p[1]/a");
    private final SelenideElement forgotPasswordLink = $x("//*[@id='root']/div/main/div/div/p[2]/a");
    private final SelenideElement loginFromForgottenPasswordLink = $x("//*[@id='root']/div/main/div/div/p/a");
    private final SelenideElement loginFromRegisterLink = $x("//*[@id='root']/div/main/div/div/p/a");

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
    @Step("Клик по второй кнопке входа")
    public void clickLoginFromForgottenPasswordLink() {
        loginFromForgottenPasswordLink.click();
    }
    @Step("Клик по второй кнопке входа")
    public void clickLoginFromRegisterLink() {
        loginFromRegisterLink.click();
    }
}

