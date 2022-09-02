package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class UnsuccessfullyRegistrationTest {

    private RegistrationPage newRegistration;
    private LoginPage loginPage;

    @Before
    public void preconditions() {
        // Configuration.browser = System.getProperty("browser"); - для запуска в разных браузерах
        MainPage openMainPage = open("https://stellarburgers.nomoreparties.site/", MainPage.class);
        openMainPage.waitForLoadHomePage();
        newRegistration = new RegistrationPage(RandomStringUtils.randomAlphabetic(6),
                RandomStringUtils.randomAlphabetic(6) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(5));
        loginPage = new LoginPage();
        openMainPage.clickPrivetOfficeButton();
        loginPage.waitForLoadLoginPage();
    }

    @After
    public void postConditions() {
        Selenide.closeWebDriver();
    }

    // Тест на не успешную регистрацию нового пользователя, пароль меньше 6 символов
    @Test
    public void newNegativeRegistration() {
        loginPage.clickRegistrationButtonOnLoginPage();
        newRegistration.registerOrderPageFiller
                (newRegistration.getName(), newRegistration.getEmail(), newRegistration.getPassword());
        newRegistration.clickRegistrationButton();
        newRegistration.getUnsuccessfulRegistration().shouldHave(Condition.exactText("Некорректный пароль"));
    }
}
