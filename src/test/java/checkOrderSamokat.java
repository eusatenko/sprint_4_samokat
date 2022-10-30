import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPage;

import static org.hamcrest.CoreMatchers.containsString;

/*
Тестовый сценарий
2) Заказ самоката. Весь флоу позитивного сценария.
Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
Нажать кнопку «Заказать». На странице две кнопки заказа.
Заполнить форму заказа.
Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
Нужно написать тесты с разными данными: минимум два набора. Какие именно данные использовать — на твоё усмотрение.
 */

//Параметризированный тест
@RunWith(Parameterized.class)
public class checkOrderSamokat {

    private WebDriver driver;

    @Before
    public void startUp() {

        // Рабочий вариант запуска Firefox через вебдрайвер
//        WebDriverManager.firefoxdriver().clearDriverCache().setup();
//        driver = new FirefoxDriver();
        // Запуск в Crome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private final int n;
    private final String firstName;
    private final String secondName;
    private final String adress;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String samokatColor;
    private final String commentForCourier;


    public checkOrderSamokat(int n, String firstName, String secondName, String adress, String metroStation, String phoneNumber, String date, String samokatColor, String commentForCourier) {
        this.n = n;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adress = adress;

        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.samokatColor = samokatColor;
        this.commentForCourier = commentForCourier;
    }


    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        //Сгенерируй тестовые данные (свою учётку и несколько случайных)
        return new Object[][]{
                {1, "Майк", "Вазовский", "ул. Страшища, д.15", "Крылатское", "+71231231212", "05.04.2023", "black", "Перед выездом позвонить мне!"},
                {2, "Кощей", "Бессмертный", "ул. Сказочная, д.666", "Лубянка", "+71231231212", "15.04.2023", "grey", "Перед выездом позвонить мне!"},
        };
    }

    @Test
    public void positiveCheckOrderSamokat() {
        // Создали объект главной страницы
        MainPage mainPage = new MainPage(driver);
        // переход на страницу тестового приложения
        mainPage.openMainPage();
        // Клик по кнопке "Заказать"
        mainPage.clickOrder(n);
        // Создали объект страницы "Заказать"
        OrderPage orderPage = new OrderPage(driver);
        // Ввод имени в Поле ввода имени
        orderPage.inputFirstName(firstName);
        // Ввод фамилии в Поле ввода фамилии
        orderPage.inputSecondName(secondName);
        // Ввод адреса в Поле ввода адреса
        orderPage.inputAdress(adress);
        // Выбор метро - клик по полю
        orderPage.clickMetro();
        // Ввод названия станции и Клик по 1 выпавшей станции
        orderPage.choseMetroStation(metroStation);
        // Ввод телефона в Поле ввода номера телефона
        orderPage.inputPhoneNumber(phoneNumber);
        // Нажать кнопку "Далее"
        orderPage.clickNext();
        // Перешли на следующий экран "Про аренду"
        // Кликнуть в поле выбора даты и поставить нужную и нажать Enter
        orderPage.inputDate(date);
        // Клик по выбору срока аренды и Выбор срока аренды в выпадающем меню - первый из списка
        orderPage.clickTimeRent();
        // Выбор цвета самоката (первый чек-бокс)
        orderPage.clickColorSamokat(samokatColor);
        // Ввод комментария в Поле "Комментарий для курьера"
        orderPage.inputСommentForCourier(commentForCourier);
        // Нажать кнопку Заказать
        orderPage.clickOrder();
        //Проверка, что окно подтверждения заказа появилось
        orderPage.chekOrderVerificationWindow();
        // Клик на кнопку "Да"
        orderPage.clickYesOrder();
        //Прверка текста в финальном окне подтверждения заказа
        MatcherAssert.assertThat("Не появилось окно (Заказ оформлен)",
                orderPage.getFinalOrderMessage(), containsString("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

}
