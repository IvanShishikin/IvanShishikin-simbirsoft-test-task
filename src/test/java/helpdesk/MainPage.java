package helpdesk;

import core.BaseSeleniumPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;


public class MainPage extends BaseSeleniumPage {

    public static final String SRC_TEST_RESOURCES_IMG_PNG = "src/test/resources/img.png";
    @FindBy(xpath = "//*[@id=\"firstName\"]")
    private WebElement firstNameList;

    @FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[3]/label")
    private WebElement genderList;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    private WebElement lastNameList;

    @FindBy(xpath = "//*[@id=\"userEmail\"]")
    private WebElement emailList;

    @FindBy(xpath = "//*[@id=\"dateOfBirthInput\"]")
    private WebElement dateField;

    @FindBy(xpath = "//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]")
    private WebElement dateValue;

    @FindBy(xpath = "//*[@id=\"userNumber\"]")
    private WebElement mobileList;

    @FindBy(xpath = "//*[@id=\"subjectsInput\"]")
    private WebElement subjectList;

    @FindBy(xpath = "//*[@id=\"currentAddress\"]")
    private WebElement addressList;

    @FindBy(xpath = "//*[@id=\"uploadPicture\"]")
    private WebElement uploadPictureList;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    private WebElement submitButton;


    @FindBy(css = "#state > div")
    private WebElement stateSelector;

    @FindBy(css = "#city > div")
    private WebElement citySelector;

    public MainPage() {
        driver.get("https://demoqa.com/automation-practice-form");
        PageFactory.initElements(driver, this);
    }

    public void createForm(String firstNameValue,
                           String lastNameValue,
                           String emailValue,
                           String mobileValue,
                           String addressValue,
                           String subjectFirstLetterKeyValue) throws InterruptedException {
        genderList.click();

        firstNameList.sendKeys(firstNameValue);
        lastNameList.sendKeys(lastNameValue);
        emailList.sendKeys(emailValue);
        mobileList.sendKeys(mobileValue);
        subjectList.sendKeys(subjectFirstLetterKeyValue);
        subjectList.sendKeys(Keys.ENTER);
        addressList.sendKeys(addressValue);

        String path = SRC_TEST_RESOURCES_IMG_PNG;
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        uploadPictureList.sendKeys(absolutePath);

        dateField.click();
        dateValue.click();

        new Actions(driver)
                .moveToElement(stateSelector)
                .perform();
        Thread.sleep(2000);
        stateSelector.click();
        Thread.sleep(2000);
        new Actions(driver)
                .moveByOffset(0, 100)
                .perform();
        Thread.sleep(2000);
        new Actions(driver)
                .click()
                .perform();

        new Actions(driver)
                .moveToElement(citySelector)
                .perform();
        Thread.sleep(2000);
        citySelector.click();
        Thread.sleep(2000);
        new Actions(driver)
                .moveByOffset(0, 100)
                .perform();
        Thread.sleep(2000);
        new Actions(driver)
                .click()
                .perform();
        HelpDeskTest.setBirthDateValue(driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]")).getAttribute("value"));
        submitButton.submit();
    }


}
