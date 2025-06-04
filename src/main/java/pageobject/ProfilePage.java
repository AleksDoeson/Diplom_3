package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class ProfilePage {


    // Кнопка «Выход» — кнопка с текстом 'Выход' в nav
    private final SelenideElement logoutButton = $x("//nav//button[text()='Выход']");

    // Ссылка «Конструктор» — <p> с текстом 'Конструктор' внутри header/nav
    private final SelenideElement constructorLink = $x("//header//nav//p[text()='Конструктор']");

    // Логотип «Stellar Burgers» — div внутри header с классом, содержащим 'AppHeader_header__logo'
    private final SelenideElement logoBurger = $x("//header//div[contains(@class, 'AppHeader_header__logo')]");

    // Заголовок «Профиль» — <a> с текстом 'Профиль' или h1 с текстом 'Профиль' в main
    private final SelenideElement profileHeader = $x("//main//a[contains(text(), 'Профиль')] | //main//h1[contains(text(), 'Профиль')]");

    // Заголовок «Конструктор» — h1 с текстом 'Соберите бургер' или 'Конструктор' в main
    private final SelenideElement constructorHeader = $x("//main//h1[contains(text(),'Соберите бургер') or contains(text(),'Конструктор')]");

    // Заголовок «Вход» — h2 с текстом 'Вход' в main
    private final SelenideElement loginHeader = $x("//main//h2[contains(text(),'Вход')]");


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
        return profileHeader.shouldBe(visible).getText().equals(expectedText);
    }

    @Step("Проверить наличие заголовка конструктора на странице")
    public boolean isConstructorHeaderVisible() {
        return constructorHeader.shouldBe(visible).exists();
    }

    @Step("Проверить наличие заголовка Входа на странице")
    public boolean isLoginHeaderVisible() {
        return loginHeader.shouldBe(visible).exists();
    }
}




