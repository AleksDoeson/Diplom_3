import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import pageobject.ConstructorPage;
import pageobject.MainPage;
import utils.BrowserProvider;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.Assert.*;
import static pageobject.MainPage.BASE_URL;

public class ConstructorTests {

    private ConstructorPage constructorPage;

    @BeforeClass
    public static void setupClass() {
        BrowserProvider.configureSelenide("yandex");
    }

    @Before
    public void setup() {
        open(BASE_URL);
        MainPage mainPage = new MainPage();
        constructorPage = new ConstructorPage();
        mainPage.clickConstructor();
    }

    @After
    public void teardown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Проверка, что вкладка 'Булки' активна по умолчанию")
    public void testBunsTabIsActiveByDefault() {
        assertTrue(
                "Вкладка 'Булки' должна быть активна по умолчанию",
                constructorPage.isBunsTabActive()
        );
    }

    @Test
    @DisplayName("Проверка активации вкладки 'Соусы'")
    public void testSaucesTabIsActive() {
        constructorPage.switchToSauces();
        assertTrue(
                "Вкладка 'Соусы' должна быть активна после клика",
                constructorPage.isSaucesTabActive()
        );
    }

    @Test
    @DisplayName("Проверка активации вкладки 'Начинки'")
    public void testFillingsTabIsActive() {
        constructorPage.switchToFillings();
        assertTrue(
                "Вкладка 'Начинки' должна быть активна после клика",
                constructorPage.isFillingsTabActive()
        );
    }

    @Test
    @DisplayName("Проверка переключения с вкладки 'Начинки' на вкладку 'Булки'")
    public void testSwitchFromFillingsToBuns() {
        constructorPage.switchToFillings();
        assertTrue(
                "Вкладка 'Начинки' должна быть активна после клика",
                constructorPage.isFillingsTabActive()
        );

        constructorPage.switchToBuns();
        assertTrue(
                "После перехода с 'Начинки' на 'Булки' вкладка 'Булки' должна быть активна",
                constructorPage.isBunsTabActive()
        );
    }
}















