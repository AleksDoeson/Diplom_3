package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class ConstructorPage {

    // XPath вкладок (точки были взяты из вашего сообщения)
    private final SelenideElement bunsTab     = $x("//*[@id='root']/div/main/section[1]/div[1]/div[1]");
    private final SelenideElement saucesTab   = $x("//*[@id='root']/div/main/section[1]/div[1]/div[2]");
    private final SelenideElement fillingsTab = $x("//*[@id='root']/div/main/section[1]/div[1]/div[3]");


    @Step("Переключиться на вкладку 'Булки'")
    public void switchToBuns() {
        bunsTab.scrollIntoView(true).shouldBe(visible, enabled).click();
        // Ожидаем, что в атрибуте class элемента bunsTab появится подстрока "tab_tab_type_current"
        bunsTab.shouldHave(attributeMatching("class", ".*tab_tab_type_current.*"));
    }

    @Step("Переключиться на вкладку 'Соусы'")
    public void switchToSauces() {
        saucesTab.scrollIntoView(true).shouldBe(visible, enabled).click();
        saucesTab.shouldHave(attributeMatching("class", ".*tab_tab_type_current.*"));
    }

    @Step("Переключиться на вкладку 'Начинки'")
    public void switchToFillings() {
        fillingsTab.scrollIntoView(true).shouldBe(visible, enabled).click();
        fillingsTab.shouldHave(attributeMatching("class", ".*tab_tab_type_current.*"));
    }

    @Step("Проверить, что вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        return Objects.requireNonNull(bunsTab.getAttribute("class")).matches(".*tab_tab_type_current.*");
    }

    @Step("Проверить, что вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        return Objects.requireNonNull(saucesTab.getAttribute("class")).matches(".*tab_tab_type_current.*");
    }

    @Step("Проверить, что вкладка 'Начинки' активна")
    public boolean isFillingsTabActive() {
        return Objects.requireNonNull(fillingsTab.getAttribute("class")).matches(".*tab_tab_type_current.*");
    }
}









