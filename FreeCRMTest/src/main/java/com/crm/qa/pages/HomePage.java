package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	Actions action;
	
	@FindBy(xpath="//span[contains(text(),'Rajeev Anand K.R')]")
	@CacheLookup /*Now It will store our webObject in the cache memory, 
				 If not mention this annotation in WebElements, every time it will check in HTML DOM. so it will increase the speed of the Test Execution.
				 Disadvantages: Sometimes if the pages got refreshed or if DOM property got changed, then this cache memory got corrupted, then we may face StaleElementException*/
	WebElement userNameLabel;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[@href='/contacts']//following-sibling::button[contains(@class,'ui mini basic icon button')]")
	WebElement newcontactLink;
	
	@FindBy(xpath="//span[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//span[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath="//b[contains(text(),'Hireteks India Private Limited')]")
	WebElement focusOtherElement;
	
	//To initialize the above WebElements (Initializing the Page Objects), create constructor for that and call the method of initElements
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink() throws InterruptedException {
		contactsLink.click();
		Thread.sleep(3000);
		//Creating object of an Actions class
		action = new Actions(driver);

		//Performing the mouse hover action on the target element.
		action.moveToElement(focusOtherElement).build().perform();
		Thread.sleep(3000);
		return new ContactsPage(); // To validate ContactsPage WebObjects after click of Contacts link
	}
	
	public DealsPage clickOndealsLink() {
		dealsLink.click();
		return new DealsPage(); // To validate DealsPage WebObjects after click of Deals link
	}
	
	public TasksPage clickOntasksLink() {
		tasksLink.click();
		return new TasksPage(); // To validate TasksPage WebObjects after click of Tasks link
	}
	
	public void clickOnNewContactLink() throws InterruptedException {
		//Creating object of an Actions class
		action = new Actions(driver);
		
		//Performing the mouse hover action on the contactsLink element.
		action.moveToElement(contactsLink).build().perform();
		newcontactLink.click(); //click + icon in webpage
		
		//Performing the mouse hover action on the other element to make menu close automatically.
		action.moveToElement(focusOtherElement).build().perform();
		Thread.sleep(3000);
	}
}