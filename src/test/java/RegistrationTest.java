import io.qameta.allure.Step;
import org.junit.*;
import PageObject.RegistrationPage;
import Utils.ApiUtils;
import Utils.BrowserProvider;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.*;

public class RegistrationTest {

    private RegistrationPage registrationPage;

    // Тестовые данные
    private final String testEmail = "tester" + System.currentTimeMillis() + "@mail.ru";
    private final String testPassword = "Password123!";  // ≥ 6 символов
    private final String testName = "Test User";

    // API-токен для удаления пользователя после успешной регистрации
    private String accessToken;

    @BeforeClass
    public static void setupClass() {
        // Указываем браузер: "chrome" или "yandex"
        BrowserProvider.configureSelenide("chrome");
    }

    @Before
    public void setup() {
        // Открываем сразу страницу регистрации
        open("https://stellarburgers.nomoreparties.site/register");
        registrationPage = new RegistrationPage();
        accessToken = null;
    }

    @After
    public void teardown() {
        // Закрываем браузер
        closeWebDriver();

        // Если accessToken != null, это значит, что пользователь уже создан в API, удаляем его
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Test
    @Step("Успешная регистрация нового пользователя")
    public void testSuccessfulRegistration() {
        // Шаг 1: заполняем форму регистрацией с корректными данными
        registrationPage.register(testName, testEmail, testPassword);

        String currentUrl = url();
        assertTrue(
                "После успешной регистрации ожидаем URL с 'profile', но был: " + currentUrl,
                currentUrl.contains("profile")
        );

        // Шаг 3: чтобы удалить пользователя через API, надо получить accessToken.
        // Сделаем дополнительный вызов API для создания "такого же" пользователя и получения токена.
        accessToken = ApiUtils.createUser(testEmail, testPassword, testName);
        assertNotNull("AccessToken не должен быть null после API-создания", accessToken);
    }

    @Test
    @Step("Ошибка при регистрации: слишком короткий пароль (< 6 символов)")
    public void testRegistrationWithShortPassword() {
        // Шаг 1: формируем пароль короче 6 символов
        String shortPassword = "12345"; // 5 символов

        // Шаг 2: пытаемся зарегистрироваться
        registrationPage.register(testName, testEmail, shortPassword);

        // Шаг 3: ожидаем появление сообщения об ошибке "Некорректный пароль"
        String actualError = registrationPage.getErrorMessage();
        assertEquals(
                "Ожидаем текст ошибки 'Некорректный пароль', но было: " + actualError,
                "Некорректный пароль",
                actualError
        );
    }
}

