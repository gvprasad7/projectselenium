package com.amazon.bangalore.projectselenium.feature1;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.bangalore.projectselenium.testutil.BaseSeleniumTest;

public class LoginFeatureTest  extends BaseSeleniumTest{
	
	LoginFeatureRepo loginFeatureRepo;
	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws IOException {
		loginFeatureRepo = new LoginFeatureRepo();
	}
	
	
	/**
	 * Test Successful sign in with correct credentials
	 */
	@Test(groups = {"sanity"})
	public void testAmazonEndToEndPlaceOrder(){
		loginFeatureRepo.openOnlyUrl(getTestData("baseUrl"));
		loginFeatureRepo.successfulLoginWithCorrectCredentials(getTestData("email"), getTestData("password"), getTestData("helloText"), true);
		Assert.assertEquals(loginFeatureRepo.nameOfTitlePage(), getTestData("helloText"));
		logger.info("customer signIn successfull");
		loginFeatureRepo.successfulLogout();
		Assert.assertEquals(loginFeatureRepo.nameOfTitlePage(), getTestData("afterSignoutText"));
		logger.info("customer Singout successfull");
	}	

}
