package com.amazon.bangalore.projectselenium.feature2;

import org.openqa.selenium.support.PageFactory;

import com.amazon.bangalore.projectselenium.pagerepo.LoginPageRepo;
import com.amazon.bangalore.projectselenium.pagerepo.PlaceOrderPageRepo;
import com.amazon.bangalore.projectselenium.testutil.WebDriverUtility;

public class PlaceOrderFeatureRepo extends WebDriverUtility{
	
	LoginPageRepo loginPageRepo;
	PlaceOrderPageRepo placeOrderPageRepo;
	
	public PlaceOrderFeatureRepo(){
		loginPageRepo = PageFactory.initElements(driver, LoginPageRepo.class);
		placeOrderPageRepo = PageFactory.initElements(driver, PlaceOrderPageRepo.class);
		
	}
	
	public void placeOrderAndThenLogin(String username, String password, String custName, String itemName, String fullDesc, String formatType) {
		placeOrderPageRepo.searchItemAndPlaceOrder(itemName, fullDesc, formatType);
		loginPageRepo.loginAmazon(username, password, custName, false);
	}
	
	public void loginAndThenPlacingOrder(String username, String password, String custName, String itemName, String fullDesc, String formatType) {
		loginPageRepo.loginAmazon(username, password, custName, true);
		placeOrderPageRepo.searchItemAndPlaceOrder(itemName, fullDesc, formatType);
		
	}
	
	public void placeOrderAndThenLoginWithCategory(String username, String password, String custName, String itemName, String fullDesc, String formatType) {
		placeOrderPageRepo.searchItemAndPlaceOrder(itemName, fullDesc, formatType);
		loginPageRepo.loginAmazon(username, password, custName, false);
	}
}
