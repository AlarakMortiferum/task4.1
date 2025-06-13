package ru.netology.patterns.test;

import org.junit.jupiter.api.Test;
import ru.netology.patterns.data.DataGenerator;
import ru.netology.patterns.data.UserInfo;
import ru.netology.patterns.page.DeliveryOrderPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {

    @BeforeAll
    static void setupAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    void shouldReplanMeeting() {
        open("http://localhost:9999");
        DeliveryOrderPage page = new DeliveryOrderPage();

        UserInfo user = DataGenerator.generateUser("ru");
        String firstDate = DataGenerator.generateDate(4);
        String secondDate = DataGenerator.generateDate(10);

        page.fillForm(user, firstDate);
        page.checkSuccessNotificationVisible(firstDate);

        page.setDate(secondDate);
        page.submitForm();
        page.confirmReplan();
        page.checkSuccessNotificationVisible(secondDate);
    }
}
