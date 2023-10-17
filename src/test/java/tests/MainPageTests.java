package tests;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.components.VerifyComponent;

import java.util.stream.Stream;

@Tags({@Tag("UI"), @Tag("MainPage")})
public class MainPageTests extends TestBase{
    MainPage hyperSkillMainpage = new MainPage();
    VerifyComponent verifyComponent = new VerifyComponent();

    @CsvSource(value = {
            "Java, https://hyperskill.org/categories/2",
            "Beginner-friendly, https://hyperskill.org/categories/3",
            "Python, https://hyperskill.org/categories/1"
    })
    @ParameterizedTest(name = "Открытие страницы {1} при клике на {0}")
    @Tags({@Tag("UI"), @Tag("Severe")})
    void openStudyGroupLinkTest(String target, String actualUrl) {
        hyperSkillMainpage.openPage();
        hyperSkillMainpage.clickTargetBar(target);
        verifyComponent.verifyUrl(actualUrl);
    }
    @CsvFileSource(resources = "/exploreBar.csv")
    @ParameterizedTest(name = "При нажатии на кнопку в выпадающем списке {0} открывается страница с Заголовком {1}")
    @Tags({@Tag("UI"), @Tag("Blocker")})
    void openCommunityGroupLinkTest(String value, String url) {
        hyperSkillMainpage.openPage();
        hyperSkillMainpage.clickTargetBar(value);
        verifyComponent.verifySiteTitle(url);
    }

    static Stream<Arguments> selenideButtonsTest() {
        return Stream.of(
                Arguments.of("Frontend", "Learn Frontend tracks"),
                Arguments.of(" Scala ", "Learn Scala tracks")
        );
    }

    @MethodSource("selenideButtonsTest")
    @ParameterizedTest(name = "При нажатии на кнопку навигации {0} открывается страница с заголовком {1} первого уровня")
    @Tags({@Tag("UI"), @Tag("Minor")})
    void openHeaderNavLinkTest(String navLink, String article) {
        hyperSkillMainpage.openPage();
        hyperSkillMainpage.clickTargetBar(navLink);
        verifyComponent.verifyPageArticleText(article);
    }


}
