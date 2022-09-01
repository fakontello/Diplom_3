package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    // поле "Имя"
    private final SelenideElement nameField =
            $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input"));

    // поле "Email"
    private final SelenideElement emailField =
            $(byXpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input"));

    // поле "Пароль"
    private final SelenideElement passwordField =
            $(byXpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input"));

    // кнопка "Войти" на странице регистрации
    private final SelenideElement registrationLoginButton = $(byXpath("//*[@id=\"root\"]/div/main/div/div/p/a"));

    // кнопка "Зарегистрироваться"
    private final SelenideElement registrationButton =
            $(byCssSelector("button[class='button_button__33qZ0 "
                    + "button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"));

    // поле Некорректный пароль
    private final SelenideElement unsuccessfulRegistration = $(byText("Некорректный пароль"));

    public SelenideElement getUnsuccessfulRegistration() {
        return unsuccessfulRegistration;
    }

    // метод заполнения поля "Имя"
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    // метод заполнения поля "Email"
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    // метод заполнения поля "Пароль"
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    // метод клика по кнопке "Зарегистрироваться"
    public void clickRegistrationButton() {
        registrationButton.click();
    }

    // метод ожидания кнопки "Зарегистрироваться"
    public void waitForRegistrationButton() {
        registrationButton.shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // метод заполнения страницы регистрации нового пользователя
    public void registerOrderPageFiller(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    // метод клика по кнопке "Войти" на странице регистрации
    public void clickRegistrationLoginButton() {
        registrationLoginButton.click();
    }

    // метод ожидания кнопки Войти на странице регистрации
    public void waitRegistrationLoginButton() {
        registrationButton.shouldBe(Condition.visible, Duration.ofSeconds(3));
    }
}
