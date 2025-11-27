import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DataGenerator {

    private final Faker faker = new Faker(new Locale("ru"));

    public String generateData(int day, String pattern) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldRegisteredAccount() {
        String name = faker.name().fullName();
        String city = faker.address().city();
        String phone = faker.phoneNumber().phoneNumber();
        SelenideElement form = $("form");
        open("http://localhost:9999/");

        $("[name='name']").setValue(name);
        $("[placeholder='Город']").setValue(city);

        String date = generateData(3, "dd.MM.yyyy");

        form.$(By.cssSelector("[data-test-id='date'] input")).press(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE)).sendKeys(generateData(3, "dd.MM.yyyy"));
//        $("[type='date']").setValue(date);
        $("[name='phone']").setValue(phone);
        $("[data-test-id='agreement']").click();
        $$("button").find(text("Запланировать")).click();
        $("div.notification__title").shouldHave(text("Успешно"), Duration.ofSeconds(15));
        $(".notification__content").shouldHave(text("Встреча успешно запланирована на " + date), Duration.ofSeconds(15));
//        $("div.notification__title").shouldHave(text("Необходимо подтверждение"), Duration.ofSeconds(15));
//        $("div.notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15));
//        $$("button").find(text("Перепланировать")).click();
//
    }
}
