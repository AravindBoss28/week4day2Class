package week4day1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertBoxhandling {
    static WebDriver driver;
    static void initPage() {
    	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.leafground.com/pages/Alert.html");	
    }
    
    private static Alert genericAlert(String xPath) {
        driver.findElement(By.xpath(xPath)).click();
        Alert al = driver.switchTo().alert();
        return al;
    }
    
    static String handleAlert = "//button[text()='Alert Box']";
    
    static void handleAlert() {
      Alert al = genericAlert(handleAlert);
      System.out.println(al.getText());
      al.dismiss();
    }
    
    static String confirmAlert = "//button[text()='Confirm Box']";
    static void confirmAlert() {
    	Alert al = genericAlert(confirmAlert);
        System.out.println(al.getText());
        al.dismiss();	
    }
    
    static String promptAlert = "//button[text()='Prompt Box']";
    static void promptAlert() {
    	Alert al = genericAlert(promptAlert);
    	al.sendKeys("helloooo");
    	System.out.println("Prompt Alert");
        System.out.println(al.getText());
        System.out.println("Prompt Alert ==============>");
        al.dismiss();	
    }
	
    static String lineBreaks = "//button[contains(text(), 'Line')]";
    static void lineBreaks() {
    	Alert al = genericAlert(lineBreaks);
    	System.out.println("Line Alert");
        System.out.println(al.getText());
        System.out.println("Line Alert ==============>");
        al.accept();	
    }
    
    static String sweet = "//button[contains(text(), 'Sweet')]";
    static void sweetAlert() {
    	//Alert al = genericAlert(sweet);
    	driver.findElement(By.xpath(sweet)).click();
    	System.out.println("Sweet Alert");
        System.out.println(driver.findElement(By.xpath("//div[contains(text(), 'Happy')]")).getText());
        System.out.println("Sweet Alert ==============>");
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
    }
    
    static void close() {
      driver.close();	
    }
    
	public static void main(String[] args) throws InterruptedException {
		
	   // TODO Auto-generated method stub
       initPage();
       handleAlert();
       Thread.sleep(2000);
       confirmAlert();
       Thread.sleep(2000);
       promptAlert();
       Thread.sleep(2000);
       lineBreaks();
       Thread.sleep(2000);
       sweetAlert();
       Thread.sleep(2000);
       close();
	}

}
