package com.yugi.provider;

import com.yugi.driverEnum.DriverEnum;
import com.yugi.util.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author zhonghaowen
 * @apiNote
 * @since 2017-04-05
 */
public class DriverProvider {
    private static DriverProvider ourInstance = new DriverProvider();

    public static DriverProvider getInstance() {
        return ourInstance;
    }

    private DriverProvider() {
    }

    public static WebDriver initWebDriver(DriverEnum driverEnum) {
        //设置超时时间为3S
        return initWebDriver(driverEnum, 3);
    }

    public static WebDriver initWebDriver(DriverEnum driverEnum, int timeOut) {
        WebDriver driver;
        System.setProperty(driverEnum.getDriver(), driverEnum.getPath());
        switch (driverEnum) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
        DriverUtil.init(driver);
        return driver;
    }
}
