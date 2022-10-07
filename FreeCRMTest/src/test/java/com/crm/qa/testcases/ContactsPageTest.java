package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class ContactsPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	//This class first initially run this HomePageTest() constructor to validate all pre requisites
	public ContactsPageTest () {
		super(); //This will call parent class which is TestBase constructor to load properties file
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException  {
		initialization();
		testutil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		/*This code not required, currently FreeCRM application removed all these frames
		testutil.switchToFrame(); */
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Naveen K");
	}
	
	@Test(priority=3,enabled=false)
	public void selectMultipleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Ron Weisley");
		contactsPage.selectContactsByName("Rajeev Anand");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String firstName, String lastName, String company) throws InterruptedException {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Tom", "Peter", "Google");
		contactsPage.createNewContact(firstName, lastName, company);
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		contactsPage.logOut();
		driver.quit();
	}
}