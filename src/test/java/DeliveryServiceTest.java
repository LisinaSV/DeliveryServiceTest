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

class DataGeneratorTest {

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
        $("[name='name']").setValue(validUser.getName());
        $("[placeholder='Город']").setValue(validUser.getCity());
        $("[name='phone']").setValue(validUser.getPhone());
        form.$(By.cssSelector("[data-test-id='date'] input")).press(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE)).sendKeys(firstMeetingDate);
        $("[data-test-id='agreement']").click();
        $$("button").find(text("Запланировать"))
                .shouldBe(enabled, Duration.ofSeconds(15))
                .click();
        $("div.notification__title").shouldHave(text("Успешно"), Duration.ofSeconds(15));
        $("div.notification__content").shouldHave(text("Встреча успешно запланирована на "), Duration.ofSeconds(20))
                .shouldHave(text(firstMeetingDate), Duration.ofSeconds(5));
        $$("button").find(text("Запланировать")).shouldBe(enabled, Duration.ofSeconds(15))
                .click();
        $(Selectors.byText("Необходимо подтверждение"));
        $(Selectors.byText("У вас уже есть запланированная встреча"));
        $(Selectors.byText("Перепланировать")).click();
        $("div.notification__content").shouldHave(text("Успешно"), Duration.ofSeconds(20))
                .shouldHave(text(firstMeetingDate), Duration.ofSeconds(5));
    }
}