package helpdesk;

import core.BaseSeleniumTest;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelpDeskTest extends BaseSeleniumTest {

    private static final String RESPONSE_MESSAGE_VALUE = "Thanks for submitting the form";

    public static String birthDateValue;

    @Test
    public void checkForm() throws InterruptedException {

        String firstName = "Ivan";
        String lastName = "Shishikin";
        String email = "testEmailIvanShishikin@gmail.com";
        String mobile = "8999000000";
        String address = "Address value for example";
        String subjectFirstLetter = "E";
        String expectedSubjectValue = "English";
        String genderExpectedValue = "Other";

        MainPage mainPage = new MainPage();
        mainPage.createForm(firstName, lastName, email, mobile, address, subjectFirstLetter);

        assertEquals(RESPONSE_MESSAGE_VALUE, driver.findElement(By.xpath("//*[@id=\"example-modal-sizes-title-lg\"]")).getText());
        assertEquals(firstName.concat(" ").concat(lastName), driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]")).getText());
        assertEquals(email, driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]")).getText());
        assertEquals(mobile, driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]")).getText());
        assertEquals(address, driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[9]/td[2]")).getText());
        assertEquals(expectedSubjectValue, driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[6]/td[2]")).getText());
        assertEquals(genderExpectedValue, driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]")).getText());

        String actualDateValue = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]")).getText();
        List<String> birthDateValueList = List.of(birthDateValue.split("\\s"));
        List<String> actualDateValueList = List.of(actualDateValue.replaceAll(",", " ").split("\\s"));

        assertEquals(birthDateValueList.size(), actualDateValueList.size());
        assertEquals(3, birthDateValueList.size());

        for (int i = 0; i < birthDateValueList.size(); i++) {
            if (i == 0 || i == 2) {
                assertEquals(birthDateValueList.get(i), actualDateValueList.get(i));
            } else if (i == 1) {
                assertTrue(actualDateValueList.get(i).contains(birthDateValueList.get(i)));
            }
        }

    }


    public static void setBirthDateValue(String value) {
        birthDateValue = value;
    }

}
