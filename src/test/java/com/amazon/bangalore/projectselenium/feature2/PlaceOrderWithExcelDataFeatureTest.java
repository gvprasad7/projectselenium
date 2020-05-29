package com.amazon.bangalore.projectselenium.feature2;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.bangalore.projectselenium.feature1.LoginFeatureRepo;
import com.amazon.bangalore.projectselenium.testutil.BaseSeleniumTest;
import com.amazon.bangalore.projectselenium.testutil.ExcelTestData;

public class PlaceOrderWithExcelDataFeatureTest extends BaseSeleniumTest {

	LoginFeatureRepo loginFeatureRepo;
	PlaceOrderFeatureRepo placeOrderFeatureRepo;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException {
		loginFeatureRepo = new LoginFeatureRepo();
		placeOrderFeatureRepo = new PlaceOrderFeatureRepo();
	}

	/**
	 * Place order and then login with correct credentials
	 * 
	 * @throws Exception
	 */
	@Test(groups = {"sanity"}) 
	  public void testPlaceOrderAndThenLogin() throws Exception{  
		  ExcelTestData.initExcelUtils();
		  loginFeatureRepo.openOnlyUrl(ExcelTestData.baseUrl);
		  placeOrderFeatureRepo.placeOrderAndThenLogin(ExcelTestData.email, ExcelTestData.password, ExcelTestData.helloText,
				  										ExcelTestData.searchString1, ExcelTestData.expString1, ExcelTestData.kindle);
		  loginFeatureRepo.successfulLogout();
		  Assert.assertEquals(loginFeatureRepo.nameOfTitlePage(), ExcelTestData.afterSignoutText); 
		  logger.info("customer login successfull");
	  }

	/**
	 * Login with correct credentials and then place an order
	 * 
	 * @throws Exception
	 */
	@Test(groups = {"sanity"}) 
	  public void testLoginAndthenPlaceOrder() throws Exception{
		  ExcelTestData.initExcelUtils();
		  loginFeatureRepo.openOnlyUrl(ExcelTestData.baseUrl);
		  placeOrderFeatureRepo.loginAndThenPlacingOrder(ExcelTestData.email, ExcelTestData.password, ExcelTestData.helloText,
														 ExcelTestData.searchString1, ExcelTestData.expString1, ExcelTestData.kindle);
		  loginFeatureRepo.successfulLogout();
		  Assert.assertEquals(loginFeatureRepo.nameOfTitlePage(), ExcelTestData.afterSignoutText);
		  logger.info("customer login successfull");
	  }
}
