package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {this.driver = driver;}

    // Открыть страницу с сайтом
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Кликнуть по строке в "Вопросы о важном"
    public void clickAccordeonHeading(String locatorAccordeonHeading) {
        //Явное ожидание на появление текста (не больше 3 секунд)
        WebElement element = driver.findElement(By.id(String.valueOf(locatorAccordeonHeading)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        // Кликаем по строке
        element.click();
    }

    // Получить текст из строки "Вопросы о важном"
    public String getTextAccordeonHeading(String locatorAccordeonHeading) {
        //Явное ожидание на появление текста (не больше 3 секунд)
        WebElement element = driver.findElement(By.id(String.valueOf(locatorAccordeonHeading)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        String actualTextAccordeonHeader = element.getText();
        return actualTextAccordeonHeader;
    }

    // Получить текст из выпавшей панели (после клика по строке в "Вопросы о важном)
    public String getTextAccordeonPanel (String locatorAccordeonPanel) {
        String actualTextAccordeonPanel = driver.findElement(By.id(locatorAccordeonPanel)).getText();
        return actualTextAccordeonPanel;
    }
}
