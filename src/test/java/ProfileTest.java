import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.*;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;
import utils.ApiUtils;
import utils.BrowserProvider;
import utils.UserModel;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;
import static pageobject.MainPage.BASE_URL;

public class ProfileTest {

    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;

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
        profilePage = new ProfilePage();

        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16);
        String name = faker.name().fullName();

        testUser = new UserModel(email, password, name);
        accessToken = ApiUtils.createUser(testUser);

        mainPage.clickLogin();
        loginPage.login(testUser.getEmail(), testUser.getPassword());
    }

    @After
    public void teardown() {
        closeWebDriver();
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Проверка, что пользователь видит профиль после входа")
    public void testProfileHeaderIsVisible() {
        mainPage.clickProfile();
        assertTrue(
                "Заголовок профиля должен отображаться после входа",
                profilePage.isProfileHeaderVisible("Профиль")
        );
    }

    @Test
    @DisplayName("Проверка выхода из профиля и появления страницы Авторизации")
    public void testLogout() {
        mainPage.clickProfile();
        profilePage.logout();

        assertTrue(
                "После выхода должен отображаться заголовок логина",
                profilePage.isLoginHeaderVisible()
        );
    }

    @Test
    @DisplayName("Проверка перехода в конструктор из профиля по кнопке 'Конструктор'")
    public void testGoToConstructorFromProfile() {
        mainPage.clickProfile();
        profilePage.goToConstructor();

        assertTrue(
                "После клика по 'Конструктор' должен отображаться заголовок конструктора",
                profilePage.isConstructorHeaderVisible()
        );
    }

    @Test
    @DisplayName("Проверка перехода в конструктор из профиля по клику на логотип Stellar Burgers")
    public void testGoToConstructorFromProfileByLogo() {
        mainPage.clickProfile();
        profilePage.clickLogo();

        assertTrue(
                "После клика по логотипу должен отображаться заголовок конструктора",
                profilePage.isConstructorHeaderVisible()
        );
    }
}






