package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	Actions action;
	
	@FindBy(xpath="//div[text()='Contacts']")
	WebElement contactsLabel;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(name="middle_name")
	WebElement middleName;
	
	@FindBy(xpath="//div[@name='company']//input[@class='search']")
	WebElement company;
	
	@FindBy(xpath="//button[@class='ui linkedin button']")
	WebElement saveBtn;
	
	@FindBy(xpath="//div[@role='listbox']//i[@class='settings icon']")
	WebElement settingsIcon;
	
	@FindBy(xpath="//span[text()='Log Out']")
	WebElement logoutBtn;
	
	
	
	//This below WebElement is not good practice, because this will select only this Naveen K particular, In future, if we want to change, then this locator should also needs to be changed.
	/*@FindBy(xpath="//a[contains(text(),'Naveen K')]/parent::td//preceding-sibling::td//div[@class='ui fitted read-only checkbox']/label")
	WebElement contactsNameChkbx;*/
	
	//To initialize the above WebElements (Initializing the Page Objects), create constructor for that and call the method of initElements
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectContactsByName(String name) throws InterruptedException {
		//driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td//preceding-sibling::td//div[@class='ui fitted read-only checkbox']//input")).click();
		//And I have used JavascriptExecutor due to ===> ::before is a pseudo element not actually in the DOM and facing ElementClickInterceptedException: ===> Better to use JavaScriptExecutor in this case
		WebElement contactsNameChkbx = driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td//preceding-sibling::td//div[@class='ui fitted read-only checkbox']//input"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", contactsNameChkbx);
		Thread.sleep(3000);
	}
	
	//public void createNewContact(String title, String ftName, String ltName, String comp) { ===> Title was not currently present in FreeCRM.com Web site.
	public void createNewContact(String ftName, String ltName, String comp) {
		//Currently not available to select the Title using Select class like below:
		/*Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);*/
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		saveBtn.click();
	}
	
	public void logOut() throws InterruptedException {
		//Creating object of an Actions class
		action = new Actions(driver);
		
		settingsIcon.click();
		Thread.sleep(3000);
		action.moveToElement(logoutBtn).build().perform();
		logoutBtn.click();
		Thread.sleep(3000);	
	}	
}