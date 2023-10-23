package drivendata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class drivendata1
	{
		
		WebDriver driver;
		
		String url = ("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		@BeforeTest
		public void tc() {
			
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			
			driver.get(url);
			
		}
		@Test
		
		public void ts() throws IOException, InterruptedException {
			
			File excel = new File("C://Users//user//Downloads//datadriven.xlsx/");
			
			FileInputStream fis = new FileInputStream(excel);
			
			XSSFWorkbook book = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = book.getSheet("Sheet1");
			
			int rowcount = sheet.getLastRowNum();
			
			for(int i=0; i<=rowcount; i++) {
				
				String Username = sheet.getRow(i).getCell(0).getStringCellValue();
				String password = sheet.getRow(i).getCell(1).getStringCellValue();
				driver.findElement(By.name("username")).sendKeys(Username);
				Thread.sleep(2000);
				driver.findElement(By.name("password")).sendKeys(password);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(2000);
			   if(driver.getCurrentUrl().equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index")) {
				   
			   driver.findElement(By.className("oxd-userdropdown-name")).click();
			   Thread.sleep(2000);
			   driver.findElement(By.linkText("Logout")).click();
			   Thread.sleep(2000);
			   }
			   else if (driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")).isDisplayed())
					   {
				   for( int j =i+1; i<=rowcount; j++ ) 
				   {
					   
					   String username = sheet.getRow(j).getCell(0).getStringCellValue();
					   String Password = sheet.getRow(j).getCell(1).getStringCellValue();
					   Thread.sleep(2000);
					   driver.findElement(By.name("username")).sendKeys(username);
					   Thread.sleep(2000);
						driver.findElement(By.name("password")).sendKeys(Password);
						Thread.sleep(2000);
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						Thread.sleep(2000);
						
					if(driver.getCurrentUrl().equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"))
					{
						
						System.out.println("errors");
						  driver.findElement(By.className("oxd-userdropdown-name")).click();
						   Thread.sleep(2000);
						   driver.findElement(By.linkText("Logout")).click();
						   Thread.sleep(2000);
					}
					   
				   }
			}
			   
			   
			}


}
	}

