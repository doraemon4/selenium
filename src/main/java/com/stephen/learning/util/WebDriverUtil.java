package com.stephen.learning.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;

/**
 * @Auther: jack
 * @Date: 2018/10/12 15:44
 * @Description:
 */
public class WebDriverUtil {
    public static WebDriver getChromeInstance(){
        //设置下载路径
        String downloadsPath = "/tmp/file/facebook";
        HashMap<String, Object> chromePrefs = new HashMap();
        chromePrefs.put("download.default_directory", downloadsPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        //options.addArguments("--proxy-server=http://" + "150.109.167.161:8888");
        DesiredCapabilities capabilities=new DesiredCapabilities();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("150.109.167.161:8888");
        capabilities.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
        System.setProperty("http.nonProxyHosts", "localhost");
        capabilities.setCapability("proxy", proxy);

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty("webdriver.chrome.driver", "/opt/webdriver/chrome/chromedriver");
        WebDriver webDriver = new ChromeDriver(capabilities);
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
