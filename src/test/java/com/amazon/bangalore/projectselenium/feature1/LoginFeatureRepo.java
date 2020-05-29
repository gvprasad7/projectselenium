package com.amazon.bangalore.projectselenium.feature1;


import org.openqa.selenium.support.PageFactory;
import com.amazon.bangalore.projectselenium.pagerepo.LoginPageRepo;
import com.amazon.bangalore.projectselenium.testutil.WebDriverUtility;


public class LoginFeatureRepo extends WebDriverUtility{
	
	LoginPageRepo loginPageRepo;
	
	public LoginFeatureRepo(){
		loginPageRepo = PageFactory.initElements(driver, LoginPageRepo.class);
	}
		
	public void successfulLoginWithCorrectCredentials(String username, String password, String custName, Boolean fromStart){
		loginPageRepo.loginAmazon(username, password, custName, fromStart);	
	}
	
	public void successfulLogout(){
		loginPageRepo.logutAmazon();
	}
	
	public void openOnlyUrl(String url){
		loginPageRepo.navigateToURL(url);
		waitForPageLoad();
	}
	
	/**
	 * Getting title of the page
	 * 
	 * @return Title of the page
	 */
	public String nameOfTitlePage(){
		return this.titleOfThePage();
	}
	
	
	

}
