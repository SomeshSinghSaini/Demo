package com;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NaukriCVUpdater {
	static WebDriver driver;
	static {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
	}
	@BeforeMethod
	void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	@AfterMethod
	void closeBrowser() {
		driver.close();
	}
	@Test
	public static void updateCV() throws InterruptedException {
		driver.get("https://www.naukri.com/nlogin/login");
		driver.findElement(By.id("usernameField")).sendKeys("somesh.s.saini@gmail.com");
		driver.findElement(By.id("passwordField")).sendKeys("somesh007");
		driver.findElement(By.id("passwordField")).submit();
		driver.findElement(By.xpath("//a//div[.='Somesh Singh Saini']")).click();
		driver.findElement(By.linkText("DELETE RESUME")).click();
		driver.findElement(By.linkText("CANCEL")).findElement(By.xpath("./..//button[.='DELETE']")).click();
		String relativePath = "\\src\\test\\resources\\Somesh-Automation-Test-Engineer-3years6months-experience.docx";
		String absolutePath = System.getProperty("user.dir") + relativePath;
		Reporter.log(absolutePath);
		driver.findElement(By.id("attachCV")).sendKeys(absolutePath);
		Thread.sleep(3000);
		driver.navigate().refresh();
	}
}
