package pagebase;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

public class PageBase {
	
    //String url = "https://playwright.dev/";
    String url = "http://127.0.0.1:5500/Frontend/index.html";

	public Page page;

	public PageBase() {
	};

	public PageBase(Page page) {
		this.page = page;
	}

	public void openPage() {
		page.navigate(url);
	}

public Response responseOpenPage() {
        Response response = page.navigate(url);
        return response;
    }

	public Page getPage() {
		return page;
	}

	public String getUrl() {
		return url;
	}

}