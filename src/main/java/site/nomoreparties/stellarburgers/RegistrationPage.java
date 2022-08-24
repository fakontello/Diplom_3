package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {

    // поле "Имя"
    private final SelenideElement nameField = $(byText("Имя"));

    // метод клика по полю "Имя"
    public void clickNameField() {
        nameField.click();
    }

    // метод заполнения поля "Имя"
    public void setNameField(String name) {
        nameField.setValue(name);
    }

    // поле "Email"
    private final SelenideElement emailField = $(byText("Email"));

    // метод клика по полю "Email"
    public void clickEmailField() {
        emailField.click();
    }

    // метод заполнения поля "Имя"
    public void setEmailField(String email) {
        emailField.setValue(email);
    }

    // поле "Пароль"
    private final SelenideElement passwordField = $(byText("Пароль"));

    // метод клика по полю "Пароль"
    public void clickPasswordField() {
        passwordField.click();
    }

    // метод заполнения поля "Пароль"
    public void setPasswordField(String password) {
        passwordField.setValue(password);
    }

    // кнопка "Зарегистрироваться"
    private final SelenideElement registrationButton =
            $(byCssSelector("button[class='button_button__33qZ0 "
                    + "button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"));

    // метод клика по полю "Зарегистрироваться"
    public void clickRegistrationButton() {
        registrationButton.click();
    }

    // метод заполнения страницы регистрации нового пользователя
    public void RegisterOrderPageFiller(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }
}
