package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {

    public final SelenideElement nameInput = $x("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");
    private final SelenideElement emailInput = $x("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");
    private final SelenideElement passwordInput = $x("//*[@id='root']/div/main/div/form/fieldset[3]/div/div/input");
    private final SelenideElement registerButton = $x("//*[@id='root']/div/main/div/form/button");
    private final SelenideElement errorMessage = $x("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");

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

