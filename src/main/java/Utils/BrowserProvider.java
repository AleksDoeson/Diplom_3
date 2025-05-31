package Utils;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserProvider {

    public static void configureSelenide(String browser) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");  // Запускать окно максимизированием

        if ("yandex".equalsIgnoreCase(browser)) {
            options.setBinary("C:\\Users\\aleks\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        }

        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";

        Configuration.timeout = 10000;
        Configuration.headless = false;

    }
}


