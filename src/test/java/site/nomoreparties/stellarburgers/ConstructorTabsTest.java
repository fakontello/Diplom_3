package site.nomoreparties.stellarburgers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTabsTest {
    private MainPage openMainPage;
    String password = RandomStringUtils.randomAlphabetic(6);
    String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";


    @Before
    public void preconditions() {
        openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        RegistrationPage newRegistration = new RegistrationPage();
        LoginPage loginPage = new LoginPage();
        PrivateOfficePage privateOfficePage = new PrivateOfficePage();

        // регистрация нового пользователя
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.RegisterOrderPageFiller(RandomStringUtils.randomAlphabetic(6),
                email,
                password);
        newRegistration.waitForRegistrationButton();
        newRegistration.clickRegistrationButton();
        loginPage.waitForLoadLoginPage();

        // логин
        loginPage.loginPageFiller(email, password);
        loginPage.waitForLoadLoginPage();
        loginPage.clickLoginEnterButton();
        loginPage.clickLoginEnterButton(); // я абсолютно без понятия кака избавиться здесь от второго клика
        openMainPage.waitForLoadMainPageAfterLogin();
    }

    // переход по разделам Конструктора: Соусы
    @Test
    public void openSauceTab() {
        openMainPage.clickSauceButton();
        openMainPage.waitSaucePageOpen();
    }

    // переход по разделам Конструктора: Начинки
    @Test
    public void openFillersTab() {
        openMainPage.clickFillersButton();
        openMainPage.waitFillersPageOpen();
    }

    // переход по разделам Конструктора: Булки
    @Test
    public void openBunsTab() {
        openMainPage.clickSauceButton();
        openMainPage.waitSaucePageOpen();
        openMainPage.clickBunsButton();
        openMainPage.waitBunsPageOpen();
    }
}
