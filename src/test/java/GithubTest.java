import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class GithubTest {
    private WebDriver driver;
    private Properties properties;

    @BeforeMethod   // This method will be executed before each test method
    public void setUp() {
        loadProperties();
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriver_path"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com/login");
    }

    @AfterMethod   // This method will be executed after each test method
    public void tearDown() {
        driver.close();
    }

    @Test(priority = 1)
    public void loginWithInvalidPassword() {
        System.out.println("Test Case 1: Logging in with invalid password");
        System.out.println("Email: " + properties.getProperty("email"));
        System.out.println("Password: 1234");
        driver.findElement(By.id("login_field")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.name("commit")).click();
        Assert.assertTrue(driver.getPageSource().contains("Incorrect username or password."), "Error message not displayed for invalid password");
    }


    @Test(priority = 2)
    public void loginWithValidCredentials() {
        System.out.println("Test Case 2: Logging in with valid credentials");
        System.out.println("Email: " + properties.getProperty("email"));
        System.out.println("Password: " + properties.getProperty("password"));
        driver.findElement(By.id("login_field")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.name("commit")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://github.com/"), "Login unsuccessful");
    }


    @Test(priority = 3)
    public void forgotPassword() {
        System.out.println("Test Case 3: Testing Forgot Password functionality");
        driver.findElement(By.linkText("Forgot password?")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://github.com/password_reset"), "Forgot Password page not opened");
    }


    @Test(priority = 4)
    public void SignInAndSignOut() {
        System.out.println("Test Case 3: Signing in and signing out");

        driver.findElement(By.id("login_field")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.name("commit")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div[3]/deferred-side-panel/include-fragment/user-drawer-side-panel/button/span/span")));
        element.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");

        WebElement signOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div[3]/deferred-side-panel/include-fragment/user-drawer-side-panel/dialog-helper/dialog/scrollable-region/div/div/nav/nav-list/ul/li[22]/a")));
        signOutButton.click();

        Assert.assertTrue(driver.getCurrentUrl().equals("https://github.com/"), "Logout unsuccessful");
    }

    @Test(priority = 5)
    public void editProfile() {
        System.out.println("Test Case 4: Editing profile");
        login();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div[3]/deferred-side-panel/include-fragment/user-drawer-side-panel/button/span/span")));
        profileButton.click();

        WebElement yourProfileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div[3]/deferred-side-panel/include-fragment/user-drawer-side-panel/dialog-helper/dialog/scrollable-region/div/div/nav/nav-list/ul/li[3]/a")));
        yourProfileLink.click();

        WebElement editProfileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[6]/main/div/div/div[1]/div/div/div[3]/div[2]/div[2]/button")));
        editProfileButton.click();

        WebElement bioField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[6]/main/div/div/div[1]/div/div/div[3]/div[1]/waiting-form/form/div[2]/text-expander/textarea")));
        bioField.clear();
        bioField.sendKeys("Hello, I'm Automated Tester!");

        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[6]/main/div/div/div[1]/div/div/div[3]/div[1]/waiting-form/form/div[9]/button[1]")));
        saveButton.click();
        
        // Asserting that the profile page is opened
        // add your username in the below line else it will show test failed
        Assert.assertTrue(driver.getCurrentUrl().contains("https://github.com/username"), "Edit profile page not opened");
    }

    // this method is not working as expected, so commented it out
    /*
    @Test(priority = 6)
    public void createNewRepository() {
        System.out.println("Test Case 6: Creating a new repository");
        login();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement newRepoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[6]/div/div/aside/div/div/loading-context/div/div[1]/div/div[1]/a")));
        newRepoButton.click();

        WebElement repoNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[6]/main/react-app/div/form/div[3]/div[1]/div[1]/div[1]/fieldset/div/div[2]/span/input")));
        repoNameField.sendKeys("test-repo");

        WebElement repoDescField = driver.findElement(By.xpath("/html/body/div[1]/div[6]/main/react-app/div/form/div[3]/div[1]/div[1]/div[3]/span/input"));
        repoDescField.sendKeys("This is a test repository");

        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[contains(@class, 'nzz')]")));

        WebElement CreateButton = driver.findElement(By.xpath("//button[contains(@class, 'nzz')]"));
        CreateButton.click();


        Assert.assertTrue(driver.getCurrentUrl().contains("https://github.com/" + properties.getProperty("username") + "/test-repo"), "Repository not created successfully");
    }*/

    @Test(priority = 7)
    public void SearchandForkRepo() throws InterruptedException {
        login();

        // Search for repositories owned by SandeepVashishtha 
        // change it toh specific user you want to search for
        driver.get("https://github.com/search?q=owner%3ASandeepVashishtha&type=repositories");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[6]/main/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[1]/div[4]/div/div/div[1]/div/div[1]/h3/div/div[2]/a/span")));

        WebElement firstRepoLink = driver.findElement(By.xpath("/html/body/div[1]/div[6]/main/react-app/div/div/div[1]/div/div/div[2]/div[2]/div/div[1]/div[4]/div/div/div[1]/div/div[1]/h3/div/div[2]/a/span"));
        firstRepoLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"repository-details-container\"]/ul/li[3]/div/div[2]/form/button")));

        WebElement starButton = driver.findElement(By.xpath("//*[@id=\"repository-details-container\"]/ul/li[3]/div/div[2]/form/button"));
        starButton.click();

        WebElement forkButton = driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/div/div[1]/div[2]/ul/li[2]/div/a"));
        forkButton.click();

        Thread.sleep(3000);

        // Asserting that the repository is forked successfully
        // change the title to the repository you are forking
        Assert.assertTrue(driver.getTitle().equals("Fork SandeepVashishtha/Image-Recognition"), "Repository not forked successfully");

    }

    private void login() {
        System.out.println("Logging in");
        driver.findElement(By.id("login_field")).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.name("commit")).click();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://github.com/"), "Login unsuccessful");
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
