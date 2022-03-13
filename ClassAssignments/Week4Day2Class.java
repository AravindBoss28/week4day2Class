package week4day1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Week4Day2Class {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver  driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://erail.in/");
		
		// enter trichy
		driver.findElement(By.id("txtStationFrom")).clear();
		driver.findElement(By.id("txtStationFrom")).sendKeys("tpj");
		driver.findElement(By.id("txtStationFrom")).sendKeys(Keys.TAB);
		
		// enter chennai
		driver.findElement(By.id("txtStationTo")).clear();
		driver.findElement(By.id("txtStationTo")).sendKeys("mas");
		driver.findElement(By.id("txtStationTo")).sendKeys(Keys.TAB);
		
		// uncheck box
		driver.findElement(By.id("chkSelectDateOnly")).click();
		
  
	
		 
		// found all data
		List<WebElement> body = driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']/tbody"));
		for (int i = 0; i < body.size(); i++) {
			List<WebElement> eachrow =  body.get(i).findElements(By.tagName("tr"));
			for (int j = 0; j < eachrow.size(); j++) {
				List<WebElement> eachCol =  eachrow.get(j).findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']/tbody/tr"));
			     System.out.println(eachCol.get(j).getText());
			     System.out.println("");
			}	
		}
		
		for (int i = 0; i < body.size(); i++) {
			List<WebElement> eachrow =  body.get(i).findElements(By.tagName("tr"));
			for (int j = 0; j < eachrow.size(); j++) {
				List<WebElement> trainNames =  eachrow.get(j).findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']/tbody/tr/td[2]"));
                if(trainNames.get(j).getText().contains("CHENNAI")) {
                	List<WebElement> trainID =  eachrow.get(j).findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader']/tbody/tr/td[1]"));
                    System.out.println(trainID.get(j).getText());
                }			    
			}
		}
		
		
	}

}
