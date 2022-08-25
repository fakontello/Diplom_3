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
    private final SelenideElement privetOfficeButton = $(byXpath("//*[@id=\"root\"]/div/header/nav/a/p"));

    // метод клика по кнопке "Личный кабинет"
    public void clickPrivetOfficeButton() {
        privetOfficeButton.click();
    }

    // метод ожидания загрузки страницы: проверили видимость кнопки "Личный кабинет"
    public void waitForLoadHomePage() {
        $(byText("Соберите бургер")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // кнопка "Войти в аккаунт"
    private final SelenideElement loginAccountButton = $(byXpath("//*[@id=\"root\"]/div/main/section[2]/div/button"));

    // метод клика по кнопке "Войти в аккаунт"
    public void clickLoginAccountButton() {
        loginAccountButton.click();
    }

    // кнопка "Оформить заказ" после входа в личный кабинет
    private final SelenideElement makeOrderButton = $(byXpath("//*[@id=\"root\"]/div/main/section[2]/div/button"));

    // метод ожидания страницы после входа в личный кабинет
    public void waitForLoadMainPageAfterLogin() {
        makeOrderButton.shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // кнопка галвное страницы
    private final SelenideElement homePageButton = $(byXpath("//*[@id=\"root\"]/div/header/nav/div/a"));

    // метод клика по кнопке главное страницы
    public void clickHomePageButton() {
        homePageButton.click();
    }

    // кнопка Консутрктора: Соусы
    private final SelenideElement sauceButton = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]/span"));

    // метод клика по кнопке Соусы
    public void clickSauceButton() {
        sauceButton.click();
    }

    // метод ожидания открытия раздела Соусы
    public void waitSaucePageOpen() {
        $(byText("Соусы")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // кнопка Конструктора: Начинки
    private final SelenideElement fillersButton = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]/span"));

    // метод клика по кнопке Начинки
    public void clickFillersButton() {
        fillersButton.click();
    }

    // метод ожидания открытия раздела Начинки
    public void waitFillersPageOpen() {
        $(byText("Начинки")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // кнопка Конструктора: Булки
    private final SelenideElement bunsButton = $(byXpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span"));

    // метод клика по кнопке Булки
    public void clickBunsButton() {
        bunsButton.click();
    }

    // метод ожидания открытия раздела Начинки
    public void waitBunsPageOpen() {
        $(byText("Булки")).shouldBe(Condition.visible, Duration.ofSeconds(3));
    }
}
