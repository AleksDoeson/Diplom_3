package utils;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserProvider {

    public static void configureSelenide(String browser) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        if ("yandex".equalsIgnoreCase(browser)) {
            // Драйвер для Яндекс браузера версии 134
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin2\\chromedriver.exe");
            options.setBinary("C:\\Users\\aleks\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            Configuration.browser = "chrome";
        } else if ("chrome".equalsIgnoreCase(browser)) {
            // Драйвер для Chrome версии 136
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\bin\\chromedriver.exe");

            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            Configuration.browser = "chrome";
        } else {
            throw new IllegalArgumentException("❌ Поддерживаются только браузеры: chrome или yandex");
        }

        Configuration.browserCapabilities = options;
        Configuration.timeout = 10000;
        Configuration.headless = false;
    }
}






