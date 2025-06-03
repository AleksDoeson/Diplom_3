package pageobject;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ConstructorPage {

    private final SelenideElement bunsTab = $x("//div[contains(@class, 'tab')]/span[text()='Булки']/..");
    private final SelenideElement saucesTab = $x("//div[contains(@class, 'tab')]/span[text()='Соусы']/..");
    private final SelenideElement fillingsTab = $x("//div[contains(@class, 'tab')]/span[text()='Начинки']/..");

    @Step("Переключиться на вкладку 'Булки'")
    public void switchToBuns() {
        System.out.println("Кликаем по вкладке 'Булки'");
        bunsTab.scrollIntoView(true).shouldBe(visible, enabled).click();

        String screenshotName = screenshot("after_click_buns");
        System.out.println("Скриншот сделан: " + screenshotName);

        SelenideElement buns = $x("//div[contains(@class, 'tab')]/span[text()='Булки']/..");
        buns.shouldHave(attributeMatching("class", ".*tab_tab_type_current.*"), Duration.ofSeconds(5));
        System.out.println("Вкладка 'Булки' активна");
    }

    @Step("Переключиться на вкладку 'Соусы'")
    public void switchToSauces() {
        System.out.println("Кликаем по вкладке 'Соусы'");
        saucesTab.scrollIntoView(true).shouldBe(visible, enabled).click();

        String screenshotName = screenshot("after_click_sauces");
        System.out.println("Скриншот сделан: " + screenshotName);

        SelenideElement sauces = $x("//div[contains(@class, 'tab')]/span[text()='Соусы']/..");
        sauces.shouldHave(attributeMatching("class", ".*tab_tab_type_current.*"), Duration.ofSeconds(5));
        System.out.println("Вкладка 'Соусы' активна");
    }

    @Step("Переключиться на вкладку 'Начинки'")
    public void switchToFillings() {
        System.out.println("Кликаем по вкладке 'Начинки'");
        fillingsTab.scrollIntoView(true).shouldBe(visible, enabled).click();

        String screenshotName = screenshot("after_click_fillings");
        System.out.println("Скриншот сделан: " + screenshotName);

        SelenideElement fillings = $x("//div[contains(@class, 'tab')]/span[text()='Начинки']/..");
        fillings.shouldHave(attributeMatching("class", ".*tab_tab_type_current.*"), Duration.ofSeconds(5));
        System.out.println("Вкладка 'Начинки' активна");
    }

    @Step("Проверить, что вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        return Objects.requireNonNull(bunsTab.getAttribute("class")).contains("tab_tab_type_current");
    }

    @Step("Проверить, что вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        return Objects.requireNonNull(saucesTab.getAttribute("class")).contains("tab_tab_type_current");
    }

    @Step("Проверить, что вкладка 'Начинки' активна")
    public boolean isFillingsTabActive() {
        return Objects.requireNonNull(fillingsTab.getAttribute("class")).contains("tab_tab_type_current");
    }
}













