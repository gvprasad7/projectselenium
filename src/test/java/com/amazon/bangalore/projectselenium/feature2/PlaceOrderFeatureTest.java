package com.amazon.bangalore.projectselenium.feature2;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.bangalore.projectselenium.feature1.LoginFeatureRepo;
import com.amazon.bangalore.projectselenium.testutil.BaseSeleniumTest;

public class PlaceOrderFeatureTest extends BaseSeleniumTest {

	LoginFeatureRepo loginFeatureRepo;
	PlaceOrderFeatureRepo placeOrderFeatureRepo;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException {
		loginFeatureRepo = new LoginFeatureRepo();
		placeOrderFeatureRepo = new PlaceOrderFeatureRepo();
	}

	 /**
	 * Place order and then login with correct credentials
	 */
	  @Test(groups = {"sanity"}) 
	  public void testPlaceOrderAndThenLogin(){
		  loginFeatureRepo.openOnlyUrl(getTestData("baseUrl"));
		  placeOrderFeatureRepo.placeOrderAndThenLogin(getTestData("email"),
		  getTestData("password"), getTestData("helloText"),
		  getTestData("searchString1"), getTestData("expString1"), "kindle");
		  loginFeatureRepo.successfulLogout();
		  Assert.assertEquals(loginFeatureRepo.nameOfTitlePage(), getTestData("afterSignoutText")); 
		  logger.info("customer login successfull");
	  }
	  
	  /**
	  * Login with correct credentials and then place an order
	  */
	  @Test(groups = {"sanity"}) 
	  public void testLoginAndthenPlaceOrder(){
		  loginFeatureRepo.openOnlyUrl(getTestData("baseUrl"));
		  placeOrderFeatureRepo.loginAndThenPlacingOrder(getTestData("email"),
		  getTestData("password"), getTestData("helloText"),
		  getTestData("searchString1"), getTestData("expString1"), "kindle");
		  loginFeatureRepo.successfulLogout();
		  Assert.assertEquals(loginFeatureRepo.nameOfTitlePage(), getTestData("afterSignoutText")); 
		  logger.info("customer login successfull");
	  }
}
