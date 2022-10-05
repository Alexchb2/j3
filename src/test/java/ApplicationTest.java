import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationTest {



    @Test
    void shouldValidTest1(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Макаренко Алексей");
        form.$("[data-test-id=phone] input").setValue("+79876543210");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldValidTest2(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Макаренко Aлёна");
        form.$("[data-test-id=phone] input").setValue("+79876543210");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNegativeNameTest1(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Ivanov Ivan");
        form.$("[data-test-id=phone] input").setValue("+79876543210");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNegativePhoneTest1(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Макаренко Алексей");
        form.$("[data-test-id=phone] input").setValue("+798765432100");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNegativeAgreementTest1(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Макаренко Алексей");
        form.$("[data-test-id=phone] input").setValue("+79876543210");
        form.$("button.button").click();
        $("[data-test-id=agreement]").should(cssClass("input_invalid"));
    }
}
