import io.qameta.allure.Step;
import org.junit.*;
import PageObject.ConstructorPage;
import PageObject.MainPage;
import Utils.BrowserProvider;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.Assert.*;

public class ConstructorTests {

    private ConstructorPage constructorPage;

    @BeforeClass
    public static void setupClass() {
        BrowserProvider.configureSelenide("chrome"); // или "yandex"
    }

    @Before
    public void setup() {
        // Открываем главную страницу Stellar Burgers
        open("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage();
        constructorPage = new ConstructorPage();
        // Переходим в Конструктор (по логотипу/кнопке "Конструктор")
        mainPage.clickConstructor();
    }

    @After
    public void teardown() {
        closeWebDriver();
    }

    @Test
    @Step("Проверка, что вкладка 'Булки' активна по умолчанию")
    public void testBunsTabIsActiveByDefault() {
        assertTrue(
                "Вкладка 'Булки' должна быть активна по умолчанию",
                constructorPage.isBunsTabActive()
        );
    }

    @Test
    @Step("Проверка активации вкладки 'Соусы'")
    public void testSaucesTabIsActive() {
        constructorPage.switchToSauces();
        assertTrue(
                "Вкладка 'Соусы' должна быть активна после клика",
                constructorPage.isSaucesTabActive()
        );
    }

    @Test
    @Step("Проверка активации вкладки 'Начинки'")
    public void testFillingsTabIsActive() {
        constructorPage.switchToFillings();
        assertTrue(
                "Вкладка 'Начинки' должна быть активна после клика",
                constructorPage.isFillingsTabActive()
        );
    }

    @Test
    @Step("Проверка переключения с вкладки 'Начинки' на вкладку 'Булки'")
    public void testSwitchFromFillingsToBuns() {
        // Сначала переключаемся на "Начинки" и убеждаемся, что они активны
        constructorPage.switchToFillings();
        assertTrue(
                "Вкладка 'Начинки' должна быть активна после клика",
                constructorPage.isFillingsTabActive()
        );

        // Затем переключаемся на "Булки" и проверяем, что они снова активны
        constructorPage.switchToBuns();
        assertTrue(
                "После перехода с 'Начинки' на 'Булки' вкладка 'Булки' должна быть активна",
                constructorPage.isBunsTabActive()
        );
    }
}














