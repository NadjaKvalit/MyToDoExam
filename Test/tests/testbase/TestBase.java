package testbase;

import org.junit.jupiter.api.*;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestBase {
    // Shared between all tests in this class and subclasses.
    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;

    @BeforeAll
    static void launchBrowser() {
            Playwright playwright = Playwright.create();
            Browser browser = null;
            String browserName = System.getProperty("browser");
            if (browserName == null) {
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
                System.out.println("Running tests on browser: Chrome");
            } else {
                if (browserName.equals("chromium")) {
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
                    System.out.println("Running tests on browser: Chrome");
                    
                } else if (browserName.equals("firefox")) {
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
                    System.out.println("Running tests on browser: Firefox");
                    
                }
            }
            if (browser != null) {
                context = browser.newContext();
                page = context.newPage();
        }
    }

    @AfterAll
    static void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeEach
    protected void createContextAndPage() {
        if (browser != null) {
            context = browser.newContext();
            page = context.newPage();
        }
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    
}