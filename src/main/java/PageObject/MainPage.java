package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement loginButton = $x("//button[text()='Войти в аккаунт']");
    public final SelenideElement profileButton = $x("//*[@id='root']/div/header/nav/a/p");
    private final SelenideElement constructorButton = $x("//*[@id='root']/div/header/nav/ul/li[1]/a/p");

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

