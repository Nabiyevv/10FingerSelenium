package com.example.tenfingerselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {

    public static WebDriver driver;
    public static void main(String[] args) throws InterruptedException
    {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://10fastfingers.com/typing-test/english");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("closeIconHit")));
        driver.findElement(By.id("closeIconHit")).click();

        driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();

        List<WebElement> words = driver.findElements(new By.ByCssSelector("#row1 > span"));
        for (WebElement word : words) {
            WebElement input = driver.findElement(By.id("inputfield"));
            input.sendKeys(word.getText(),Keys.SPACE);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        Thread.sleep(Duration.ofSeconds(10));

        driver.quit();

    }
}
