package seleniumFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;


/*
 * This is practice project of web application of the grocery website.
 * 
 */

public class VegEcomHomePage {
	private WebDriver driver;
	
	private Logger log = LogManager.getLogger(VegEcomHomePage.class.getName());

	WebDriverWait w;
	
	public VegEcomHomePage() {
		// TODO Auto-generated constructor stub
	}
	
	public VegEcomHomePage(WebDriver driver) {
		this.driver = driver;
		w = new WebDriverWait(driver, 5);
	}

	// Returns the webElement of search bar of vegetables.
	public By search() {
		return By.cssSelector("input[type ='search']");
	}
	
	
	// the method accepts chars and checks if those products starting with those letters are displayed and return the names of the list of products else returns null
	
	public List<String> searchByLetter(CharSequence fewCharacters, Properties props) throws IllegalArgumentException{
		
		
		if(fewCharacters.length()<=0 || fewCharacters.equals(null)) {
			
			return null;
		}
						
		List<String> displayedProducts = new ArrayList<>();
		
		List<WebElement> items = new ArrayList<WebElement>();
		
		driver.findElement(By.xpath("//div[@class = 'search']/form/input")).sendKeys(fewCharacters);
		
	    driver.findElement(By.xpath("//div[@class = 'search']/form/button")).click(); // here the page get's refreshed
	   
	    w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("products")));
	    
	  try {
			items = driver.findElements(By.cssSelector("h4[class='product-name']"));
			}
		catch (StaleElementReferenceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
		String[] vegetableNames;
		
		for (int i=0; i < items.size(); i++) {

			vegetableNames = items.get(i).getText().split("-");
			String name = vegetableNames[0].trim();
			displayedProducts.add(name);
		}		
				
		return displayedProducts;
	}
	

	// This method adds the particular quantity of given vegetables into the cart.

	public void addVegetables(String vegetableName, int quantity) throws IllegalArgumentException, NullPointerException{

		try {		
		
		List<WebElement> buttons = driver.findElements(By.cssSelector("div[class='product-action']"));

		List<WebElement> items = driver.findElements(By.cssSelector("h4[class='product-name']"));

		String[] vegetableNames;

		for (int i = 0; i < items.size(); i++) {

			vegetableNames = items.get(i).getText().split("-");
			String name = vegetableNames[0].trim();

			if (name.equals(vegetableName)) {

				for (int j = 1; j <= quantity; j++) {
					buttons.get(i).click();
				}
			}
		}

	}
		
		catch (Exception e) {
			// TODO: handle exception
			log.error("Exception: ", e);
		}		
		
	}
		
   public int totalPrice() {
		return Integer.parseInt(driver.findElement(By.xpath("//*[@class='cart-info']/table/tbody/tr[2]/td[3]/strong")).getText());
	}	

   public int totalItems() {
	   
	   return Integer.parseInt(driver.findElement(By.xpath("//*[@class='cart-info']/table/tbody/tr[1]/td[3]/strong")).getText());

    }
   
   // This method returns the basketElement and allows to proceed for checkout or remove the selected items
   public By basketElement() {
	   return By.className("cart-icon");
   }  
   
   
   public By checkOut() {  
	   driver.findElement(basketElement()).click();
	   
	   w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class=’cart-preview active’]")));
	   
	   return By.xpath("//div[@class = 'cart-preview active']/div[2]/button");
	    
   }
   

   
   // Remove particular items from the basket using the below element
   public List<String> removeItemsElement(String... strings) throws IllegalArgumentException, InterruptedException{
	   
	   if(strings.length <= 0) {
		   
		   return null;
	}
	   else {
		    
	   //Get all the webelements of cart items into a list<WebElement>, use that list to verify with the given strings(items) to be removed.   
		   
		driver.findElement(By.xpath("//a[@class='cart-icon']")).click();	   
		
		Thread.sleep(3000);
		
		List<WebElement> cartItemsWebElements = driver.findElements(By.cssSelector("ul[class=’cart-items’]"));
	 
	  //Now use an cartItems to store the product string and its position. Since all the products have the same remove tag, it's better to know the serial number of every product in cart
	    HashMap<String, Integer> cartItems = new HashMap<String, Integer>();
	   
	    int itemSerialNumbersInCart = 0;
	    
	    if(cartItemsWebElements.size() > 0){
	   
	   for(WebElement element:cartItemsWebElements) {
		   itemSerialNumbersInCart+=1;
		   cartItems.put(element.findElement(By.cssSelector("div[class='product-Info']")).getAttribute("product-name"), itemSerialNumbersInCart);
		 }
	    }   
	    
	
	     List<String> removeElementXpaths = new ArrayList<>();
	   	   
	   for(String itemToBeRemoved: strings) {
		   
		   try {
			   
			 int position = cartItems.get(itemToBeRemoved);
			   
			   String cartItemsXpath = "//ul[@class ='cart-items']/li[pos]/a";
			   
			   StringBuilder builder = new StringBuilder(cartItemsXpath);
			   
			   builder.replace(30, 33, Integer.toString(position+1));
			   
			   removeElementXpaths.add(cartItemsXpath);

			 }
		   
		   catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
		     }
		   		   
		}
	   
	   
	 System.out.println(removeElementXpaths.toString());
	   
	   
	   
	   // this list can be used by the caller, and perform click operation in the list to remove from the cart. 
	  return removeElementXpaths;
	  }
   }
   
}
