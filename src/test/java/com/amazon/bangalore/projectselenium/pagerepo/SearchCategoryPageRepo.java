package com.amazon.bangalore.projectselenium.pagerepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.bangalore.projectselenium.testutil.WebDriverUtility;

public class SearchCategoryPageRepo extends WebDriverUtility{
	
	@FindBy(id= "twotabsearchtextbox")
    private WebElement searchTextBox;

}
