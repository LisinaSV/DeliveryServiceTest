//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.SelenideElement;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selenide.$;
//
//public class DeliveryServiceTest {
//
//    public String generateData(int day, String pattern) {
//        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern(pattern));
//
//    }
//
//    @Test
//    void shouldRegisteredAccount() {
//        String planningDate = generateData(4, "dd.MM.yyyy");
//        Selenide.open("http://localhost:9999/");//открыть страничку
////        $$(".tab-item").find(Condition.text("По номеру счета")).click();//Поиск коллекции по номеру счета
//        SelenideElement form = $("form");//вся форма, внутри формы можно искать вложенные в него элементы
////        form.$("[placeholder='Город'] input").setValue("Москва");
//        form.$(By.cssSelector("[data-test-id='city'] input")).sendKeys("Москва");
//        form.$(By.cssSelector("[data-test-id='date'] input")).press(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE)).sendKeys("30.11.2025");
//        form.$(By.cssSelector("[data-test-id='name'] input")).sendKeys("Лисина Светлана");
//        form.$(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79260000000");
//        form.$("[data-test-id='agreement']").click();
//        form.$$("button").find(text("Запланировать")).click();
////        $("div.notification__title").shouldHave(text("Успешно"), Duration.ofSeconds(15));
////        $("div.notification__content").shouldHave(text("Задача успешно запланирована на 30.11.2025"), Duration.ofSeconds(15));
//        $("div.notification__title").shouldHave(text("Необходимо подтверждение"),Duration.ofSeconds(15));
//        $("div.notification__content").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15));
//        form.$$("button").find(text("Перепланировать")).click();
//
//    }
//
//}