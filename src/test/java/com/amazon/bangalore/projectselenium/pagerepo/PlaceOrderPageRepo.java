package com.amazon.bangalore.projectselenium.pagerepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.bangalore.projectselenium.testutil.WebDriverUtility;

public class PlaceOrderPageRepo extends WebDriverUtility{
	
	@FindBy(id= "twotabsearchtextbox")
    private WebElement searchTextBox;
	

	@FindBy(xpath= "//div[@id='suggestions']/div")
	private List <WebElement> autoLists;
	
	@FindBy(xpath = "//h2/a/span")
	private List<WebElement> displayList;
	
	@FindBy(xpath = "//div[@id='tmmSwatches']/ul/li[3]/span[@class='a-list-item']/span[1]//a[@role='button']")
	private WebElement paperBack;
	
	@FindBy(xpath = "//div[@id='tmmSwatches']/ul/li[2]/span[@class='a-list-item']/span[1]//a[@role='button']")
	private WebElement audiobook;
	
	@FindBy(xpath = "//div[@id='tmmSwatches']/ul/li[1]/span[@class='a-list-item']/span[1]//a[@role='button']")
	private WebElement kindleEdition;
	
	@FindBy(xpath = "//div[@id='tmmSwatches']/ul/li[4]/span[@class='a-list-item']/span[1]//a[@role='button']")
	private WebElement hardcover;
	
	@FindBy(id = "checkout-button")
	private WebElement checkOutButton;
	
	@FindBy(xpath = "//input[@value='Go']")
	private WebElement submit;
	
	@FindBy (id= "searchDropdownBox")
	private WebElement dropdownBox;
	
	

	/**
	 * Placing an order from amazon
	 * 
	 * @param itemName: name of the item
	 * @param fullDesc : full name of the item
	 * @param formatType : any one type of audio, paper, hard, kindle
	 */
	public void searchItemAndPlaceOrder(String itemName, String fullDesc, String formatType) {
		this.clickElement(searchTextBox);
		this.sendKeys(searchTextBox, itemName, true);
		this.clickAutoCompleteText(autoLists, fullDesc);
		this.clickElement(submit);
		this.clickAutoCompleteText(displayList, fullDesc);
		this.switchToNewWindow();
		waitForPageLoad();	
		logger.info("Switched to new window successfully");	
		
		if (formatType.equalsIgnoreCase("audio")) { 
			  this.clickElement(audiobook);
		      logger.info("clicked on audio format");}
		  
		if (formatType.equalsIgnoreCase("paper")) {
			  this.clickElement(paperBack); 
		      logger.info("clicked on paper format");}
		  
		if (formatType.equalsIgnoreCase("hard")) {
			  this.clickElement(hardcover);
		      logger.info("clicked on hardcopy format");}
		  
		if (formatType.equalsIgnoreCase("kindle")) {
			this.clickElement(kindleEdition);
		    logger.info("clicked on kindle format");}
	
		sleepTightInSeconds(2);
		this.clickElement(checkOutButton);
		waitForPageLoad();
	}
	
}
