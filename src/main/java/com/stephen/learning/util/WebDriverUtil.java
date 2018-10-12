package com.stephen.learning.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @Auther: jack
 * @Date: 2018/10/12 15:44
 * @Description:
 */
public class WebDriverUtil {
    public static WebDriver getChromeInstance(){
        DesiredCapabilities capabilities=new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-infobars");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty("webdriver.chrome.driver", "/opt/webdriver/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
