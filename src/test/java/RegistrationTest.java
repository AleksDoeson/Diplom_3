import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.*;
import pageobject.LoginPage;
import pageobject.RegistrationPage;
import utils.ApiUtils;
import utils.BrowserProvider;
import utils.UserModel;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;
import static pageobject.MainPage.BASE_URL_REGISTER;

public class RegistrationTest {

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
        open(BASE_URL_REGISTER);
        registrationPage = new RegistrationPage();

        String email = faker.internet().emailAddress();
        String name = faker.name().fullName();
        String password = faker.internet().password(8, 16);

        testUser = new UserModel(email, password, name);
        accessToken = null;
    }

    @After
    public void teardown() {
        closeWebDriver();
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void testSuccessfulRegistration() {
        registrationPage.register(testUser.getName(), testUser.getEmail(), testUser.getPassword());
        LoginPage.loginButton.shouldBe(visible, Duration.ofSeconds(10));

        // Получаем accessToken через API логина
        accessToken = ApiUtils.loginUser(testUser.getEmail(), testUser.getPassword());
        assertNotNull("AccessToken не должен быть null после логина", accessToken);
    }

    @Test
    @DisplayName("Ошибка при регистрации: слишком короткий пароль (< 6 символов)")
    public void testRegistrationWithShortPassword() {
        String shortPassword = "12345";
        registrationPage.register(testUser.getName(), testUser.getEmail(), shortPassword);
        String actualError = registrationPage.getErrorMessage();
        assertEquals("Некорректный пароль", actualError);
    }
}





