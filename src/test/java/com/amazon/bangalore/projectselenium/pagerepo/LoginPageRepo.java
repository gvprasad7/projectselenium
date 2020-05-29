package com.amazon.bangalore.projectselenium.pagerepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.bangalore.projectselenium.testutil.WebDriverUtility;

public class LoginPageRepo extends WebDriverUtility{
	
	@FindBy(id= "ap_email")
    private WebElement apEmail;
	
	@FindBy(id= "ap_password")
    private WebElement apPassword;
	
	@FindBy(xpath = "//input[@id='continue']")
	private WebElement continueButton;
	
	@FindBy(id = "signInSubmit")
	private WebElement loginButton;
	
	@FindBy (xpath = "//a[@id='nav-link-accountList']/span[1]")
	private WebElement helloSignInText;
	
	@FindBy (xpath = "//a[@id='nav-link-accountList']/span[2]")
	private WebElement accountList;
	
	@FindBy (xpath = "//a[@id='nav-item-signout']/span")
	private WebElement signOut;
	
	@FindBy (css="label.a-form-label")
	private WebElement signOutLink;
	
	
	
	/**
	 * Login to given URL using user name, password, name of the customer
	 * 
	 * @param userName
	 * @param passWord
	 * @param customerName
	 * @param fromLoginStart
	 */
	public void loginAmazon(String username, String password, String customerName, Boolean fromLoginStart){
		try{
			if (fromLoginStart) {
				this.waitForElementPresent(helloSignInText);
				this.clickElement(accountList);
			}
			this.waitForElementPresent(apEmail);
			this.sendKeys(apEmail, username, true);
			this.clickElement(continueButton);
			
			this.sendKeys(apPassword, password, true);
			this.clickElement(loginButton);
			this.waitForElementPresent(helloSignInText);
		}
		
		catch (Exception e){
			throw new RuntimeException("customer login failed: ", e);
		}
	}

	/**
	 * Move cursor and sign out
	 * 
	 */
	public void logutAmazon(){
		this.moveCursorToSpecifiedElement(accountList);
		this.clickElement(signOut);
	}
	
	
	/**
	 * Getting hello user name of sign in text
	 * 
	 * @return hello user name or Sign In
	 */
	public String getHelloUsernameText(){
		return this.getWebElementText(helloSignInText);
	}
	
	/**
	 * Getting text after successful sign out
	 * 
	 * @return text after sign out
	 */
	public String getTextAfterSignOut(){
		return this.getWebElementText(signOutLink);
	}


}
