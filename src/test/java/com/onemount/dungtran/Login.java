package com.onemount.dungtran;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Login extends WebDriverTest{
	
	String appUrl = "https://id.zing.vn/";
	public Login() {
		super();
		getUrl(appUrl);
	}
	private void login(User user) throws InterruptedException {
		WebElement username = driver.findElement(By.id("login_account"));
		username.clear();
		username.sendKeys(user.getUsername());
		WebElement password = driver.findElement(By.id("login_pwd"));
		password.clear();
		password.sendKeys(user.getPassword());
		// click button Đăng nhập
		WebElement signInButton = driver.findElement(By.className("zidsignin_btn"));
		signInButton.click();
		TimeUnit.SECONDS.sleep(3);
	}

	// Kiểm tra thông báo và đưa ra kết quả kiểm tra
	private void checkString(String actualFeedback, String desiredFeedback, String msg) {
		if (actualFeedback.equals(desiredFeedback)) {
			System.out.println(msg + "PASS");
		} else {
			System.out.println(msg + "FAIL ");
		}
	}
	private String getTextFeedback(int index) {
		String tipcontent = driver.findElements(By.xpath("//div[@class='tipcontent']")).get(index).getText();
		return tipcontent;
	}
//============================================================================
	@Test
	public void runTest() throws InterruptedException {
		checkTitle();
		loginBlankUsername();
		loginBlankPassword();
		loginInvalidPassword();
		loginWrongPassword();
		loginSuccess();
		System.out.println("Kết thúc chương trình!");

	}

//	===========================================================================
	// kiểm tra tiêu đề trang
	private void checkTitle() throws InterruptedException {
		String textTitle = "Zing ID - Zing Passport - Tài khoản Zing của VNG";
		String message = "Kiểm tra tiêu đề trang: ";
		checkString(textTitle, driver.getTitle(), message);
	}
	
	// Kiểm tra đăng nhập thành công
	private void loginSuccess() throws InterruptedException {
		String message = "Kiểm tra đăng nhập đúng tài khoản: ";
		User user = new User("dung.ttt99","Dung12345@");
		login(user);
		String url = driver.getCurrentUrl();
		String infosettingUrl = "id.zing.vn/v2/infosetting";
		if (url.contains(infosettingUrl)) {
			System.out.println(message + "PASS");
		} else {
			System.out.println(message + "FAIL");
		}
		driver.quit();
	}

	// kiểm tra đăng nhập với trường username hoặc cả hai trường để trống
	private void loginBlankUsername() throws InterruptedException {
		String textFeedback = "Bạn cần nhập thông tin này";
		String message = "Kiểm tra đăng nhập để username trống: ";
		User user = new User("","");
		login(user);
		String tipcontent = getTextFeedback(0);
		checkString(tipcontent, textFeedback, message);
		//driver.quit();
	}

	// kiểm tra đăng nhập với trường mật khẩu để trống
	private void loginBlankPassword() throws InterruptedException {
		String textFeedback = "Bạn cần nhập thông tin này";
		String mesage = "Kiểm tra đăng nhập với mật khẩu trống: ";
		User user = new User("dung.ttt99","");
		login(user);
		String tipcontent = getTextFeedback(1);
		checkString(tipcontent, textFeedback, mesage);
		//driver.quit();
	}
	// kiểm tra đăng nhập với mật khẩu không đúng định dạng

	private void loginInvalidPassword() throws InterruptedException {
		String message = "Kiểm tra đăng nhập với mật khẩu không đúng định dạng: ";
		String textFeedback = "Vui lòng nhập mật khẩu dài 6-32 ký tự, có ký tự chữ số, chữ hoa và chữ thường";
		User user = new User("dung.ttt99","Dung");
		login(user);
		String tipcontent = getTextFeedback(1);
		checkString(tipcontent, textFeedback, message);
		//driver.quit();
	}

	// Kiểm tra đăng nhập với mật khẩu sai

	private void loginWrongPassword() throws InterruptedException {
		String textFeedback = "Tài khoản hoặc mật khẩu không đúng";
		String message = "Kiểm tra đăng nhập sai mật khẩu: ";
		User user = new User("dung.ttt99","dung12345@");
		login(user);
		String tipcontent = getTextFeedback(0);
		checkString(tipcontent, textFeedback, message);
		//driver.quit();
	}

	

}
