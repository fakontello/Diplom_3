package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // кнопка "Зарегистрироваться" на странице логина
    private final SelenideElement registrationButtonOnLoginPage =
            $(byXpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a"));

    // метод клика по кнопке "Зарегистрироваться" на странице логина
    public void clickRegistrationButtonOnLoginPage() {
        registrationButtonOnLoginPage.click();
    }

    // метод ожидания загрузки страницы: проверили видимость кнопки "Войти"
    public void waitForLoadLoginPage() {
        $(byText("Вы — новый пользователь?")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // поле "Email"
    private final SelenideElement loginEmailField =
            $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"));

    // метод заполнения поля "Email"
    public void setLoginEmailField(String email) {
        loginEmailField.setValue(email);
    }

    // поле "Пароль"
    private final SelenideElement loginPasswordField =
            $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input"));

    // метод заполнения поля "Пароль"
    public void setLoginPasswordField(String password) {
        loginPasswordField.setValue(password);
    }

    // кнопка "Войти" на странице логина
    private final SelenideElement loginEnterButton = $(byXpath("//*[@id=\"root\"]/div/main/div/form/button"));

    // метод клика по кнопке "Войти" на странице логина
    public void clickLoginEnterButton() {
        loginEnterButton.click();
    }

    // кнопка Восстановить пароль на странице логина
    private final SelenideElement resetPasswordButton = $(byXpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a"));

    // метод клика по кнопке Восстановить пароль
    public void clickResetPasswordButton() {
        resetPasswordButton.click();
    }

    // метод заполнения страницы вход пользователя
    public void loginPageFiller(String email, String password) {
        setLoginEmailField(email);
        setLoginPasswordField(password);
    }

}
