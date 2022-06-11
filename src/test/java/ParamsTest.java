
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.util.Arrays.asList;


public class ParamsTest extends TestBase {

    @DisplayName("Поиск автора")
    @Test
    void authorSearch() {
        Selenide.open("https://www.labirint.ru/");
        $("#search-field").setValue("Lovecraft Howard Phillips");
        $("button[type='submit']").click();
        $(".body-main-content-wrapper").find(byText("Lovecraft Howard Phillips")).shouldBe(visible);
    }



    @ValueSource(strings = {"Кинг Стивен", "Poe Edgar Allan"})
    @ParameterizedTest(name = "При поиске в лабиринте автора {0} в результатах получаем {0}")
    void labirintTest(String testData) {
        Selenide.open("https://www.labirint.ru/");
        $("#search-field").setValue(testData);
        $("button[type='submit']").click();
        $(".body-main-content-wrapper").find(byText(testData)).shouldBe(visible);

    }

    @EnumSource(Books.class)
    @ParameterizedTest
    void booksTest(Books books) {
        Selenide.open("https://www.labirint.ru/");
        $("#search-field").setValue(books.desc);
        $("button[type='submit']").click();
        $(".body-main-content-wrapper").shouldHave(text(books.desc));

    }

    static Stream<Arguments> authorData() {
        return Stream.of(
                Arguments.of("Кинг Стивен", asList("Кинг Стивен")),
                Arguments.of("Poe Edgar Allan", asList("Poe Edgar Allan"))
        );
    }

    @MethodSource(value = "authorData")
    @ParameterizedTest(name = "При поиске в лабиринте автора {0} в результатах получаем {0}")
    void authorSearch(String testData, List<String> expectedResult) {
        Selenide.open("https://www.labirint.ru/");
        $("#search-field").setValue(testData);
        $("button[type='submit']").click();
        $(".body-main-content-wrapper").find(byText(testData)).shouldBe(visible);
    }
    @Disabled
    @Test
    void testCheck(){
        Assertions.assertFalse(9<11);
    }
}
