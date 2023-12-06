import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.BrowserContext;
//import com.microsoft.playwright.BrowserType;
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import pagebase.PageBase;
import testbase.TestBase;

public class App extends TestBase{
  @Test
  // public static void main(String[] args){

  public void testNavigation() {
    //Playwright playwright = Playwright.create();
    //Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    //BrowserContext context = browser.newContext();
    //Page page = context.newPage();
    PageBase pageBase = new PageBase(page);
    pageBase.openPage();
		Response response = pageBase.responseOpenPage();
    //Response response = page.navigate("https://playwright.dev/");
    System.out.println(response.status());
    Assertions.assertEquals(response.status(), 200);

  }
}