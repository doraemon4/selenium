package com.stephen.learning;

import com.stephen.learning.constant.FacebookConstant;
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
    public void test() throws Exception{
        String cookies="SID=jQffrfg8t1onD16hpUfl6xJ5aJlUEYkuxDCLQP44Gq2CysEmv61TiMXsaeRbykj25-MGKw.; HSID=Av34gVHtlMrcG_mKD; SSID=AhYbFGhKqBGKuWyR0; APISID=80UAwbOcpgZ32m83/AYS8x8G7Veulna625; SAPISID=yhGepKB1I0gYFthv/AzM_gLy0l9ByGrh8E; NID=186=Lpk5vTE9Re1jkyTkQXl9RWKHneXKE-UavSzkKVqjlZgVnc_ytFy4wZ8Vv4zG23zbscdGOtKCiMM1ZDI2raaP9HkwHMcX1NrwMniSCrs0tq1EnFOOV6vLXMus9vlryP4JvuxJZezYVmo1af8N-bWX28AvEXcntaDX_pVKrg-nh8oFZlWy; OSID=jQffrfyi5Bj0jDU41SXwRrxpPPpauEgKY7L3G4DoBpvK2A_fgrCIUXY4g1Xb7Fp7uZ9G-A.; 1P_JAR=2019-6-21-6; SIDCC=AN0-TYuaAg4_xi3aJHep-x_35YZ7Hixl0tw-SE99Fxu9yu1XWM_yCrFg2qT64M3WajB6Y3IfFQ";
        //String cookies="SID=UAdzQ4GxW9_KHbIwilhHkeHZ8q23y7fGMSS730IIucmBbRi436rL6AldbO1EEhMIovo3AA.; HSID=ArCVENRM_ol025Mtl; SSID=A_pJA1XKwukxLABjM; APISID=oRPak50iLdfboV7v/Ai7cYynX18HgzZqru; SAPISID=QSLMqgNPvyx3r4kX/AymLOJ59CpN3m7Pab; NID=181=OB96w7EzwSmaomKdRvAOkqqAab3Pz6kzi6_gCns0e2EARCofNMrh9oc6YHcjrqxqIr9b31c20LgRn72grEYAlq0rvYkgOw4imLJUhnFDEqWaph5UZ1gqLaV1ybF0ddjYPG8Cmf7IattKpnVSxvJM7gs68OL5YJ7zHvzQLcIjh9g; OSID=UAdzQxnpy7Mbklgff_iQHfigtfRBp5sfDcOLTeQ0VelEJK4yjgJCYoaUngGDTslhhvNJvA.; 1P_JAR=2019-4-17-3; __utma=245730968.810515484.1555470104.1555470104.1555470104.1; __utmc=245730968; __utmz=245730968.1555470104.1.1.utmcsr=accounts.google.com|utmccn=(referral)|utmcmd=referral|utmcct=/CheckCookie; __utmt_t0=1; __utmb=245730968.1.10.1555470104; SIDCC=AN0-TYufOBxZ6abZy9GzxR4PQ9gMBre10ckM2NlnFZGxj2jxVSRQ6kWr7Gq-o7wWy1lxC8zD";
        //String cookies="_vz=viz_5cad550ac50f1; ins-gaSSId=0a0cf435-2b6a-70ac-249d-544b8924bd9b_1554863330; scs=%7B%22t%22%3A1%7D; ins-mig-done=1; _fbp=fb.1.1554863332491.2075916912; __bwa_session_action_sequence=3; __bwa_session_id=948634023.S.8487515356960896.1554863332; __bwa_user_id=948634023.U.4127347884654590.1554863332; _ga=GA1.2.666699669.1554863330; _gid=GA1.2.1348129120.1554863332; bm_sv=8FC68F0939FB0F96ED1EE073E9EE65A8~xXw8MZxmuEtjKAfQoO+15xjoc+O4G9uvD6EaaJ0FoUuQB35BBykgUkh/z+8LxXHYtzc7S1anYqgkWe7hMR7WzH6No/iVedH0x7HAVp1t+ABT6H2gS9gCRU1DTqM75IR87gqHLI/dZnogq3Xci0fIckj9XhvjXam1l30yGPz1YgE=; insdrSV=3; _gat=1; __asc=6c939cfe16a05141a883afde37d; __auc=6c939cfe16a05141a883afde37d; G_ENABLED_IDPS=google; userId=511372274; __utma=205442883.666699669.1554863330.1554863330.1554863330.1; __utmb=205442883.2.10.1554863330; __utmc=205442883; __utmz=205442883.1554863330.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _vis_opt_test_cookie=1; _vwo_uuid_v2=DA90FFCF02066E0A5C81F0178CCACF729|47aba4faf8ce7449ae1158b2efe9e1a2; Blibli-Is-Member=true; Blibli-Is-Remember=false; Blibli-Session-Id=c36e1461-63da-41f6-8808-cf084baddc6a; Blibli-Signature=8419a03d618bc130635a9a3ff9652176fd360124; Blibli-User-Id=yushengchu%40aliyun.com; JSESSIONID=489CC045F47BDB3B2CB87538E72F2EC6; TS018fffae=013b9c582c10a4e44cc612b98612c4dcc4fe119a9eaef23afc0a4b34f2a2bb9722866171504a8e4a5b8439ce0b220326c4d90a5e11; __bwa_user_session_sequence=1; _dc_gtm_UA-21718848-13=1; _gat_UA-21718848-13=1; cto_lwid=fe45b150-97e9-4029-a2b6-5920830fa497; __utmt=1; ak_bmsc=2F5C23F0E89FAEB5223E0C8738E5EE0417D2D775CC480000DF54AD5C584A9611~pl3LIYyeU456hH321xJ182+32PCgfFdtvD2Dn4sCtu05qMhcgyAUXCRBm8AKkq57s3s/FMRH/m09p3CZ468cx6uiXv69Ey75/p/PbylUwVdq/owzps0D14gG5GQt/srNfVNGu32x/i+CRbFpinC9G41cp02jve38IHDNo1NJ9UIqz1RakrzvVAIzM+BLHvvOa8Vt9R9Q9f+BwmKGyT9mJsDce3GWH383WQbEJjfRUDm0QTZmuxIrGlrM2wd78AaSR5; _abck=9B1805FE2AC5BCF492A684DDE3D88C8817D2D775CC480000DF54AD5CB2C1DD26~0~fMDjFTuiko1x0LirKgOLVAvJcsurnR1i1TvsUUugVBM=~-1~-1; _vis_opt_s=1%7C; bm_sz=3762317A366FC52A9A7B2A352AAE4223~YAAQddfSFwtGDOxpAQAAK4gTBQPNRQVdnx/hMQh90ALAd1GTxZ0okBOK1mugxy8LouFQiOjCtgOFLzZa/7ONX5Sev3luf/u2oFiAsadsnn1rRGn+x99060Hx2uK1FRkq7hq5DJ1w08OvsxDd3AHN1RICIlbcbCatlmsQO8vzPXgdK3nL1HIk6f1TyNwNSvFk";
        WebDriver webDriver=WebDriverUtil.getChromeInstance();
        Map<String, String> cookieMap = HttpclientUtil.convertJsonToMap(cookies);

        webDriver.get(FacebookConstant.HOME_PAGE);
        //设置cookie
        webDriver.manage().deleteAllCookies();
        //设置浏览器最大化
        //webDriver.manage().window().maximize();
        for (Map.Entry<String, String> entry : cookieMap.entrySet()) {
            webDriver.manage().addCookie(new Cookie(
                    entry.getKey(), entry.getValue(),FacebookConstant.DOMIAN, "/", null, false, false
            ));
        }

        webDriver.get(FacebookConstant.HOME_PAGE);
        //进入个人信息设置页面
        webDriver.get("https://www.facebook.com/settings?tab=your_facebook_information");
        //点击下载，进入下载页面
        webDriver.findElement(By.xpath("//*[@id='u_0_8']/..")).click();
        TimeUnit.SECONDS.sleep(1000);
        webDriver.findElement(By.xpath("//*[contains(text(),'Deselect All')]")).click();
    }

    public void sendMessage(WebDriver webDriver,String uid,String content){
        webDriver.get("https://weibo.com/"+uid+"/fans?rightmod=1&wvr=6");
        //等待10秒直到显示粉丝的链接
        WebDriverWait webDriverWait =new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[starts-with(@id,\"Pl_Official_HisRelation__\")]//ul[@class='follow_list']")));

        //查找粉丝列表
        WebElement followElement=webDriver.findElement(By.xpath("//*[starts-with(@id,\"Pl_Official_HisRelation__\")]//ul[@class='follow_list']"));
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
                TimeUnit.SECONDS.sleep(10);
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
