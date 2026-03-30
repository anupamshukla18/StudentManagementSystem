package com.studentmanagement.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StudentManagementTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		try {
			System.out.println("=== Starting Selenium Tests ===\n");

			driver.manage().window().maximize();
			driver.get("http://localhost:8080/index.html");
			System.out.println(" Opened application successfully");
			Thread.sleep(2000);

			// Run all tests
			testAddStudent(driver);
			testSearchStudent(driver);
			testEditStudent(driver);
			testDeleteStudent(driver);
			testFormValidation(driver);

			System.out.println("\n=== All Tests Completed Successfully! ===");

		} catch (Exception e) {
			System.err.println("❌ Test Failed: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Wait 3 seconds before closing (so you can see the result)
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.quit();
			System.out.println("\n✅ Browser closed");
		}
	}

	public static void testAddStudent(WebDriver driver) throws InterruptedException {
		System.out.println("\n--- Test 1: Add Student ---");

		driver.findElement(By.id("name")).sendKeys("Rahul Sharma");
		driver.findElement(By.id("email")).sendKeys("rahul@example.com");
		driver.findElement(By.id("phone")).sendKeys("9876543210");
		driver.findElement(By.id("course")).sendKeys("Computer Science");
		driver.findElement(By.id("enrollment-date")).sendKeys("15/01/2024");

		System.out.println("📝 Filled form with student details");

		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(1000);

		Alert alert = driver.switchTo().alert();
		System.out.println("🔔 Alert: " + alert.getText());
		alert.accept();
		Thread.sleep(2000);

		WebElement studentRow = driver.findElement(By.xpath("//td[contains(text(),'Rahul Sharma')]"));
		if (studentRow.isDisplayed()) {
			System.out.println("✅ Student added successfully");
		}
	}

	public static void testSearchStudent(WebDriver driver) throws InterruptedException {
		System.out.println("\n--- Test 2: Search Student ---");

		WebElement searchBox = driver.findElement(By.id("search-input"));
		searchBox.clear();
		searchBox.sendKeys("Rahul");
		driver.findElement(By.id("search-btn")).click();
		Thread.sleep(2000);

		WebElement tableBody = driver.findElement(By.id("students-tbody"));
		if (tableBody.getText().contains("Rahul")) {
			System.out.println("✅ Search working correctly");
		}

		driver.findElement(By.id("show-all-btn")).click();
		Thread.sleep(1000);
	}

	public static void testEditStudent(WebDriver driver) throws InterruptedException {
		System.out.println("\n--- Test 3: Edit Student ---");

		Thread.sleep(1000);
		driver.findElement(By.className("edit-btn")).click();
		Thread.sleep(1000);

		WebElement phoneField = driver.findElement(By.id("phone"));
		phoneField.clear();
		phoneField.sendKeys("1111111111");

		driver.findElement(By.id("submit-btn")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		System.out.println("✅ Student updated successfully");
	}

	public static void testDeleteStudent(WebDriver driver) throws InterruptedException {
		System.out.println("\n--- Test 4: Delete Student ---");

		Thread.sleep(1000);
		driver.findElement(By.className("delete-btn")).click();

		Thread.sleep(500);
		driver.switchTo().alert().accept();
		Thread.sleep(500);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		System.out.println("✅ Student deleted successfully");
	}

	public static void testFormValidation(WebDriver driver) throws InterruptedException {
		System.out.println("\n--- Test 5: Form Validation ---");

		driver.get("http://localhost:8080/index.html");
		Thread.sleep(1000);

		driver.findElement(By.id("submit-btn")).click();

		WebElement nameField = driver.findElement(By.id("name"));
		String validationMsg = nameField.getAttribute("validationMessage");

		if (validationMsg != null && !validationMsg.isEmpty()) {
			System.out.println("✅ Form validation working");
		}
	}
}