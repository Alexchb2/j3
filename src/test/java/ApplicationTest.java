import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationTest {

    @BeforeEach
    void start(){
        open("http://localhost:9999/");
    }




    @Test
    void shouldValidTest1(){
        $("[data-test-id=name] input").setValue("Макаренко Алексей");
        $("[data-test-id=phone] input").setValue("+79876543210");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldValidTest2(){
        $("[data-test-id=name] input").setValue("Макаренко Aлёна");
        $("[data-test-id=phone] input").setValue("+79876543210");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNegativeNameTest1(){
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79876543210");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNegativePhoneTest1(){
        $("[data-test-id=name] input").setValue("Макаренко Алексей");
        $("[data-test-id=phone] input").setValue("+798765432100");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNegativeAgreementTest1(){
        $("[data-test-id=name] input").setValue("Макаренко Алексей");
        $("[data-test-id=phone] input").setValue("+79876543210");
        $("button.button").click();
        $("[data-test-id=agreement]").should(cssClass("input_invalid"));
    }

    @Test
    void shouldEmptyFieldName(){
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79876543210");
        $("button.button").click();
        $("[data-test-id=name]").should(cssClass("input_invalid"));
    }

    @Test
    void shouldEmptyFieldPhone(){
        $("[data-test-id=name] input").setValue("Макаренко Алексей");
        $("[data-test-id=phone] input").setValue("");
        $("button.button").click();
        $("[data-test-id=phone]").should(cssClass("input_invalid"));
    }
}
