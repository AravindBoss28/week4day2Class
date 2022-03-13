package week4day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {
	ChromeDriver driver;

	private void takeScreenShot(String fname) {
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		try {
			File file = new File("./Snaps/" + fname + ".png");
			FileHandler.copy(screenshotAs, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("repective folder is not available or may be some other issue in file path");
		}
	}

	void initChrome(String url) {
		WebDriverManager.chromedriver().setup();
		if (driver == null) {
			driver = new ChromeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	private WebDriver switchFrame() {
		WebDriver frame = driver.switchTo().frame(0);
		return frame;
	}

	void dragAndDrop(String fileName) {
		Actions actions = new Actions(switchFrame());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		takeScreenShot("before" + fileName);
		actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build()
				.perform();
		takeScreenShot("after" + fileName);

	}

	void selectable(String fileName) {
		Actions actions = new Actions(switchFrame());

		List<WebElement> findElements = driver.findElements(By.xpath("//ol[@id= \"selectable\"]/li"));
		Actions completeclick = null;
		for (int i = 0; i < findElements.size(); i++) {
			if (i % 2 != 0) {
				completeclick = actions.keyDown(Keys.CONTROL).click(findElements.get(i));
			}
		}
		completeclick.keyUp(Keys.CONTROL).perform();
		takeScreenShot("before" + fileName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		takeScreenShot("after" + fileName);
	}

	void dragable(String fileName) {
		Actions actions = new Actions(switchFrame());
		Point location = driver.findElement(By.id("draggable")).getLocation();
		int xOffset = location.x + 100;
		int yOffset = location.y + 150;
		takeScreenShot("before" + fileName);
		actions.dragAndDropBy(driver.findElement(By.id("draggable")), xOffset, yOffset).perform();
		takeScreenShot("after" + fileName);
	}

	void sortable(String fileName) {
		Actions actions = new Actions(switchFrame());
		List<WebElement> findElements = driver.findElements(By.xpath("//ul[@id= 'sortable']/li"));
		WebElement firstwebElement = findElements.get(0);
		WebElement lastwebElement = findElements.get(findElements.size() - 1);
		takeScreenShot("before" + fileName);
		actions.clickAndHold(lastwebElement);
		actions.moveToElement(firstwebElement).release().perform();
		takeScreenShot("after" + fileName);
	}

	void closeBrowser() {
		driver.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DragAndDrop n = new DragAndDrop();
		n.initChrome("https://jqueryui.com/droppable/");
		n.dragAndDrop("dragdrop");
		System.out.println("drag and drop, completed");

		n.initChrome("https://jqueryui.com/draggable/");
		n.dragable("draggable");
		System.out.println("draggable, completed");

		n.initChrome("https://jqueryui.com/selectable/");
		n.selectable("selecting");
		System.out.println("mutliple selection, completed");

		n.initChrome("https://jqueryui.com/sortable/");
		n.sortable("sorting");
		System.out.println("sorting, completed");

		n.closeBrowser();
		System.out.println("browser closed");
	}

}
