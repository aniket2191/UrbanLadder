package com.urbanladder.pageObjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookShelves {

	WebDriver ldriver;
	public String productInfo="(//div[@class='product-info-block'])";
	public String productName="//a//child::div[1]//span[1]";
	public String productprice="//a//child::div[2]//span[1]";
	public String setRange="//div[@class='noUi-handle noUi-handle-upper']";
	public static int maxLimit=15999;
	public static String exp_sortBySelection="Price: High to Low";
	


	public BookShelves(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}

	@FindBy(xpath="//h4[text()='Bookshelves']")
	WebElement bookshelves;
	
	@FindBy(xpath="(//div[@class='gname'])[1]")
	WebElement price;
  
	
	@FindBy(xpath="//div[@class='noUi-handle noUi-handle-upper']")
	WebElement upperSlider;
	
	@FindBy(id="price_limit_9000-15999")
	WebElement range_below15999;

	@FindBy(xpath="(//div[@class='gname'])[2]")
	WebElement storageType;
	
	@FindBy(xpath="//input[@id='filters_storage_type_Open']")
	WebElement storageTypeOpen;
	
	//@FindBy(xpath="//a[@data-gaaction='popup.auth.close']")
	//WebElement closeLoginPopup;
	
	@FindBy(xpath="//a[@class='close-reveal-modal hide-mobile']")
	WebElement closeLoginPopup;
	
	@FindBy(xpath="//input[@id='filters_availability_In_Stock_Only']")
	WebElement includeOutOfStcok;
	
	@FindBy(xpath="//div[@class='item']//span")
	WebElement recommandation;
	
	@FindBy(xpath="(//li[@class='option'])[2]")
	WebElement recommandation_HighToLow;
	
	@FindBy(xpath="//ul[@class='sortoptions']//li[3]")
	WebElement optionIsSelected;
	
	
	@FindBy(xpath="//div[@class='product-info-block']")
	List<WebElement> listOfProducts;

	//@FindBy(xpath="(//span[@class='name'])")
	//List<WebElement> nameOfAllBookShelves;
	
	@FindBy(xpath="//div[@class='price-number']//span")
	List<WebElement> priceOfAllBookShelves;
	
	@FindBy(xpath="//input[@id='filters_price_max']")
	WebElement maxPrice;
	
	@FindBy(xpath="//div[@class='item']/descendant::span[text()='Price: High to Low']")
	WebElement sortBySelection;
	

	public void clickOnBookShelves()
	{
		bookshelves.click();
	}
	
	public WebElement getRange_below15999() {
		return range_below15999;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getStorageType() {
		return storageType;
	}
	
	public WebElement getStorageTypeOpen() {
		return storageTypeOpen;
	}
	
	public WebElement getIncludeOutOfStcok() {
		return includeOutOfStcok;
	}
	
	public WebElement getCloseLoginPopup() {
		return closeLoginPopup;
	}

	public WebElement getRecommandation() {
		return recommandation;
	}

	public WebElement getRecommandation_HighToLow() {
		return recommandation_HighToLow;
	}
	
	public WebElement getUpperSlider() {
		return upperSlider;
	}
	/*
	public List<WebElement> getNameOfAllBookShelves() {
		return  nameOfAllBookShelves;
	}
*/
	public List<WebElement> getPriceOfAllBookShelves() {
		return priceOfAllBookShelves;
	}

	public List<WebElement> getListOfProducts() {
		return listOfProducts;
	}

	public int getListSizeOfProducts() {
		return listOfProducts.size();
	}
	public WebElement optionIsSelected() {
		
		//return optionIsSelected.getAttribute("class");
		return optionIsSelected;
	}
	

	public String getMaxPrice() {
		return maxPrice.getAttribute("value");
	}
	
	public WebElement getSortBySelection() {
		return sortBySelection;
	}
	
}
