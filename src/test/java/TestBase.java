import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.labirint.ru ";
        Configuration.browserSize = "3840x2160";
        Configuration.holdBrowserOpen = true;
    }
}
