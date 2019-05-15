import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SimpleTest {
    WebDriver browser;
    //Открытие браузера с настройками и загрузка сайта
    @BeforeClass
    public void startTest()
    {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
        chromeOptionsMap.put("plugins.plugins_disabled", new String[] {
                "Chrome PDF Viewer"
        });
        chromeOptionsMap.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", chromeOptionsMap);
        String downloadFilepath = "C:\\";
        chromeOptionsMap.put("download.default_directory", downloadFilepath);
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        browser = new ChromeDriver(cap);
        browser.manage().window().maximize();
    }
    //Тестовое задание №1
    @Description("Тест №1")
    @Test
    public void testSite1() {
        browser.get("http://rencredit.ru");
        Log.logName("Открытие сайта http://rencredit.ru");
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        browser.findElement(By.cssSelector("body > div.content > div.main-screen > div.main-screen__content > div > div.services.services_main > div:nth-child(2) > div.service__title > a:nth-child(1)")).click();
        Log.logName("Переход на страницу \"Вклад\"");
        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        browser.findElement(By.cssSelector("#section_1 > div > div > form > div.calculator__content > div.internet-bank > div > div:nth-child(3) > label > span.calculator__check-block-input > div")).click();
        Log.logName("Выбрать чекбокс \"В отделении банка\"");
        browser.findElement(By.cssSelector("#section_1 > div > div > form > div.calculator__content > div:nth-child(3) > div.calculator__slide-input-row > div > label > input")).sendKeys("201 000");
        Log.logName("Ввести сумму вклада");
        WebElement Slider = browser.findElement(By.cssSelector("#section_1 > div > div > form > div.calculator__content > div:nth-child(4) > div.range_wrapper.calculator__slide-row > div.range.js-range.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all > div"));
        Actions SliderTo = new Actions(browser);
        SliderTo.clickAndHold(Slider);
        SliderTo.moveByOffset(40, 0).build().perform();
        Log.logName("Передвинуть ползунок \"На срок\"");
        browser.findElement(By.cssSelector("#section_2 > div > div.tabs.js-tabs > div.tabs-content > div:nth-child(1) > div.deposits-terms__info-doc-row > div > h3 > a")).click();
        Log.logName("Выгрузить Печатную Форму \"Общие условия по вкладам\"");
    }
    //Тестовое задание №2
    @Description("Тест №2")
    @Test
    public void testSite2()
    {
        Set<String> beforeTabs =browser.getWindowHandles();
        String a = "window.open('about:blank','_blank');";
        ((JavascriptExecutor)browser).executeScript(a);
        Set<String> afterTabs = browser.getWindowHandles();
        afterTabs.removeAll(beforeTabs);
        browser.switchTo().window((String) afterTabs.toArray()[0]);
        browser.get("http://rencredit.ru");
        Log.logName("Открыть сайт rencredit.ru");
        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        browser.findElement(By.cssSelector("body > div.content > div.main-screen > div.main-screen__content > div > div.services.services_main > div:nth-child(3) > div.service__title > a.service__title-action")).click();
        Log.logName("Перейти на страницу \"Карта\"");
        browser.findElement(By.cssSelector("#t1")).sendKeys("Сидоров");
        browser.findElement(By.cssSelector("#t2")).sendKeys("Сергей");
        browser.findElement(By.cssSelector("#t4")).sendKeys("9374023300");
        browser.findElement(By.cssSelector("#t38")).sendKeys("sidorovserega92@gmail.com");
        Log.logName("Ввести в поля \"Фамилия\", \"Имя\", \"Мобильный телефон\", \"e-mail\" значения");
        browser.findElement(By.cssSelector("#section_1 > div.order-form-block__content > div > form > div:nth-child(1) > div:nth-child(4) > div > label > div")).click();
        Log.logName("Выбрать чекбокс \"Нет отчества\"");
        Select gender = new Select(browser.findElement(By.xpath("//*[@id=\"s2\"]")));
        gender.selectByVisibleText("Пензенская область");
        browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement selectMonth = browser.findElement(By.xpath("//*[@id=\"s3\"]"));
        Select select = new Select(selectMonth);
        select.selectByVisibleText("г.Пенза");
        Log.logName("Выбрать значение из выпадающего списка \"Где вы желаете получить карту\"");
    }
    public static class Log
    {
        private Log() {
        }
        @Step("{0}")
        public static void logName(final String message){
            //intentionally empty
        }
    }
}
