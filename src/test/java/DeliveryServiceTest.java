import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class DeliveryServiceTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        SelenideElement form = $("form");
        form.$(By.cssSelector("[data-test-id='date'] input"))
                .press(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE))
                .sendKeys(firstMeetingDate);
        $("[name='name']").setValue(validUser.getName());
        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[name='phone']").setValue(validUser.getPhone());

        $("[data-test-id='agreement']").click();
        $$("button").find(text("Запланировать"))
                .shouldBe(enabled, Duration.ofSeconds(20))
                .click();
        $("div.notification__title").shouldHave(text("Успешно"), Duration.ofSeconds(20));
        $("div.notification__content").shouldHave(text("Встреча успешно запланирована на " + firstMeetingDate)
                , Duration.ofSeconds(20));
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $(Selectors.byText("Запланировать")).click();
        $("[data-test-id='replan-notification'] div.notification__title")
                .shouldHave(text(("Необходимо подтверждение")), Duration.ofSeconds(20));
        $("[data-test-id='replan-notification'] div.notification__content")
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible);
        $$("button").find(text("Перепланировать")).shouldBe(enabled, Duration.ofSeconds(20))
                .click();
        $("div.notification__content").shouldHave(text("Встреча успешно запланирована на " + secondMeetingDate), Duration.ofSeconds(20));

    }
}