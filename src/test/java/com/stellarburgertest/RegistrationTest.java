package com.stellarburgertest;
import com.stellarburger.User;
import io.qameta.allure.junit4.DisplayName;
import com.stellarburger.pageobject.AuthorizationPage;
import com.stellarburger.pageobject.MainPage;
import com.stellarburger.pageobject.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
    private RegistrationPage objRegistrationPage;
    private AuthorizationPage objAuthorizationPage;
    private WebDriver driver;
    private String name;
    private String email;
    private String password;


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
        User user = new User();
        name = user.getRandomName();
        email = user.getRandomEmail();
        password = user.getRandomPassword();
        objRegistrationPage = new RegistrationPage(driver); // создание объекта класса страницы регистрации
    }

    @Test
    @DisplayName("check Registration - creating user")
    public void checkRegistrationTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        objRegistrationPage.openRegPage();
        objRegistrationPage.createNewUser(name,email,password);
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email, password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Registration was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("check Registration - check Error Message")
    public void checkErrorMassageTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        objRegistrationPage.openRegPage();
        objRegistrationPage.createNewUser(name,email,"12345");
        objRegistrationPage.getErrorMessage();
        assertEquals("Registration was Failed", "Некорректный пароль", objRegistrationPage.getTextErrorMessage());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

