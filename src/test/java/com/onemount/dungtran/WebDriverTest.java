package com.onemount.dungtran;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTest {
	protected static WebDriver driver;
	
	protected WebDriverTest() {
		super();
	}

	protected void getUrl(String url) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\dung.tran8_onemount\\Downloads\\Webdriver\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.get(url);
	}
}