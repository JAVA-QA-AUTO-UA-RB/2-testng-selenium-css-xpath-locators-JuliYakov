import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

// This test class inherits from BasicSetupTest, where the browser is initialized.
// The variable browser is available in this class as it is inherited and can be used anywhere.

public class SeleniumTestngTest extends BasicSetupTest {

    @Test
    public void abTestingPageHasSpecificTextTest() {
        // Navigate to the "A/B Testing" page
        browser.get("https://the-internet.herokuapp.com/");
        browser.manage().window().maximize();
        WebElement abTestingTaskLink = browser.findElement(By.cssSelector("a[href='/abtest']"));
        abTestingTaskLink.click();

        // Verify the presence of the text "A/B Test Control"
        WebElement heading = browser.findElement(By.xpath("//h3[contains(text(),'A/B Test Control')]"));
        Assert.assertTrue(heading.isDisplayed(), "The heading 'A/B Test Control' is not displayed!");
    }

    @Test
    public void testAddRemoveElements() {
        // Navigate to the "Add/Remove Elements" page
        browser.get("https://the-internet.herokuapp.com/add_remove_elements/");

        // Add three "Delete" buttons
        WebElement addButton = browser.findElement(By.cssSelector("button[onclick='addElement()']"));
        addButton.click();
        addButton.click();
        addButton.click();

        // Check that three "Delete" buttons are added and visible
        Assert.assertEquals(browser.findElements(By.cssSelector(".added-manually")).size(), 3, "Not all 'Delete' buttons are present!");

        // Remove all "Delete" buttons
        for (WebElement deleteButton : browser.findElements(By.cssSelector(".added-manually"))) {
            deleteButton.click();
        }

        // Check that buttons are removed
        Assert.assertTrue(browser.findElements(By.cssSelector(".added-manually")).isEmpty(), "Not all 'Delete' buttons were removed!");
    }

    @Test
    public void testCheckboxes() {
        // Navigate to the "Checkboxes" page
        browser.get("https://the-internet.herokuapp.com/checkboxes");

        // Select all checkboxes
        for (WebElement checkbox : browser.findElements(By.cssSelector("input[type='checkbox']"))) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected(), "Checkbox is not selected!");
        }
    }

    @Test
    public void testDropdown() {
        // Navigate to the "Dropdown" page
        browser.get("https://the-internet.herokuapp.com/dropdown");

        // Select "Option 2"
        WebElement dropdown = browser.findElement(By.cssSelector("#dropdown"));
        dropdown.click();
        WebElement option2 = browser.findElement(By.xpath("//option[@value='2']"));
        option2.click();

        // Verify the option is selected
        Assert.assertTrue(option2.isSelected(), "Option 2 is not selected!");
    }

    @Test
    public void testFormAuthentication() {
        // Navigate to the "Form Authentication" page
        browser.get("https://the-internet.herokuapp.com/login");

        // Enter login and password
        browser.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
        browser.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");
        browser.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify successful login
        WebElement logoutButton = browser.findElement(By.cssSelector("a.button.secondary.radius"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not displayed!");

        // Log out
        logoutButton.click();
        WebElement loginButton = browser.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed after logout!");
    }

    @Test
    public void testDragAndDrop() {
        // Navigate to the "Drag and Drop" page
        browser.get("https://the-internet.herokuapp.com/drag_and_drop");

        // Locate the elements to be dragged and dropped
        WebElement columnA = browser.findElement(By.cssSelector("#column-a"));
        WebElement columnB = browser.findElement(By.cssSelector("#column-b"));

        // Create an instance of Actions class
        Actions actions = new Actions(browser);

        // Perform the drag and drop action
        actions.dragAndDrop(columnA, columnB).perform();

        // Verify the text of the columns has swapped
        String columnATextAfter = columnA.getText();
        String columnBTextAfter = columnB.getText();

        Assert.assertEquals(columnATextAfter, "B", "Column A text did not change to B!");
        Assert.assertEquals(columnBTextAfter, "A", "Column B text did not change to A!");
    }

    @Test
    public void testHorizontalSlider() {
        // Navigate to the "Horizontal Slider" page
        browser.get("https://the-internet.herokuapp.com/horizontal_slider");

        WebElement slider = browser.findElement(By.cssSelector("input[type='range']"));
        WebElement value = browser.findElement(By.cssSelector("#range"));

        // Set the slider value to 4.5 by cyclically moving right
        while (!value.getText().equals("4.5")) {
            slider.sendKeys(org.openqa.selenium.Keys.ARROW_RIGHT);
        }

        // Verify the value has changed
        Assert.assertEquals(value.getText(), "4.5", "Slider value is incorrect!");
    }
}
