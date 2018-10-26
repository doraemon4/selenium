package com.stephen.learning.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.ResourceUtils;

import java.io.File;

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

    /**
     * 加载jquery文件
     * @param driver
     */
    public static void loadJQuery(JavascriptExecutor driver,boolean islocalFile){
        if(!jQueryLoaded(driver)){
            try{
                String strJqueryMin;
                if(islocalFile){
                    strJqueryMin=FileUtils.readFileToString(new File("/opt/webdriver/chrome/jquery-1.10.2.min.js"),"utf-8");
                }else{
                    //ResourceUtils可以加载resources下的文件
                    File file=ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"jquery-1.10.2.min.js");
                    strJqueryMin=FileUtils.readFileToString(file,"utf-8");
                }
                driver.executeScript(strJqueryMin);
                //释放$符号
                //driver.executeScript("window.jQuery=jQuery.noConflict();");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static boolean jQueryLoaded(JavascriptExecutor driver) {
        boolean loaded;
        try {
            loaded = (boolean) driver
                    .executeScript("if(typeof jQuery==\"undefined\"){return false;}else{return true;}");
        } catch (WebDriverException e) {
            loaded = false;
        }
        return loaded;
    }
}
