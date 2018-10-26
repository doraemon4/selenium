package com.stephen.learning;

import com.stephen.learning.constant.SinaWeiboConstant;
import com.stephen.learning.util.HttpclientUtil;
import com.stephen.learning.util.WebDriverUtil;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
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
        String cookies="**********************此处马赛克*********************";
        Map<String, String> cookieMap = HttpclientUtil.convertJsonToMap(cookies);

        webDriver.get(SinaWeiboConstant.HOME_PAGE);
        //设置cookie
        webDriver.manage().deleteAllCookies();
        //设置浏览器最大化
        webDriver.manage().window().maximize();
        for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
            webDriver.manage().addCookie(new Cookie(
                    entry.getKey(), entry.getValue(),SinaWeiboConstant.DOMIAN, "/", null, false, false
            ));
        }

        sendMessage(webDriver,"test");
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){

        }finally {
            if(webDriver!=null){
                webDriver.quit();
            }
        }
    }

    public void sendMessage(WebDriver webDriver,String content){
        webDriver.get(SinaWeiboConstant.HOME_PAGE);
        //等待10秒直到显示粉丝的链接
        WebDriverWait webDriverWait =new WebDriverWait(webDriver, 10);
        WebElement element=webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"v6_pl_rightmod_myinfo\"]/div/div/div[2]/ul/li[2]/a")));
        String href=element.getAttribute("href");
        webDriver.get(href);
        //查找粉丝列表
        WebElement followElement=webDriver.findElement(By.xpath("//*[@id=\"Pl_Official_RelationFans__87\"]//ul[@class='follow_list']"));
        List<WebElement> elements=followElement.findElements(By.xpath("li"));
        elements.forEach(webElement->{
            try{
                //触发鼠标mouseover事件
                TimeUnit.SECONDS.sleep(1);
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                //WebDriverUtil.loadJQuery(js,true);
                js.executeScript("arguments[0].scrollIntoView(true)",webElement.findElement(By.xpath("dl/dd[2]/a")));
                //js.executeScript("$(arguments[0]).mouseover();",webElement.findElement(By.xpath("dl/dd[2]/a")));
                //webElement.findElement(By.xpath("dl/dd[2]/a")).click();

                js.executeScript("arguments[0].click()",webElement.findElement(By.xpath("dl/dd[2]//a[contains(text(), '私信')]")));
                TimeUnit.SECONDS.sleep(1);
                webDriver.findElement(By.xpath("//*[@id=\"WB_webim\"]//textarea")).sendKeys(content);
                webDriver.findElement(By.xpath("//*[@id=\"WB_webim\"]//textarea")).sendKeys(Keys.ENTER);
                //按下回车键
                //Actions action = new Actions(webDriver);
                //action.sendKeys(Keys.ENTER);
                webDriver.findElement(By.xpath("//*[@id=\"WB_webim\"]//a[@node-type='_closeBtn']")).click();
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }

        });

    }

}
