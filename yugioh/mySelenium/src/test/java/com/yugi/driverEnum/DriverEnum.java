package com.yugi.driverEnum;

/**
 * @author zhonghaowen
 * @apiNote
 * @since 2017-04-05
 */
public enum DriverEnum {

    CHROME("J:/project/yugioh/driver/chromedriver.exe", "webdriver.chrome.driver"),

    FIREFOX("J:/project/yugioh/driver/chromedriver.exe", "webdriver.gecko.driver"),

    IE("J:/project/yugioh/driver/chromedriver.exe", "webdriver.ie.driver");

    private String path;

    private String driver;

    DriverEnum(String path, String driver) {
        this.path = path;
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }


    public String getDriver() {
        return driver;
    }


    public static DriverEnum parseOfDesc(String driver) {
        DriverEnum[] values = DriverEnum.values();
        for (DriverEnum value : values) {
            if (value.getDriver().equals(driver)) {
                return value;
            }
        }
        throw new IllegalArgumentException("找不到对应的类型");
    }
}
