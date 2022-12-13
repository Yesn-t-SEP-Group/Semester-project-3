using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using WebDriverManager;
using WebDriverManager.DriverConfigs.Impl;
using Xunit;

[assembly: CollectionBehavior(DisableTestParallelization = true)]
namespace BlazorTest // Note: actual namespace depends on the project name.
{
    [Collection("Test before logging in")]
    public class BeforeLogin
    {
        private static ChromeDriver driver;

        [Fact]
        public void TestLoginButton()
        {
            new DriverManager().SetUpDriver(new ChromeConfig());

            // Set up the Chrome driver
            driver = new ChromeDriver();

            // Navigate to the login page
            driver.Navigate().GoToUrl("https://localhost:7201/Login");

            // Wait for the page to load
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(5000);

            // Find the username input field and enter the user name
            var userNameInput = driver.FindElement(By.ClassName("userName"));
            userNameInput.SendKeys("bob");

            // Find the password input field and enter the password
            var passwordInput = driver.FindElement(By.ClassName("password"));
            passwordInput.SendKeys("builder");

            // Find the login button and click it to submit the form
            var loginButton = driver.FindElement(By.CssSelector(".login-form .btn"));
            loginButton.Click();

            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(3000);

            // Wait for the page title to change
            WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(10));
            wait.Until(d => d.Title == "Index");

            Assert.Equal("https://localhost:7201/", driver.Url);
        }

        [Collection("Test after logging in")]
        public class LoggedInTests
        {
            [Fact]
            public void TesCreatePost()
            {
                // Navigate to the login page
                driver.Navigate().GoToUrl("https://localhost:7201/CreatePost");
                driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromMilliseconds(5000);

                // Find the input fields and enter text
                var titleField = driver.FindElement(By.Id("postTitle"));
                titleField.SendKeys("My Post Title");

                var descriptionField = driver.FindElement(By.Id("postBody"));
                descriptionField.SendKeys("My Post Description");

                var locationField = driver.FindElement(By.Id("location"));
                locationField.SendKeys("My Post Location");

                // Select a value from the dropdown
                var categoryDropdown = driver.FindElement(By.Id("selectedValue"));
                categoryDropdown.Click();
                var categoryOption = driver.FindElement(By.CssSelector("option[value='my_category']"));
                categoryOption.Click();

                var pictureField = driver.FindElement(By.Id("picture"));
                pictureField.SendKeys("https://example.com/my_picture.jpg");

                var priceField = driver.FindElement(By.Id("price"));
                priceField.SendKeys("100");

                // Find the button and click it
                var createButton = driver.FindElement(By.ClassName("acceptbtn"));
                createButton.Click();
            }
        }
    }
}