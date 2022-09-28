package pruebaII;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testing {

	private WebDriver driver;
	private WriteExcelFile writeFile;
	private ReadExcelFile readFile;
	private By searchBoxLocator = By.className("SearchBar-module_searchBar__Input__1VvhT");
	private By searchBtnLocator = By.className("SearchBar-module_searchBtnIcon__6KVum");
	private By resultTextLocator = By.id("testId-SearchLandingContainer-totalResults");

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		writeFile = new WriteExcelFile();
		readFile = new ReadExcelFile();

		driver.get("https://www.falabella.com.co/falabella-co");
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
	}

	@Test
	public void test() throws IOException {
		
		
		String filepath = "C:\\Users\\ASUS\\Downloads\\test.xlsx";

		String searchText = readFile.getCellValue(filepath, "Sheet1", 0, 0);
		

		driver.findElement(searchBoxLocator).clear();

		driver.findElement(searchBoxLocator).sendKeys(searchText);
		driver.findElement(searchBtnLocator).click();
		String resultText = driver.findElement(resultTextLocator).getText();

		System.out.println("Page result text:" + resultText);

		readFile.readExcel(filepath, "Sheet1");

		writeFile.writeCellValue(filepath, "Sheet1", 0, 1, resultText);

		readFile.readExcel(filepath, "Sheet1");

	}


}
