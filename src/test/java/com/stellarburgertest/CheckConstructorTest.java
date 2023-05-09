package com.stellarburgertest;

import io.qameta.allure.junit4.DisplayName;
import com.stellarburger.pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;

public class CheckConstructorTest {
    private MainPage objMainPage;
    private WebDriver driver;
    @Before
    public void before() {

        /*// тест для  Chrome Browser
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();*/

        // тест для Yandex Browser
        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\sta_l\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        objMainPage = new MainPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("check Constructor Sauce Link at MainPage")
    public void checkSauceLink() {
        objMainPage.openMainPage();
        assertTrue("Error in section links-Sauce", objMainPage.checkSauceLinkDisplayed());
    }

    @Test
    @DisplayName("check Constructor Buns Link at MainPage")
    public void checkBunsLink() {
        objMainPage.openMainPage();
        assertTrue("Error in section Links-Buns", objMainPage.checkBunsLinkDisplayed());
    }

    @Test
    @DisplayName("check Constructor Stuffing Link at MainPage")
    public void checkStuffingLink() {
        objMainPage.openMainPage();
        assertTrue("Error in section Links-Stuffing", objMainPage.checkStuffingLinkDisplayed());
    }
}
