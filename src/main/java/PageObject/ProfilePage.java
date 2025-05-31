package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object для страницы «Профиль» Stellar Burgers.
 */
public class ProfilePage {

    // Кнопка «Выйти» в навигации профиля
    private final SelenideElement logoutButton = $x("//*[@id='root']/div/main/div/nav/ul/li[3]/button");

    // Ссылка «Конструктор» в шапке (header)
    private final SelenideElement constructorLink = $x("//*[@id='root']/div/header/nav/ul/li[1]/a/p");

    // Логотип «Stellar Burgers» в шапке
    private final SelenideElement logoBurger = $x("//*[@id='root']/div/header/nav/div");

    // Заголовок «Профиль» на странице
    private final SelenideElement profileHeader = $x("//*[@id='root']/div/main/div/nav/ul/li[1]/a");
    private final SelenideElement constructorHeader = $x("//*[@id='root']/div/main/section[1]/h1");
    private final SelenideElement loginHeader = $x("//*[@id='root']/div/main/div/h2");

    @Step("Выйти из аккаунта")
    public void logout() {
        logoutButton.scrollIntoView(true).shouldBe(visible).click();
    }

    @Step("Перейти в конструктор по ссылке в шапке")
    public void goToConstructor() {
        constructorLink.scrollIntoView(true).shouldBe(visible).click();
    }

    @Step("Перейти в конструктор по клику на логотип")
    public void clickLogo() {
        logoBurger.scrollIntoView(true).shouldBe(visible).click();
    }

    @Step("Проверить, что заголовок профиля отображается: {expectedText}")
    public boolean isProfileHeaderVisible(String expectedText) {
        // Ждём, пока заголовок «Профиль» станет видимым, и проверяем текст
        return profileHeader.shouldBe(visible).getText().equals(expectedText);
    }
    @Step("Проверить наличие заголовка конструктора на странице")
    public boolean isConstructorHeaderVisible() {
        // Ждём, пока заголовок станет видимым
        return constructorHeader.shouldBe(visible).exists();
    }
    @Step("Проверить наличие заголовка Вход после на странице Входа")
    public boolean isLoginHeaderVisible() {
        // Ждём, пока заголовок станет видимым
        return loginHeader.shouldBe(visible).exists();
    }
}



