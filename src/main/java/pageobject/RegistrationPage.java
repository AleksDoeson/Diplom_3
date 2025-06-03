package pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    // Инпут Имя — по атрибуту name='name' и классу
    public final SelenideElement nameInput = $x("//input[@name='name' and contains(@class, 'input__textfield')]");

    private final SelenideElement emailInput = $x("//label[contains(text(),'Email')]/following-sibling::input[contains(@class, 'input__textfield')]");

    // Инпут Пароль — по типу 'password' и классу
    private final SelenideElement passwordInput = $x("//input[@type='password' and contains(@class, 'input__textfield')]");

    // Кнопка Зарегистрироваться — кнопка с текстом 'Зарегистрироваться' и классом кнопки
    private final SelenideElement registerButton = $x("//button[contains(text(),'Зарегистрироваться') and contains(@class, 'button_button')]");

    // Сообщение об ошибке — <p> с классом 'input__error'
    private final SelenideElement errorMessage = $x("//p[contains(@class, 'input__error')]");

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        nameInput.shouldBe(visible).setValue(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        emailInput.shouldBe(visible).setValue(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password) {
        passwordInput.shouldBe(visible).setValue(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegister() {
        registerButton.shouldBe(visible).click();
    }

    @Step("Заполнить форму регистрации и отправить: name={name}, email={email}, password={password}")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegister();
    }

    @Step("Получить текст ошибки пароля")
    public String getErrorMessage() {
        return errorMessage.shouldBe(visible).getText();
    }
}


