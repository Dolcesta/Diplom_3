package com.stellarburgertest;

import com.stellarburger.User;
import io.qameta.allure.junit4.DisplayName;
import com.stellarburger.pageobject.AuthorizationPage;
import com.stellarburger.pageobject.MainPage;
import com.stellarburger.pageobject.RegistrationPage;
import com.stellarburger.pageobject.UserAccountPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class UserAccountPageTest {
    private RegistrationPage objRegistrationPage;
    private AuthorizationPage objAuthorizationPage;
    private MainPage objMainPage;
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
        objRegistrationPage = new RegistrationPage(driver);// создание объекта класса страницы регистрации
        objRegistrationPage.openRegPage();
        objRegistrationPage.createNewUser(name,email,password);
        objAuthorizationPage = new AuthorizationPage(driver);
        objMainPage = new MainPage(driver);// создание объекта класса main страницы
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("check enter in Personal Account")
    public void checkPersonalAccountLinkTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objUserAccountPage = new UserAccountPage(driver);
        assertEquals("Entering was  Failed", "Выход", objUserAccountPage.checkLogInPersonalAccount());
    }

    @Test
    @DisplayName("check exit from Account by Link at Personal Account")
    public void checkExitButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickExitButton();
        assertEquals("ExitFailed", "Войти", objAuthorizationPage.checkLoginButton());
    }

    @Test
    @DisplayName("check exit to MainPage by Logo at Personal Account")
    public void checkLogoButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickLogoButton();
        assertEquals("LogoButtonFailed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("check exit to MainPage by Constructor Button at Personal Account")
    public void checkConstructorButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickConstructorButton();
        assertEquals("ConstructorButtonFailed", "Оформить заказ", objMainPage.textOrderButton());
    }
}
