package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;


public class MainPage {

    // кнопка "Личный кабинет"
    private final SelenideElement privetOfficeButton = $(byXpath("//p[text()='Личный Кабинет']"));

    // кнопка "Войти в аккаунт"
    private final SelenideElement loginAccountButton = $(byXpath("//button[text()='Войти в аккаунт']"));

    // кнопка "Оформить заказ" после входа в личный кабинет
    private final SelenideElement makeOrderButton = $(byXpath("//button[text()='Оформить заказ']"));

    // кнопка главной страницы
    private final SelenideElement homePageButton = $(byClassName("AppHeader_header__logo__2D0X2"));

    // кнопка Консутрктора: Соусы
    private final SelenideElement sauceButton = $(byXpath("//span[text()='Соусы']/.."));

    // кнопка Конструктора: Начинки
    private final SelenideElement fillersButton = $(byXpath("//span[text()='Начинки']/.."));

    // кнопка Конструктора: Булки
    private final SelenideElement bunsButton = $(byXpath("//span[text()='Булки']/.."));

    // $(By.xpath("//*[@id='list']//li[@class='enabled' and .//text()='foo']//*[@class='remove']"))

    // метод клика по кнопке "Личный кабинет"
    public void clickPrivetOfficeButton() {
        privetOfficeButton.click();
    }

    // метод ожидания загрузки страницы: проверили видимость кнопки "Личный кабинет"
    public void waitForLoadHomePage() {
        homePageButton.shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // метод клика по кнопке "Войти в аккаунт"
    public void clickLoginAccountButton() {
        loginAccountButton.click();
    }

    // метод ожидания страницы после входа в личный кабинет
    public void waitForLoadMainPageAfterLogin() {
        makeOrderButton.shouldBe(Condition.visible, Duration.ofSeconds(3));
    }

    // метод клика по кнопке главное страницы
    public void clickHomePageButton() {
        homePageButton.click();
    }

    // метод клика по кнопке Соусы
    public void clickSauceButton() {
        sauceButton.click();
    }

    // метод ожидания открытия раздела Соусы
    public void waitSaucePageOpen() {
        String expectedClassName = sauceButton.getAttribute("class");
        assert expectedClassName != null;
        Assert.assertTrue(expectedClassName.contains("tab_tab_type_current__2BEPc"));
    }

    // метод клика по кнопке Начинки
    public void clickFillersButton() {
        fillersButton.click();
    }

    // метод ожидания открытия раздела Начинки
    public void waitFillersPageOpen() {
        String expectedClassName = fillersButton.getAttribute("class");
        assert expectedClassName != null;
        Assert.assertTrue(expectedClassName.contains("tab_tab_type_current__2BEPc"));
    }

    // метод клика по кнопке Булки
    public void clickBunsButton() {
        bunsButton.click();
    }

    // метод ожидания открытия раздела Булки
    public void waitBunsPageOpen() {
        String expectedClassName = bunsButton.getAttribute("class");
        assert expectedClassName != null;
        Assert.assertTrue(expectedClassName.contains("tab_tab_type_current__2BEPc"));
    }
}
