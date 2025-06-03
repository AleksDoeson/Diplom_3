import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.*;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import utils.ApiUtils;
import utils.BrowserProvider;
import utils.UserModel;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;
import static pageobject.MainPage.BASE_URL;

public class LoginTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    private UserModel testUser;
    private String accessToken;
    private static Faker faker;

    @BeforeClass
    public static void setupClass() {
        BrowserProvider.configureSelenide("yandex");
        faker = new Faker();
    }

    @Before
    public void setup() {
        open(BASE_URL);
        mainPage = new MainPage();
        loginPage = new LoginPage();
        registrationPage = new RegistrationPage();

        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16);
        String name = faker.name().fullName();

        testUser = new UserModel(email, password, name);
        accessToken = ApiUtils.createUser(testUser);
    }

    @After
    public void teardown() {
        closeWebDriver();
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешный вход с валидными данными")
    public void testSuccessfulLogin() {
        mainPage.clickLogin();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.profileButton.exists());
    }

    @Test
    @DisplayName("Переход на страницу восстановления пароля по ссылке")
    public void testGoToForgotPasswordPage() {
        mainPage.clickLogin();
        loginPage.clickForgotPasswordLink();
        assertTrue(WebDriverRunner.url().contains("/forgot-password"));
        loginPage.clickLoginFromForgottenPasswordLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.profileButton.exists());
    }

    @Test
    @DisplayName("Переход на страницу регистрации по ссылке")
    public void testGoToRegistrationPage() {
        mainPage.clickLogin();
        loginPage.clickRegisterLink();
        assertTrue(registrationPage.nameInput.exists());
        loginPage.clickLoginFromRegisterLink();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.profileButton.exists());
    }

    @Test
    @DisplayName("Успешный вход по кнопке Личный Кабинет")
    public void testSuccessfulProfileLogin() {
        mainPage.clickProfile();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
        assertTrue(mainPage.profileButton.exists());
    }
}






