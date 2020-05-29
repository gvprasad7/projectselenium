package com.amazon.bangalore.projectselenium.feature3;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.bangalore.projectselenium.feature1.LoginFeatureRepo;
import com.amazon.bangalore.projectselenium.feature2.PlaceOrderFeatureRepo;
import com.amazon.bangalore.projectselenium.testutil.BaseSeleniumTest;

public class SearchCategoryFeatureTest extends BaseSeleniumTest{
	
	LoginFeatureRepo loginFeatureRepo;
	PlaceOrderFeatureRepo placeOrderFeatureRepo;
	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException {
		loginFeatureRepo = new LoginFeatureRepo();
		placeOrderFeatureRepo = new PlaceOrderFeatureRepo();
	}

	/**
	 * Place order and then login with correct credentials
	 * @throws Exception 
	 */
	@Test(groups = {"sanity"})
	public void testPlaceOrderAndThenLoginWithCategorySearch() throws Exception{

	}

}
