package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testutil;
	ContactsPage contactsPage;
	
	//This class first initially run this HomePageTest() constructor to validate all pre requisites
	public HomePageTest() {
		super(); //This will call parent class which is TestBase constructor to load properties file
	}
	
	//TestCases should be separated -- independent with each other
	// * TestNG will perform Before each test case -- launch the browser and do the login
	// * @Test -- will execute test cases
    // * TestNG will perform After each test case -- close the browser
	
	@BeforeMethod
	public void setUp() throws InterruptedException  {
		initialization();
		testutil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRM", "Home page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest() {
		/*This code not required, currently FreeCRM application removed all these frames
		testutil.switchToFrame(); */ 
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() throws InterruptedException {
		/*This code not required, currently FreeCRM application removed all these frames
		testutil.switchToFrame(); */
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}