package com.amazon.bangalore.projectselenium.testutil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author gvprasad
 *
 * This is selenium webdriver layer
 */
public class WebDriverUtility extends BaseSeleniumTest{
	WebDriverWait webDriverWait;
	Actions actions;
	Select select;
	Alert alert;
	
	
	/**
	 * Type the text in text box area
	 * 
	 * @param webElement WebElement
	 * @param text Text to send it
	 * @param clearBeforeSend Want to clear the text before sending text, if Yes give the boolean TRUE or other wise No give the boolean as FALSE.
	 */
	public void sendKeys(WebElement webElement, String text, boolean clearBeforeSend) {
		 if (clearBeforeSend)
			 webElement.clear();
		 webElement.sendKeys(text);
	    }
	
	/**
	 * Click on Button and it should be enabled and displayed
	 * 
	 * @param webElement WebElement
	 */
	public void clickOnButton(WebElement webElement) {
		if(webElement.isDisplayed() && webElement.isEnabled())
			webElement.click();
	}
				
    /**
     * Wait for Element present
     * 
     * @param webElement WebElement
     */
    public void waitForElementPresent(WebElement webElement){
        try {
        	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
        	webDriverWait = new WebDriverWait(driver, 20);
        	webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
        	webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
        }
        catch (Exception e) {
                throw new RuntimeException("Not getting (waited for) the text in the specified time: ", e);
            }
    } 
    
    /**
     * Wait for element up to invisible state
     * 
     * @param locator Element Locator
     * @param text Text of the WebElement
     */
    public void waitForElementInvisible(By locator, String text){
        try {
        	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
        	webDriverWait = new WebDriverWait(driver, 60);
        	webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
        	webDriverWait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
        	// logger.info("successfully waited for invisibility of text: <"+ text +" >");
        	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
        }
        catch (Exception e) {
                throw new RuntimeException("Not getting (waited for) the text in the specified time: ", e);
            }
    }
    
    
    /**
     * Wait for element up to invisible state
     * 
     * @param locator Element Locator
     * @param text Text of the WebElement
     */
    public void waitForElementInvisible(By locator){
        try {
        	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
        	webDriverWait = new WebDriverWait(driver, 60);
        	webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
        	webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        	// logger.info("successfully waited for invisibility of text: <"+ text +" >");
        	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
        }
        catch (Exception e) {
                throw new RuntimeException("Not getting (waited for) the text in the specified time: ", e);
            }
    }
    
    /**
     * Wait for Element to Switch Frame
     * 
     */
    public void waitForElementToSwitchFrame(){
        try {
        	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
        	webDriverWait = new WebDriverWait(driver, 60);
        	webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
        	webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gmifcontent"));
        	logger.info("successfully swaitched to frame: ");
        	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
        }
        catch (Exception e) {
                throw new RuntimeException("Not getting (waited for) the text in the specified time: ", e);
            }
    }
    
    /**
     * Wait for element to be present and retrun true or false
     *  
     * @param webElement
     * @return if exists true within that period of time, if not false
     */
    public boolean waitForElementPresentBoolean(WebElement webElement){
  
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
		webDriverWait = new WebDriverWait(driver, 60);
		webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
		if(webDriverWait.until(ExpectedConditions.visibilityOf(webElement)) != null){
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
		return true;
		}
		
		
		return false;
    } 
    
    
    /**
     * Wait for Element present
     * 
     * @param webElement WebElement
     */
    public void waitForElementPresent(By elementBy){
        try {
        	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
        	webDriverWait = new WebDriverWait(driver, 60);
        	webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
        	webDriverWait.until(ExpectedConditions.elementToBeClickable(elementBy));
        	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
        }
        catch (Exception e) {
                throw new RuntimeException("Not getting (waited for) the text in the specified time: ", e);
            }
    }
	
    /**
     * Click Element after focus
     * 
     * @param webElement webElement
     */
    public void clickElement(WebElement webElement) {
        webElement.click();
    }
    
    /**
     * Click Link text by using xpath
     * 
     * @param linkText Link containing text
     */
    public void clickLinkText(String linkText) {
    	driver.findElement(By.xpath("//a[contains(text(),'"+linkText+"')]")).click();
    }
    
    /**
     * choose option from the drop down list
     * @param dropDownId webElement locator
     * @param text option name
     */
    public void chooseSelectOption(WebElement dropDownLocator, String text) {
        select = new Select(dropDownLocator);
        select.selectByVisibleText(text);
    }
    
   
    /**
     * Options exists or not from dropdown
     * 
     * @param dropDownId : id of the dropdown
     * @param expectedOption : name of the expected option
     * 
     * @return
     */
    public boolean optionExistOrNotFromDropdown(WebElement dropDownId, String expectedOption){
    	String eachOption = null;
    	select = new Select(dropDownId);
    	List<WebElement> allOptions = select.getOptions();
    	int sizeOptions = allOptions.size();
    	logger.info("sizeOptions: "+sizeOptions);
    	for(WebElement everyOption : allOptions){
    		eachOption = everyOption.getText().toString();
    		logger.info("eachOption: "+eachOption);
    		if(eachOption.equalsIgnoreCase(expectedOption)){
    			return true;
    		} 		
    	}
		return false;
    }
    

	/**
	 * Get the title of the page
	 * @return title of the page
	 */
	public String titleOfThePage(){
		return driver.getTitle();
	}

	/**
	 * Whether expected text exists or not
	 * @param elementBy element locator
	 * @param text name of the text
	 * @return name of the text
	 */
	public boolean isTextPresent(By elementBy, String text)
	{
	try {
	return driver.findElement(elementBy).getText().trim().contains(text);
	} catch (NullPointerException e) {
	return false;
	}
	}

	/**
	 * Double clicking on the webElement
	 * @param elementBy ElementLocator
	 */
	public void doubleClick(By elementBy){
		doubleClick(driver.findElement(elementBy));
	}

	/**
	 * Double clicking on the webElement
	 * @param webElement webElement
	 */
	public void doubleClick(WebElement webElement){
		try{	
		actions = new Actions(driver);
		actions.doubleClick(webElement);
		Action selectMultiple = actions.build();
		selectMultiple.perform();
		sleepTightInSeconds(2);
		}
		catch(Exception e){
			throw new RuntimeException("Not double clicked on <"+webElement+">",e);
		}
	}
		
	/**
	 * @param timeoutInSeconds
	 */
	public void wait(int timeoutInSeconds) {
	    sleepTightInSeconds(timeoutInSeconds);
	}

    /**
     * Whether element is displayed or not
     * @param webElement WebElement
     * @return if exists returns true, otherwise false
     */
    public boolean isElementDisplayed(WebElement webElement) {
    	sleepTightInSeconds(2);
        boolean isDisplayed;
        try {
            isDisplayed = webElement.isDisplayed();
            return true;
        }
        catch (Throwable e) {
            isDisplayed = false;
        }

        return isDisplayed;
    }
    
    public boolean isElementPresent(WebElement webElement) {
        try {
        	if(webElement != null){
        		logger.info("webElement exist");
        		return true;
        	}
        } catch (NoSuchElementException e) {
        	logger.info("webElement not exist");
        }
        return false;
      }
    

    public void waitForElementInvisible(WebElement webElement){
    	boolean trueOrFalse = isElementPresent(webElement);
    	logger.info("isElementPresent: "+trueOrFalse); 
    	
    	for(int i=0; i<20;i++){
    		if(trueOrFalse){
    			sleepTightInSeconds(2);
    		}
    		else{
    			break;
    		}
    		sleepTightInSeconds(2);
    	}
    }

    /**
     * Whether element is displayed or not
     * 
     * @param elementBy Element By
     * 
     * @return if displayed returns to true otherwise false
     */
    public boolean isElementDisplayed(By elementBy) {
    	sleepTightInSeconds(2);
        boolean isDisplayed;
        try {
            isDisplayed = driver.findElement(elementBy).isDisplayed();
            return true;
        }
        catch (Throwable e) {
            isDisplayed = false;
        }

        return isDisplayed;
    }
    
   
    /**
     * Whether element is displayed or not
     * @param webElement WebElement
     * @return if not exists returns true, otherwise false
     */
    public boolean isElementNotDisplayed(WebElement webElement) {
    	sleepTightInSeconds(2);
        boolean isDisplayed = false;
        try {
            isDisplayed = webElement.isDisplayed();
            return false;
        }
        catch (Throwable e) {
            isDisplayed = true;
        }
        return isDisplayed;
    }
    
	/**
	 * clicking at WebElement
	 * @param webElement WebElement
	 */
	public void clickAt(WebElement  webElement){
		actions = new Actions(driver);
		actions.moveToElement(webElement).click().build().perform();
		}
	
	/**
	 * clicking at WebElement having id
	 * @param webElement WebElement
	 */
	public void clickAt(String idOfWebElement){
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id(idOfWebElement))).click().perform();
		sleepTightInSeconds(1);
		logger.info("successfully clicked at :" +idOfWebElement);
		}
    
    /**
     * Method to retrieve text of a WebElement
     * 
     * @param webElement
     * @return String - WebElement text if visible, else returns null.
     */
    public String getWebElementText (WebElement webElement) {
    		return webElement.getText().toString();
    }
    
    /**
     * Get attribute value text
     * 
     * @param webElement WebElement
     * @param attribute Name of the attribute
     * @return value of the given attribute
     */
    public String getWebElementAttributeNameText(WebElement webElement, String attribute) {
		return webElement.getAttribute(attribute);
    }
    
 
    
    /**
     * Method to retrieve text of a WebElement
     * 
     * @param elementBy 
     * @return String - text of the WebElement
     */
    public String getWebElementText (By elementBy) {
    	return driver.findElement(elementBy).getText();
    }
    
    /**
     * Switch to Content Frame
     */
    public void switchToGmifContentFrame(){
    	driver.switchTo().frame("gmifcontent");
    }
        
    /**
     * Switch To default content
     */
    public void defaultContent(){
    	driver.switchTo().defaultContent();
    }
    
     public void navigateToURL(String url){
    	driver.navigate().to(url);
    }
    
    /**
     * if isChecked is true this will make it checked, if false will make it not checked. This will happen
     * however the checkbox is already checked
     * 
     * @param webElement WebElement
     * @param isChecked true of false
     */
    public void setCheckBoxValue(WebElement webElement, boolean isChecked){
    	boolean isInputChecked = webElement.isSelected();
    	logger.info("current check box status: " +isInputChecked);   	
    	if(!isInputChecked && isChecked){
    		clickElement(webElement);
    	}
    	else if(isInputChecked && !isChecked){
    		clickElement(webElement);
    	}
    }
    
    /**
     *if isChecked is true this will make it checked, if false will make it not checked. This will happen
     * however the checkbox is already checked
     * 
     * @param elementBy Element By
     * 
     * @param isChecked true or false
     */
    public void setCheckBoxValue(By elementBy, boolean isChecked){
    	boolean isInputChecked = driver.findElement(elementBy).isSelected();
    	// logger.info("check box status: " +isInputChecked);   	
    	if(!isInputChecked && isChecked){
    		clickElement(driver.findElement(elementBy));
    	}
    	else if(isInputChecked && !isChecked){
    		clickElement(driver.findElement(elementBy));
    	}
    }
    /**
     * Is input is checked or not
     * 
     * @param elementBy Element By
     * 
     * @return true or false
     */
    public boolean isInputCheckedOrNot(By elementBy){
    return driver.findElement(elementBy).isSelected();
    }
    
    
    /**
     * if isChecked is true this will make it checked, if false will make it not checked. This will happen
     * however the radio button is already checked
     * 
     * @param webElement 
     * @param isChecked
     */
    public void setRadioButtonValue(WebElement webElement, boolean isChecked){
    	boolean isInputChecked = webElement.isSelected();
    	// logger.info("radio button status: " +isInputChecked);   	
    	if(!isInputChecked && isChecked){
    		clickElement(webElement);
    	}
    	else if(isInputChecked && !isChecked){
    		clickElement(webElement);
    	}
    }
    
    /**
     * Move cursor to specified element
     * 
     * @param webElement WebElement
     */
    public void moveCursorToSpecifiedElement(WebElement webElement){
    	actions = new Actions(driver);
    	actions.moveToElement(webElement).build().perform();
    	sleepTightInSeconds(1);
    }
    
 
    /**
     * Scrolls the specified text area to the bottom
     * 
     * @param webElement
     * @return
     */
    public void scrollElementIntoView(WebElement webElement) {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    	 sleepTightInSeconds(2);
    	 logger.info("cursor moved to <"+webElement.getText()+"> webElement");
    }
    
    
    
    
    /**
     * Scroll Element into View Text
     * 
     * @param text1 Name of the text
     */
    public void scrollElementIntoViewText(String text1) {
    	WebElement textExistsOrNot = driver.findElement(By.xpath("//a[contains(text(),'"+text1+"')]"));
    	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textExistsOrNot);
    }
    
    
    
    /**
     * Scroll Element into view Guest users
     * 
     * @param guestUser Name of the guest user
     */
    public void scrollElementIntoViewGuestUsers(String guestUser) {  	
    	WebElement textExistsOrNot = driver.findElement(By.xpath("//span[contains(.,'"+guestUser+"')]"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textExistsOrNot);
    }

	/**
	 * Close Alert and Get Its Text
	 * 
	 * @param acceptAlert Boolean, want to accept or dismissed
	 * 
	 * @return text of the alert and return true or false
	 */
	public String closeAlertAndGetItsText(boolean acceptAlert) {
	    try {
	      alert = driver.switchTo().alert();
	      sleepTightInSeconds(1);
	      String alertText = alert.getText();
	      if (acceptAlert) {
	        alert.accept();
	        sleepTightInSeconds(1);
	        logger.info("alert accepted");
	      } else {
	        alert.dismiss();
	        sleepTightInSeconds(1);
	        logger.info("alert dismissed");
	      }
	      return alertText;
	    } finally {
	    	acceptAlert = true;
	    }
	}
	
    
    /**
     * Returns the no of WebElements defined by xpath, that are currently displayed on the page
     * @param xpath
     * @return count of WebElements
     */
    public int getWebElementsCountOnPage (String xpath) {
    	List<WebElement> elements = driver.findElements(By.xpath(xpath));
    	return elements.size();
    }

    /**
     * Get page source contains text
     * 
     * @param expectedText
     * 
     * @return if expected text exist returns true otherwise false
     */
    public boolean getPageSourceContainsText(String expectedText){
    	return driver.getPageSource().contains(expectedText);
    }
    
    /**
     * Method to retrieve value of a WebElement
     * 
     * @param webElement
     * @return String - WebElement value if visible, else returns null.
     */
    public String getWebElementValue (WebElement webElement) {
    		if(webElement.getAttribute("value").isEmpty()){
    			sleepTightInSeconds(5);
    		}
    		return webElement.getAttribute("value").toString();
    }
    
    /**
     * Method to return the size of element list
     * @param xpath
     * @return size of element list
     */
    public int getElementListSize (String xpath) {
    	return driver.findElements(By.xpath(xpath)).size();
    }

	/**
	 * Clear text in text box area
	 * 
	 * @param webElement WebElement
	 * @param text Text to send it
	 * @param clearBeforeSend Want to clear the text before sending text, if Yes give the boolean TRUE or other wise No give the boolean as FALSE.
	 */
	public void cleatText(WebElement webElement) {
			 webElement.clear();
	  }
	
	
	 public boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }
	
	public void clearTextAcceptAlertIfExistsAndSendKeys(WebElement webElement, String text){
			webElement.clear();
			if(isAlertPresent()){
			    alert = driver.switchTo().alert();
		        sleepTightInSeconds(1);
		        alert.accept();
		        sleepTightInSeconds(1);
		        logger.info("alert accepted");
			}
			this.sendKeys(webElement, text, false);
	}
	
    /**
     * Wait for title present
     * 
     * @param title: title contains expected text
     */
    public void waitForTitlePresent(String expetcedTitleContains){
        try {
        	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait()
        	webDriverWait = new WebDriverWait(driver, 200);
        	webDriverWait.pollingEvery(2, TimeUnit.SECONDS);
        	webDriverWait.until(ExpectedConditions.titleContains(expetcedTitleContains));
        	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS); //reset implicitlyWait
        }
        catch (Exception e) {
                throw new RuntimeException("Title is not existed in the specified time: ", e);
            }
    } 
   
    
    /**
     * Executing a specified BAT file
     * 
     * @param nameOfBAT name of the batch file
     */
    public void executeBATFile(String nameOfBAT){
    	try {
            String[] command = {"cmd.exe", "/C", "Start", System.getProperty("user.dir")+"\\src\\test\\resources\\"+nameOfBAT};
            Runtime.getRuntime().exec(command);
            sleepTightInSeconds(10);
            logger.info("closed cmd bat file successfully");
            
        } catch (IOException ex) {
        }
    }
    
    /**
     * Delete all cookies
     */
    public void deleteAllCookies(){
    	driver.manage().deleteAllCookies();
    }
    
    
    /**
     * 
     * @param element
     * @param text
     * @return
     */
    public boolean isTextPresentInElement (WebElement element, String text) {
    	try {    		
    		return element.getText().trim().contains(text);
    	} catch (NullPointerException e) {
    		return false;
    	}
    }

	/**
	 * Close Alert by accept or dismiss
	 * 
	 * @param acceptAlert Boolean, want to accept or dismissed
	 * 
	 * 
	 */
	public void closeAlertByAcceptOrDismiss(boolean acceptAlert) {
	    try {
	      alert = driver.switchTo().alert();
	      sleepTightInSeconds(1);
	      if (acceptAlert) {
	        alert.accept();
	        sleepTightInSeconds(1);
	        logger.info("alert accepted");
	      } else {
	        alert.dismiss();
	        sleepTightInSeconds(1);
	        logger.info("alert dismissed");
	      }
	    } finally {
	    	acceptAlert = true;
	    }
	}
	
    /**
     * Return the options from multi select List
     * 
     * @param dropDownId : id of the dropdown
     * @param expectedOption : name of the expected option
     * 
     * @return
     */
    public boolean returningOfOptionsFromMultiSelectList(WebElement dropDownId, ArrayList<String> fromTestData){
    	select = new Select(dropDownId);
    	List<WebElement> allOptions = select.getOptions();
    	int sizeOptions = allOptions.size();
    	logger.info("sizeOptions: "+sizeOptions);
    	
    	for(int i=0; i<sizeOptions; i++){
    		logger.info("value fromTestData: " +i+": "+fromTestData.get(i).toString());
    		logger.info("from alloptions: " +i+": "+allOptions.get(i).getText().toString());
  
    		if(allOptions.get(i).getText().toString().equalsIgnoreCase(fromTestData.get(i).toString())){
    			continue;
    		}
    		return false;
    	}
    	return true;
    }

    /**
     * Whether element is present or not
     * 
     * @param by By
     * @return true or false
     */
    public boolean isElementPresent(By by) {
        try {
          driver.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }
    
    /**
     * Whether style attribute is having the value or not
     * 
     * @param webElement WebElement
     * @return if exists returns true, otherwise false
     */
    public boolean isValueForStyleExist(WebElement webElement) {
    	sleepTightInSeconds(2);
     
    	try{
        	String styleValue = webElement.getAttribute("style");
        	logger.info("Style value: "+styleValue);
            if(styleValue.contains("display: none")){
            	return true;
            }
    	}
    	catch(StaleElementReferenceException e2){
    		logger.info("StaleElementReferenceException caught for duration field: "+e2.toString());
    	}
			return false;
    }
    
	public void refreshingCurrentPage(){
		driver.navigate().refresh();
	}
	
	public static void sleepTightInSeconds(int seconds) {
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
		
	public void clickAutoCompleteText(List<WebElement> webElement, String itemDesc) {
		int elementSize = webElement.size();
		logger.info("elementSize: " +elementSize);
		if (elementSize > 0)
			for(WebElement all :webElement){
			logger.info("allTExt: " +all.getText());
	
			if(all.getText().equalsIgnoreCase(itemDesc)){
			all.click();
			logger.info("Successfully clicked on selected item: " + all.getText());
			sleepTightInSeconds(5);
			break;
			}
			}
	    else {
	    	   for (WebElement single : webElement) {
	    		 logger.info("search item is single: "+ single.getText());
	    		 single.click();
	    		 logger.info("clicked on item");
	    		 sleepTightInSeconds(5);
	    	   }
	       }
	   }
	
    /**
     * Switch to window
     */
    public void switchToNewWindow(){
    	String parentWinHandle = driver.getWindowHandle();
    	logger.info("parentWinHandle " + parentWinHandle);
    	
	    for (String handle : driver.getWindowHandles()) {
		   if (!handle.equals(parentWinHandle)) {
	           driver.switchTo().window(handle);
	           sleepTightInSeconds(5);
	           break;
		   }
	    }
    }
    
    public static void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
        	   Thread.sleep(5);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Exception e) {
        	throw new RuntimeException("Timeout waiting for Page Load Request to complete. ", e);
        }
    }
}
