import io.qameta.allure.Step;
import org.junit.*;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.ProfilePage;
import Utils.ApiUtils;
import Utils.BrowserProvider;

import static com.codeborne.selenide.Selenide.*;

public class ProfileTest {

    private MainPage mainPage;
    private ProfilePage profilePage;

    private final String testEmail = "testprofile" + System.currentTimeMillis() + "@mail.ru";
    private String accessToken;

    @BeforeClass
    public static void setupClass() {
        BrowserProvider.configureSelenide("chrome");
    }

    @Before
    public void setup() {
        open("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage();
        LoginPage loginPage = new LoginPage();
        profilePage = new ProfilePage();

        String testPassword = "Password123!";
        String testName = "Test ProfileUser";
        accessToken = ApiUtils.createUser(testEmail, testPassword, testName);

        mainPage.clickLogin();
        loginPage.login(testEmail, testPassword);
    }

    @After
    public void teardown() {
        closeWebDriver();
        if (accessToken != null) {
            ApiUtils.deleteUser(accessToken);
        }
    }

    @Test
    @Step("Проверка, что пользователь видит профиль после входа")
    public void testProfileHeaderIsVisible() {
        mainPage.clickProfile();
        Assert.assertTrue(
                "Заголовок профиля должен отображаться после входа",
                profilePage.isProfileHeaderVisible("Профиль")
        );
    }

    @Test
    @Step("Проверка выхода из профиля и появления страницы Авторизации")
    public void testLogout() {
        mainPage.clickProfile();
        profilePage.logout();

        Assert.assertTrue(
                "После выхода должен отображаться заголовок конструктора",
                profilePage.isLoginHeaderVisible()
        );
    }

    @Test
    @Step("Проверка перехода в конструктор из профиля по кнопке 'Конструктор'")
    public void testGoToConstructorFromProfile() {
        mainPage.clickProfile();
        profilePage.goToConstructor();

        Assert.assertTrue(
                "После клика по 'Конструктор' должен отображаться заголовок конструктора",
                profilePage.isConstructorHeaderVisible()
        );
    }

    @Test
    @Step("Проверка перехода в конструктор из профиля по клику на логотип Stellar Burgers")
    public void testGoToConstructorFromProfileByLogo() {
        mainPage.clickProfile();
        profilePage.clickLogo();

        Assert.assertTrue(
                "После клика по логотипу должен отображаться заголовок конструктора",
                profilePage.isConstructorHeaderVisible()
        );
    }
}



