package com.urbanladder.testCases;

import static org.testng.Assert.assertEquals;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverAction;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.urbanladder.Base.TestBase;
import com.urbanladder.pageObjects.BookShelves;


public class BookshelvesTest extends TestBase {


	BookShelves bShelves;
	String pageTitle;
	List<WebElement> sizeofListOfProducts;
	String priceXpath;
	String nameXpath;
	String prodName;
	String prodPrice;
	WebElement nameElement;
	WebElement priceElement;


	@Test()
	public void TC01_GetBookShelvesBelow15999() throws InterruptedException, IOException
	{

		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		//Click on Bookshelves Option
		bShelves=new BookShelves(driver);	
		bShelves.clickOnBookShelves();
		pageTitle=driver.getTitle();
		System.out.println("pageTitle :"+pageTitle);
		String title="Upto 70% Off on Bookshelves this Christmas | Very Merry Sale - Urban Ladder";	
		assertEquals(pageTitle, title);

		//Select Storage Type-Open
		act.moveToElement(bShelves.getStorageType())
		.moveToElement(bShelves.getStorageTypeOpen()).click().perform();
		Assert.assertTrue(bShelves.getStorageTypeOpen().isSelected());

		//Close Login Popup
		wait.until(ExpectedConditions.visibilityOf(bShelves.getCloseLoginPopup()));
		bShelves.getCloseLoginPopup().click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");

		System.out.println("Closed successfully.....");


		//Select Price range-below 15999
		/*
		act.moveToElement(bShelves.getPrice())
		.moveToElement(bShelves.getRange_below15999()).click().perform();
		Assert.assertTrue(bShelves.getRange_below15999().isSelected());
		 */	

		act.moveToElement(bShelves.getPrice())		 
		.dragAndDropBy(bShelves.getUpperSlider(), -247, 0).perform();
		Assert.assertTrue(Double.valueOf(bShelves.getMaxPrice()) < bShelves.maxLimit);

		
		//Include Out of Stock Items-Verify Exclude out of stock checkbox should not be Selected.
		Assert.assertFalse(bShelves.getIncludeOutOfStcok().isSelected());

		
		//Select recommendation-High to Low
		act.moveToElement(bShelves.getRecommandation())
		.moveToElement(bShelves.getRecommandation_HighToLow()).click().perform();
		wait.until(ExpectedConditions.visibilityOf(bShelves.getSortBySelection()));
		Assert.assertEquals(bShelves.getSortBySelection().getText(), bShelves.exp_sortBySelection);


		System.out.println("SizeOfList : "+bShelves.getListSizeOfProducts());
		FirstThreeProductInfo(bShelves.getListSizeOfProducts(), bShelves);

	}

	public void FirstThreeProductInfo(int ProductSize,BookShelves bShelves) throws IOException
	{

		for(int i=1;i<=bShelves.getListSizeOfProducts();i++)			
		{

			nameXpath=bShelves.productInfo+"["+i+"]"+bShelves.productName;
			priceXpath=bShelves.productInfo+"["+i+"]"+bShelves.productprice;

			nameElement=driver.findElement(By.xpath(nameXpath));
			priceElement=driver.findElement(By.xpath(priceXpath));

			wait.until(ExpectedConditions.visibilityOf(nameElement));
			wait.until(ExpectedConditions.visibilityOf(priceElement));

			prodName=nameElement.getText();
			prodPrice=priceElement.getText();

			//System.out.println("Price : "+price);//₹13,949
			prodPrice= prodPrice.replace("₹", "");
			prodPrice=prodPrice.replace(",", "");

			Assert.assertTrue(Integer.valueOf(prodPrice) < bShelves.maxLimit);

			System.out.println(prodName+" | "+Integer.valueOf(prodPrice));



		}


	}


	/*
	public void FirstThreeProductInfo(int ProductSize,BookShelves bShelves) throws IOException
	{
		XSSFWorkbook wrk=new XSSFWorkbook();
		XSSFSheet sht=wrk.createSheet("Product_Info");
		int rowsCount=0;

		//Setting Headings For Products:
		XSSFRow rows=sht.createRow(rowsCount++);
		rows.createCell(0).setCellValue("Product_Name");
		rows.createCell(1).setCellValue("Product_Price");

		for(int i=1;i<=bShelves.getListSizeOfProducts();i++)
		//for(int i=1;i<=3;i++)	
		{

			String nameXpath=bShelves.productInfo+"["+i+"]"+bShelves.productName;
			String priceXpath=bShelves.productInfo+"["+i+"]"+bShelves.productprice;

			String name=driver.findElement(By.xpath(nameXpath)).getText();
			String price=driver.findElement(By.xpath(priceXpath)).getText();

			//System.out.println(name+ "  |  " +price);

			 rows=sht.createRow(rowsCount++);
			rows.createCell(0).setCellValue(name);
			rows.createCell(1).setCellValue(price);

		}

		LocalDateTime myLocalDateTimeObj=LocalDateTime.now();
		DateTimeFormatter dateTimeFormatterObj=DateTimeFormatter.ofPattern("ddMMyyyHHmmss");
		String cuDateAndTime= myLocalDateTimeObj.format(dateTimeFormatterObj);

		FileOutputStream fos=new FileOutputStream(".//datafiles//prodInfo"+cuDateAndTime+".xlsx");
		wrk.write(fos);
		fos.close();

	}
	 */

}
