# selenium 使用出现的问题
1.org.openqa.selenium.WebDriverException: unknown error: Element `<a class="_1c2m _p _55pi _2agf _4o_4 _4jy0 _4jy3 _517h _51sy _42ft" aria-haspopup="true" role="button" href="#" aria-controls="js_2a" style="max-width: 200px;">...</a>` is not clickable at point (1054, 217). Other element would receive the click: `<div class="uiContextualLayer uiContextualLayerBelowLeft" aria-labelledby="">...</div>`
      (Session info: chrome=62.0.3202.94)
      (Driver info: chromedriver=2.33.506092 (733a02544d189eeb751fe0d7ddca79a0ee28cce4),platform=Linux 3.10.0-514.26.2.el7.x86_64 x86_64) (WARNING: The server did not provide any stacktrace information)
    Command duration or timeout: 73 milliseconds
    Build info: version: 'unknown', revision: 'unknown', time: 'unknown'
    System info: host: 'iZt4nik92jqdzoxr0aqj41Z', ip: '10.45.241.68', os.name: 'Linux', os.arch: 'amd64', os.version: '3.10.0-514.26.2.el7.x86_64', java.version: '1.8.0_112'
    Driver info: org.openqa.selenium.chrome.ChromeDriver  
    问题：上一个元素产生了一个弹出层，然后关闭有延迟，触发事件不了，此时可以用js代码触发点击事件：
    
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    js.executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//span[contains(text(), 'Get Token')]/..")));
2.点击速度过快 页面没有加载出来就需要点击页面上的元素：

    WebDriverWait webDriverWait =new WebDriverWait(webDriver, 10);
    webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Get Token')]/..")));
                    
