package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String BASE_URL_REGISTER = "https://stellarburgers.nomoreparties.site/register";


    private final SelenideElement loginButton = $x("//main//section[2]//div//button[text()='Войти в аккаунт']");

    public final SelenideElement profileButton = $x("//a[contains(@class, 'AppHeader_header__link') and .//p[text()='Личный Кабинет']]");

    private final SelenideElement constructorButton = $x("//header//nav//p[text()='Конструктор']");


    @Step("Клик на кнопку Войти в аккаунт")
    public void clickLogin() {
        loginButton.click();
    }

    @Step("Клик на Личный кабинет")
    public void clickProfile() {
        profileButton.click();
    }

    @Step("Клик на Конструктор")
    public void clickConstructor() {
        constructorButton.click();
    }
}


