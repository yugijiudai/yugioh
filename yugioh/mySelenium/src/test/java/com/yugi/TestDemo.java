package com.yugi;

import com.yugi.driverEnum.DriverEnum;
import com.yugi.provider.DriverProvider;
import com.yugi.util.DriverUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhonghaowen
 * @apiNote
 * @since 2017-04-05
 */
public class TestDemo {

    private WebDriver driver;

    @Before
    public void beforeMethod() {

    }

    @After
    public void afterMethod() {
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }

    private void doTest() {
        driver.get("https://www.baidu.com");
        // WebElement element = driver.findElement(By.name("q"));
        WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("吔屎啦梁非凡!");
        element.submit();
        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doLogin() {
        try {
            driver.get("http://localhost:18080");
            driver.findElement(By.id("j_username_display")).clear();
            driver.findElement(By.id("j_username_display")).sendKeys("admin");
            driver.findElement(By.id("j_password")).clear();
            driver.findElement(By.id("j_password")).sendKeys("1");
            driver.findElement(By.id("btn1")).click();
            Thread.sleep(2000);
            WebElement frame = DriverUtil.findByIdAwait("iframe_recyclingApplication");
            driver.switchTo().frame(frame);
            DriverUtil.findByAwait(By.xpath("//button[contains(text(), '申请器械包2')]")).click();
            // driver.findElement(By.xpath("//button[contains(text(), '申请器械包')]")).click();
            driver.switchTo().defaultContent();
            String currentWindowHandle = driver.getWindowHandle();
            driver.switchTo().window(currentWindowHandle);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testChrome() {
        driver = DriverProvider.initWebDriver(DriverEnum.CHROME);
        this.doTest();
    }

    @Test
    public void testFireFox() {
        driver = DriverProvider.initWebDriver(DriverEnum.FIREFOX);
        this.doTest();
    }

    @Test
    public void testIe() {
        String path = "D:\\myDrivers\\IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", path);
        driver = new InternetExplorerDriver();
        this.doTest();
    }

    @Test
    public void testIe2() {
        String path = "D:\\myDrivers\\IEDriverServer.exe";
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        try {
            driver = new RemoteWebDriver(new URL("https://www.google.com/ncr"), ieCapabilities);
            this.doTest();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_case3() throws InterruptedException {
        // final File file = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        // driver = new ChromeDriver();
        // System.setProperty("webdriver.gecko.driver", "D:\\myDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        System.setProperty("webdriver.ie.driver",
                "C:\\IEDriverServer_Win32_3.3.0\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();
        driver.get("https://www.google.com/ncr");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("吔屎啦梁非凡!");
        element.submit();
        Thread.sleep(8000);
    }

}
