package qa.mobile.screenplay.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.*;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MyDriver implements DriverSource {

    @Override
    public WebDriver newDriver() {
        AppiumDriver<MobileElement> driverAppium = null;
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "8");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("unicodeKeyboard", "true");
        cap.setCapability("resetKeyboard", "true");
        cap.setCapability("noReset","false");
        cap.setCapability("fullReset","false");
        cap.setCapability("isHeadless","true");
        cap.setCapability("avdArgs", "-no-window");
        cap.setCapability("appPackage", "com.android.chrome");
        cap.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        String appiumServerUrl = "http://127.0.0.1:4723/wd/hub";

        try {
            driverAppium = new AppiumDriver<MobileElement>(new URL(appiumServerUrl), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return driverAppium;
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }

    public static WebDriver getChromeDriver() {
        String driverVersion = getVersion("chrome");
        if (driverVersion == null)
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        else
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).driverVersion(driverVersion).setup();
        try {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.setAcceptInsecureCerts(true);
            options.setHeadless(headlessStatus("chrome"));
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static WebDriver getFirefoxDriver() {
        String driverVersion = getVersion("firefox");
        if (driverVersion == null)
            FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        else
            FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).driverVersion(driverVersion).setup();
        return (WebDriver) new FirefoxDriver();
    }

    public static WebDriver getEdgeDriver() {
        String driverVersion = getVersion("edge");
        if (driverVersion == null)
            EdgeDriverManager.getInstance(DriverManagerType.EDGE).setup();
        else
            EdgeDriverManager.getInstance(DriverManagerType.EDGE).driverVersion(driverVersion).setup();
        return (WebDriver) new EdgeDriver();
    }

    public static WebDriver getOperaDriver() {
        String driverVersion = getVersion("opera");
        if (driverVersion == null)
            OperaDriverManager.getInstance(DriverManagerType.OPERA).setup();
        else
            OperaDriverManager.getInstance(DriverManagerType.OPERA).driverVersion(driverVersion).setup();
        return (WebDriver) new OperaDriver();
    }

    public static WebDriver getInternetExplorerDriver() {
        String driverVersion = getVersion("ie");
        if (driverVersion == null)
            InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
        else
            InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).driverVersion(driverVersion).setup();
        return (WebDriver) new InternetExplorerDriver();
    }

    public static WebDriver getChromiumDriver() {
        String driverVersion = getVersion("chromium");
        if (driverVersion == null)
            ChromiumDriverManager.getInstance(DriverManagerType.CHROMIUM).setup();
        else
            ChromiumDriverManager.getInstance(DriverManagerType.CHROMIUM).driverVersion(driverVersion).setup();
        return (WebDriver) new ChromeDriver();
    }

    private static Boolean headlessStatus(String driver) {
        String headless = SystemEnvironmentVariables.createEnvironmentVariables().getProperty(driver + ".headless");
        if (headless == null)
            headless = "false";
        return headless.equals("true");
    }

    private static String getDriver(String driver) {
        return SystemEnvironmentVariables.createEnvironmentVariables().getProperty("webdriver." + driver);
    }

    private static String getVersion(String driver) {
        return SystemEnvironmentVariables.createEnvironmentVariables().getProperty(driver + ".version");
    }
}

