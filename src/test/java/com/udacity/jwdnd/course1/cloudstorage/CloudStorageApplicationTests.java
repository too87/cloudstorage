package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
		//Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}



	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	private void doLogOut()
	{
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout-button")));
		WebElement logoutButton = driver.findElement(By.id("logout-button"));
		logoutButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Login"));

	}

	private void doMockCreateNote(String title, String desc) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
		noteTab.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-note")));
		WebElement newNoteButton = driver.findElement(By.id("new-note"));
		newNoteButton.click();


		//fill in note title
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement nodeTitle = driver.findElement(By.id("note-title"));
		nodeTitle.click();
		nodeTitle.sendKeys(title);


		//fill in note description
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.sendKeys(desc);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("node-submit")));
		WebElement submitButton = driver.findElement(By.id("node-submit"));
		submitButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-back-home")));
		WebElement backLink = driver.findElement(By.id("success-back-home"));
		backLink.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-note")));
	}

	private void doMockCreateCredential(String url, String username, String password) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
		credentialTab.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-credential")));
		WebElement newCredentialButton = driver.findElement(By.id("new-credential"));
		newCredentialButton.click();

		//fill in url
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.click();
		credentialUrl.sendKeys(url);

		//fill in username
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement credentialUsername = driver.findElement(By.id("credential-username"));
		credentialUsername.click();
		credentialUsername.sendKeys(username);

		//fill in password
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.click();
		credentialPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-submit")));
		WebElement submitButton = driver.findElement(By.id("credential-submit"));
		submitButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-back-home")));
		WebElement backLink = driver.findElement(By.id("success-back-home"));
		backLink.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-credential")));
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling redirecting users
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric:
	 * https://review.udacity.com/#!/rubrics/2724/view
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");

		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 *
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code.
	 *
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}
	@Test
	public void testUnauthorizedUserShouldRedirectToLogin() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/home");

		Assertions.assertTrue(driver.getPageSource().contains("Login"));
	}
	@Test
	public void testUnauthorizedUserShoulBeAbleToSeeSignUpAndLogin() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		Assertions.assertTrue(driver.getPageSource().contains("Sign Up"));

		driver.get("http://localhost:" + this.port + "/login");
		webDriverWait.until(ExpectedConditions.titleContains("Login"));

		Assertions.assertTrue(driver.getPageSource().contains("Login"));
	}

	@Test
	public void testAuthorizedUserShouldAbleToAccessHomePage() {
		// Create a test account
		doMockSignUp("Valid user","Test","VU","1234");
		doLogIn("VU", "1234");

		Assertions.assertTrue(driver.getPageSource().contains("Home"));

		doLogOut();
		Assertions.assertFalse(driver.getPageSource().contains("Home"));
	}

	@Test
	public void testCreateNote() {
		// Create a test account
		doMockSignUp("Create note","Test","CN","1234");
		doLogIn("CN", "1234");

		doMockCreateNote("test-title", "test-description");

		Assertions.assertTrue(driver.getPageSource().contains("test-title"));
		Assertions.assertTrue(driver.getPageSource().contains("test-description"));

	}

	@Test
	public void testEditNote() {
		// Create a test account
		doMockSignUp("Edit note","Test","EN","1234");
		doLogIn("EN", "1234");

		doMockCreateNote("test-title", "test-description");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-note")));
		WebElement editButton = driver.findElement(By.id("edit-note"));
		editButton.click();


		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement nodeTitle = driver.findElement(By.id("note-title"));
		nodeTitle.clear();
		nodeTitle.click();
		nodeTitle.sendKeys("new-title");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.clear();
		noteDescription.click();
		noteDescription.sendKeys("new-description");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("node-submit")));
		WebElement submitButton = driver.findElement(By.id("node-submit"));
		submitButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-back-home")));
		WebElement backLink = driver.findElement(By.id("success-back-home"));
		backLink.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-note")));

		Assertions.assertFalse(driver.getPageSource().contains("test-title"));
		Assertions.assertFalse(driver.getPageSource().contains("test-description"));
		Assertions.assertTrue(driver.getPageSource().contains("new-title"));
		Assertions.assertTrue(driver.getPageSource().contains("new-description"));
	}

	@Test
	public void testDeleteNote() {
		// Create a test account
		doMockSignUp("Delete note","Test","DN","1234");
		doLogIn("DN", "1234");

		doMockCreateNote("test-title", "test-description");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-note")));
		WebElement deleteButton = driver.findElement(By.id("delete-note"));
		deleteButton.click();

		driver.switchTo().alert().accept();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-back-home")));
		WebElement backLink = driver.findElement(By.id("success-back-home"));
		backLink.click();

		Assertions.assertFalse(driver.getPageSource().contains("test-title"));
		Assertions.assertFalse(driver.getPageSource().contains("test-description"));
	}

	@Test
	public void testCreateCredential() {
		// Create a test account
		doMockSignUp("Create credential","Test","CC","1234");
		doLogIn("CC", "1234");
		doMockCreateCredential("test-url", "CC", "12er344");
		doMockCreateCredential("test-url2", "CC", "wrrrp09!");

		// verify password is encrypted
		Assertions.assertFalse(driver.findElements(By.id("encrypted-password")).stream()
				.anyMatch(c -> c.getText().equals("12er344") || c.getText().equals("wrrrp09")));

		Assertions.assertTrue(driver.getPageSource().contains("test-url"));
		Assertions.assertTrue(driver.getPageSource().contains("CC"));
	}

	@Test
	public void testEditCredential() {
		// Create a test account
		doMockSignUp("Edit credential","Test","EC","1234");
		doLogIn("EC", "1234");
		doMockCreateCredential("test-url", "EC", "!@io#edlkls");
		doMockCreateCredential("test-url2", "EC", "(0#2ej[opi)!");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-credential")));

		String encrypt = driver.findElements(By.id("encrypted-password")).get(1).getText();

		WebElement editCredentialButton = driver.findElements(By.id("edit-credential")).get(1);
		editCredentialButton.click();

		// verify password is unencrypted
		Assertions.assertEquals("(0#2ej[opi)!", driver.findElement(By.id("credential-password")).getAttribute("value"));

		//fill in username
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement credentialUsername = driver.findElement(By.id("credential-username"));
		credentialUsername.clear();
		credentialUsername.click();
		credentialUsername.sendKeys("new-username");

		//fill in password
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.clear();
		credentialPassword.click();
		credentialPassword.sendKeys("90fjjki2!");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-submit")));
		WebElement submitButton = driver.findElement(By.id("credential-submit"));
		submitButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-back-home")));
		WebElement backLink = driver.findElement(By.id("success-back-home"));
		backLink.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-credential")));

		String newPasswordEncrypt = driver.findElements(By.id("encrypted-password")).get(1).getText();

		//verify changed
		Assertions.assertNotEquals(encrypt, newPasswordEncrypt);
		Assertions.assertTrue(driver.getPageSource().contains("new-username"));
	}

	@Test
	public void testDeleteCredential() {
		// Create a test account
		doMockSignUp("Delete credential","Test","DC","1234");
		doLogIn("DC", "1234");

		doMockCreateCredential("abc.com", "EC", "!@io#edlkls");
		doMockCreateCredential("test-url2", "EC", "(0#2ej[opi)!");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-credential")));
		WebElement deleteButton = driver.findElements(By.id("delete-credential")).get(1);
		deleteButton.click();

		driver.switchTo().alert().accept();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-back-home")));
		WebElement backLink = driver.findElement(By.id("success-back-home"));
		backLink.click();

		Assertions.assertFalse(driver.getPageSource().contains("test-url2"));
		Assertions.assertTrue(driver.getPageSource().contains("abc.com"));
	}

}
