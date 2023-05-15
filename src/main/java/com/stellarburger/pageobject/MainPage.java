package com.stellarburger.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class MainPage {
    private final WebDriver driver;
    private final static String mainPage = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
            this.driver = driver;
        }
    //  локатор для кнопки Войти
    private final By authorizationButton = By.xpath(".//*[text() = 'Войти в аккаунт']");
    //  локатор для кнопки личный кабинет
    private final By userAccountButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    //  локаторы для соуса
    private final By ingredientSauceButton = By.xpath(".//*[text() = 'Соусы']");
    //  локаторы для булки
    private final By ingredientBunsButton =  By.xpath(".//*[text() = 'Булки']");
    // локаторы для начинки
    private final By ingredientFillingButton = By.xpath(".//*[text() = 'Начинки']");

    // локаторы для кнопки "Оформить заказ"
    private final By orderButton = By.className("button_button__33qZ0");
    // локаторы для кнопки Активной ссылки
    private final By activeLink = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");

    @Step("open main Page")
    public void openMainPage (){
        driver.get(mainPage);
    }

    // 	вход по кнопке «Войти в аккаунт» на главной
    @Step("Find, Check And Click Authorization Button on mainPage")
    public void clickAuthorizationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(authorizationButton));
        Object elementAuthorizationButton = driver.findElement(authorizationButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementAuthorizationButton);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(authorizationButton));
        driver.findElement(authorizationButton).click();
    }

    // 	вход в личный кабинет
    @Step("Click Personal Account Button on MainPage")
    public void clickUserAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(userAccountButton));
        driver.findElement(userAccountButton).click();
    }

    // загрузка стартовой страницы после авторизации и наличие кнопки "Оформить заказ"
    @Step("Get text from Order Button on MainPage")
    public Object textOrderButton () {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(orderButton));
        return textButton.getText();
    }


    @Step("Find And Click SauceLink on mainPage, check Sauce Element")
    public boolean checkSauceLinkDisplayed() {
        return driver.findElement(activeLink).getText().equals("Соусы");
    }

    // проверка видимости заголовка Соуса
    @Step("Click SauceLink on mainPage, check Sauce Element")
    public MainPage clickSauceLink() {
        driver.findElement(ingredientSauceButton).click();
        return this;
    }

    // проверка видимости заголовка Начинки
    @Step("Click StuffingLink on mainPage, check Stuffing Element")
    public MainPage clickFillingsLink() {
        driver.findElement(ingredientFillingButton).click();
        return this;
    }
    @Step("Find StuffingLink on mainPage, check Stuffing Element")
    public boolean checkStuffingLinkDisplayed() {
        return driver.findElement(activeLink).getText().equals("Начинки");
    }


    // проверка видимости заголовка Булки
    @Step("Click BunsLink on mainPage, check Buns Element")
    public MainPage clickBunsLink() {
        driver.findElement(ingredientBunsButton).click();
        return this;
    }
    @Step("Find BunsLink on mainPage, check Buns Element")
    public boolean checkBunsLinkDisplayed() {
        return driver.findElement(activeLink).getText().equals("Булки");
    }
}

