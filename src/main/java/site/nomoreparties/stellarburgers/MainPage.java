package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class MainPage {

    // кнопка "Личный кабинет"
    private final SelenideElement privetOfficeButton =
            $(byXpath("//*[@id=\"root\"]/div/header/nav/a/p"));

    // метод клика по кнопке "Личный кабинет"
    public void clickPrivetOfficeButton() {
        privetOfficeButton.click();
    }

    // метод ожидания загрузки страницы: проверили видимость кнопки "Личный кабинет"
    public void waitForLoadHomePage() {
        $(byText("Соберите бургер")).shouldBe(visible, ofSeconds(3));
    }

    // кнопка "Войти в аккаунт"
    private final SelenideElement loginAccountButton = $(byXpath("//*[@id=\"root\"]/div/main/section[2]/div/button"));

    // метод клика по кнопке "Войти в аккаунт"
    public void clickLoginAccountButton() {
        loginAccountButton.click();
    }

    // кнопка "Оформить заказ" после входа в личный кабинет
    private final SelenideElement makeOrderButton = $(byXpath("//*[@id=\"root\"]/div/main/section[2]/div/button"));

    // метод клика по кнопке "Оформить заказ"
    public void clickMakeOrderButton() {
        makeOrderButton.click();
    }

    // метод ожидания страницы после входа в личный кабинет
    public void waitForLoadMainPageAfterLogin() {
        $(byXpath("/html/body/div/div/main/section[2]/div/button")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // кнопка галвное страницы
    private final SelenideElement mainPageButton = $(byXpath("//*[@id=\"root\"]/div/header/nav/div/a"));

    // метод клика по кнопке главное страницы
    public void clickMainPageButton() {
        mainPageButton.click();
    }
}
