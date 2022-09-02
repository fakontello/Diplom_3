package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    private String email;
    private String password;
    private String name;

    // поле "Имя"
    private final SelenideElement nameField =
            $(byXpath("//label[text()='Имя']/parent::div//input"));

    // поле "Email"
    private final SelenideElement emailField =
            $(byXpath("//label[text()='Email']/parent::div//input"));

    // поле "Пароль"
    private final SelenideElement passwordField =
            $(byXpath("//label[text()='Пароль']/parent::div//input"));

    // кнопка "Войти" на странице регистрации
    private final SelenideElement registrationLoginButton =
            $(byXpath("//a[text()='Войти']"));

    // кнопка "Зарегистрироваться"
    private final SelenideElement registrationButton =
            $(byXpath("//button[text()='Зарегистрироваться']"));

    // поле Некорректный пароль
    private final SelenideElement unsuccessfulRegistration =
            $(byText("Некорректный пароль"));

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



    // метод клика по кнопке "Войти" на странице регистрации
    public void clickRegistrationLoginButton() {
        registrationLoginButton.click();
    }

    // метод ожидания кнопки Войти на странице регистрации
    public void waitRegistrationLoginButton() {
        registrationButton.shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        return this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        return this.password = this.password;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public RegistrationPage(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // метод заполнения страницы регистрации нового пользователя
    public void registerOrderPageFiller(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    public static RegistrationPage getRandomUser() {
        String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new RegistrationPage(email, password, name);
    }
}
