package com.stephen.learning;

import com.stephen.learning.util.WebDriverUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: jack
 * @Date: 2018/10/12 15:54
 * @Description:
 */
public class App {
    @Test
    public void test(){
        WebDriver webDriver=WebDriverUtil.getChromeInstance();
        webDriver.get("https://www.baidu.com");
        WebElement element=webDriver.findElement(By.xpath("//*[@id=\"kw\"]"));
        element.sendKeys("淘宝");
        webDriver.findElement(By.xpath("//*[@id=\"su\"]")).click();
        //设置等待的时长，最长10S
        //WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(""))).click();
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){

        }finally {
            if(webDriver!=null){
                webDriver.quit();
            }
        }
    }
}
