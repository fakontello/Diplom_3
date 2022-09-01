package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // кнопка "Зарегистрироваться" на странице логина
    private final SelenideElement registrationButtonOnLoginPage =
            $(byClassName("Auth_link__1fOlj"));

    // поле "Email"
    private final SelenideElement loginEmailField =
            $(byXpath("//label[text()='Email']/parent::div//input"));

    // поле "Пароль"
    private final SelenideElement loginPasswordField =
            $(byXpath("//label[text()='Пароль']/parent::div//input"));

    // кнопка "Войти" на странице логина
    private final SelenideElement loginEnterButton = $(byXpath("//button[text()='Войти']"));

    // кнопка Восстановить пароль на странице логина
    private final SelenideElement resetPasswordButton = $(byXpath("//a[text()='Восстановить пароль']"));

    // метод клика по кнопке "Зарегистрироваться" на странице логина
    public void clickRegistrationButtonOnLoginPage() {
        registrationButtonOnLoginPage.click();
    }

    // метод заполнения поля "Email"
    public void setLoginEmailField(String email) {
        loginEmailField.setValue(email);
    }

    // метод заполнения поля "Пароль"
    public void setLoginPasswordField(String password) {
        loginPasswordField.setValue(password);
    }

    // метод заполнения страницы вход пользователя
    public void loginPageFiller(String email, String password) {
        setLoginEmailField(email);
        setLoginPasswordField(password);
    }

    // метод ожидания загрузки страницы
    public void waitForLoadLoginPage() {
        loginEnterButton.shouldBe(Condition.visible);
    }

    // метод клика по кнопке "Войти" на странице логина
    public void clickLoginEnterButton() {
        loginEnterButton.click();
    }

    // метод клика по кнопке Восстановить пароль
    public void clickResetPasswordButton() {
        resetPasswordButton.click();
    }


}
