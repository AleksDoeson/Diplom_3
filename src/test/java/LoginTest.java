import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import Utils.ApiUtils;
import Utils.BrowserProvider;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class LoginTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    private String email;
    private final String password = "ValidPass123";
    private String accessToken;

    @BeforeClass
    public static void setupClass() {
        BrowserProvider.configureSelenide("chrome"); // или "yandex"
    }

    @Before
    public void setup() {
        open("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage();
        loginPage = new LoginPage();
        registrationPage = new RegistrationPage();

        email = "logintest" + System.currentTimeMillis() + "@mail.ru";
        String name = "Login Test User";
        accessToken = ApiUtils.createUser(email, password, name);
    }

    @After
    public void teardown() {
        closeWebDriver();
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Test
    @io.qameta.allure.Step("Успешный вход с валидными данными")
    public void testSuccessfulLogin() {
        mainPage.clickLogin();
        loginPage.login(email, password);

        assertTrue(mainPage.profileButton.exists());
    }

    @Test
    @io.qameta.allure.Step("Переход на страницу восстановления пароля по ссылке")
    public void testGoToForgotPasswordPage() {
        mainPage.clickLogin();
        loginPage.clickForgotPasswordLink();
        assertTrue(WebDriverRunner.url().contains("/forgot-password"));
        loginPage.clickLoginFromForgottenPasswordLink();
        loginPage.login(email, password);

        assertTrue(mainPage.profileButton.exists());

    }



    @Test
    @io.qameta.allure.Step("Переход на страницу регистрации по ссылке")
    public void testGoToRegistrationPage() {
        mainPage.clickLogin();
        loginPage.clickRegisterLink();

        assertTrue(registrationPage.nameInput.exists());
        loginPage.clickLoginFromRegisterLink();
        loginPage.login(email, password);

        assertTrue(mainPage.profileButton.exists());
    }
    @Test
    @io.qameta.allure.Step("Успешный вход по кнопке Личный Кабинет")
    public void testSuccessfulProfileLogin() {
        mainPage.clickProfile();
        loginPage.login(email, password);

        assertTrue(mainPage.profileButton.exists());
    }
}



