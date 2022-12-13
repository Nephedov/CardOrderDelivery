package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderDeliveryTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:7777/");
    }

    @Test
    void positiveRegistrationTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(),'Забронировать')]")
                .click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate),
                        Duration.ofSeconds(15))
                .shouldBe(visible);


    }

    @Test
    void invalidCityFieldTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Анапа");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='city'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void invalidDateFieldTest() {
        String planningDate = Date.generateDate(1);

        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $("[data-test-id='date'] .input_invalid .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void invalidNameFieldTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Petrov Petr");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='name'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void invalidPhoneFieldTest() {

        String planningDate = Date.generateDate(3);
        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+7123123121");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='phone'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void withoutCheckBoxRegistrationTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $x("//span[contains(text(),'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='agreement'] .checkbox__text")
                .shouldBe(visible)
                .shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void nullCityFieldTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='city'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void nullDateFieldTest() {
        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $("[data-test-id='date'] .input_invalid .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Неверно введена дата"));
    }

    @Test
    void nullNameFieldTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='name'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void nullPhoneFieldTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Казань");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(), 'Забронировать')]")
                .click();
        $(".input_invalid[data-test-id='phone'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void cityListClickTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Вл");
        $x("//span[contains(text(), 'Владимир')]")
                .click();
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(),'Забронировать')]")
                .click();
        $("[data-test-id='notification']")
                .shouldBe(visible,
                        Duration.ofSeconds(15));


    }

    @Test
    void cityListGetElementTest() {
        String planningDate = Date.generateDate(3);

        $("[data-test-id='city'] input")
                .setValue("Вл");
        $$(".menu-item__control")
                .get(3)
                .click();
        $("[data-test-id='date'] input")
                .sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input")
                .setValue(planningDate);
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(),'Забронировать')]")
                .click();
        $("[data-test-id='notification']")
                .shouldBe(visible,
                        Duration.ofSeconds(15));
    }

    @Test
    void calendarDateTest() {
        $("[data-test-id='city'] input")
                .setValue("Вл");
        $x("//span[contains(text(), 'Владимир')]")
                .click();
        $("[data-test-id='date'] input")
                .click();
        $x(".//td[@role='gridcell'][contains(text(),'26')]")
                .click();
        $("[data-test-id='name'] input")
                .setValue("Петров Петр");
        $("[data-test-id='phone'] input")
                .setValue("+71231231212");
        $(".checkbox__box")
                .click();
        $x("//span[contains(text(),'Забронировать')]")
                .click();
        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Встреча успешно забронирована на "),
                        Duration.ofSeconds(15))
                .shouldBe(visible);

    }
}

