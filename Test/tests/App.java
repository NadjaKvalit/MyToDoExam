//ackage tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
//

public class App {
  @Test
  // public static void main(String[] args){

  public void testNavigation() {
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    // Page page = browser.newPage();
    BrowserContext context = browser.newContext();
    Page page = context.newPage();
    Response response = page.navigate("https://playwright.dev/");
    System.out.println(response.status());
    Assertions.assertEquals(response.status(), 200);

  }
}