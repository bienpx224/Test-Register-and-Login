package com.onemount.dungtran;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Register extends WebDriverTest {

	String appUrl = "https://secure.vietnamworks.com/register/vi?client_id=3";

	public Register() {
		super();
		getUrl(appUrl);

	}

	private void provideInfo(Member member) throws InterruptedException {
		WebElement firstname = driver.findElement(By.id("firstname"));
		firstname.clear();
		firstname.sendKeys(member.getFirstname());
		WebElement lastname = driver.findElement(By.id("lastname"));
		lastname.clear();
		lastname.sendKeys(member.getLastname());
		WebElement email = driver.findElement(By.id("username"));
		email.clear();
		email.sendKeys(member.getEmail());
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys(member.getPassword());
		WebElement registerButton = driver.findElement(By.id("button-register"));
		registerButton.click();
		TimeUnit.SECONDS.sleep(1);
	}

	private void checkFeedback(String actualFeedback, String feedback, String resultCheckList) {
		if (actualFeedback.equals(feedback)) {
			System.out.println(resultCheckList + "PASS");
		} else {
			System.out.println(resultCheckList + "FAIL ");
		}
	}

	private String getMessageInvalid() {
		String inValidFeedback = driver.findElements(By.xpath("//span[@class='invalid-feedback']")).get(0).getText();
		return inValidFeedback;
	}

	// ==========================================================================
	@Test
	public void runTest() throws InterruptedException {
		checkBlankFirstname();
		checkBlankLastname();
		checkBlankEmail();
		checkInValidEmail();
		checkBlankPassword();
		checkShortPassword();
		checkInvalidPassword();
		checkRegisterConfirm();
		driver.quit();

	}
	// ==========================================================================

	private void checkBlankFirstname() throws InterruptedException {
		String msgBlankFirstname = "Kiểm tra đăng kí để trống các trường: ";
		String blankFirstname = "Vui lòng nhập Tên.";
		Member member = new Member("", "", "", "");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, blankFirstname, msgBlankFirstname);
	}

	private void checkBlankLastname() throws InterruptedException {
		String blankLastname = "Vui lòng nhập Họ.";
		String msgBlankLastname = "Kiểm tra đăng kí để trống họ: ";
		Member member = new Member("Dung", "", "", "");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, blankLastname, msgBlankLastname);
	}

	private void checkBlankEmail() throws InterruptedException {
		String msgBlankEmail = "Kiểm tra đăng kí để trống email: ";
		String blankEmail = "Vui lòng nhập Email";
		Member member = new Member("Dung", "Trần", "", "");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, blankEmail, msgBlankEmail);
	}

	private void checkBlankPassword() throws InterruptedException {
		String blankPassword = "Vui lòng nhập Mật Khẩu";
		String msgBlankPassword = "Kiểm tra đăng kí để trống mật khẩu: ";
		Member member = new Member("Dung", "Trần", RandomUtils.randomEmail(), "");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, blankPassword, msgBlankPassword);
	}

	private void checkInValidEmail() throws InterruptedException {
		String msgInvalidEmail = "Kiểm tra đăng kí email không hợp lệ: ";
		String inValidEmail = "Email không hợp lệ.";
		Member member = new Member("Dung", "Trần", "dungttt", "");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, inValidEmail, msgInvalidEmail);
	}

	private void checkInvalidPassword() throws InterruptedException {
		String inValidPassword = "Mật Khẩu không hợp lệ";
		String msgInvalidPassword = "Kiểm tra đăng kí mật khẩu không hợp lệ: ";
		Member member = new Member("Dung", "Trần", RandomUtils.randomEmail(), "ggggggggggg");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, inValidPassword, msgInvalidPassword);
	}

	private void checkShortPassword() throws InterruptedException {
		String shortPassword = "Mật Khẩu phải có ít nhất 6 ký tự.";
		String msgShortPassword = "Kiểm tra đăng kí với mật khẩu ngắn: ";
		Member member = new Member("Dung", "Trần", RandomUtils.randomEmail(), "gggg");
		provideInfo(member);
		String inValidFeedback = getMessageInvalid();
		checkFeedback(inValidFeedback, shortPassword, msgShortPassword);
	}

	private void checkRegisterConfirm() throws InterruptedException {
		String msgConfirmRegister = "Kiểm tra đăng kí hợp lệ, chuyển sang trang xác thực email: ";
		Member member = new Member("Dung", "Trần", RandomUtils.randomEmail(), RandomUtils.randomPassword());
		provideInfo(member);
		String url = driver.getCurrentUrl();
		String urlCofirmRegister = "https://secure.vietnamworks.com/register/vi/confirm?client_id=3";

		if (url.contains(urlCofirmRegister)) {
			System.out.println(msgConfirmRegister + "PASS");
		} else {
			System.out.println(msgConfirmRegister + "FAIL");
		}
	}

}
