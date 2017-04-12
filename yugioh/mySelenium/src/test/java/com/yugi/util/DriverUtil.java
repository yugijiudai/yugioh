package com.yugi.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author zhonghaowen
 * @apiNote
 * @since 2017-04-05
 */
public class DriverUtil {

    private static WebDriverWait wait;

    private static WebDriver webDriver;

    public static void init(WebDriver driver, int timeOutSecond, int sleepInMillis) {
        webDriver = driver;
        wait = new WebDriverWait(driver, timeOutSecond, sleepInMillis);
    }

    public static void init(WebDriver driver) {
        // 最多等待10S，每2S检查一次
        webDriver = driver;
        wait = new WebDriverWait(driver, 10, 2000);
    }

    public static WebElement findByIdAwait(String id) {
        WebElement webElement = null;
        if (wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(By.id(id)).isDisplayed())) {
            webElement = webDriver.findElement(By.id(id));
        }
        return webElement;
    }

    public static WebElement findByXpathAwait(String xpath) {
        WebElement webElement = null;
        if (wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(By.xpath(xpath)).isDisplayed())) {
            webElement = webDriver.findElement(By.xpath(xpath));
        }
        return webElement;
    }

    public static WebElement findByAwait(By by) {
        WebElement webElement = null;
        if (wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(by).isDisplayed())) {
            webElement = webDriver.findElement(by);
        }
        return webElement;
    }

}
