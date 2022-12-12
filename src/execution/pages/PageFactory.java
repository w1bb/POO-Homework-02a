package execution.pages;

import execution.pages.auth.*;
import execution.pages.unauth.LoginPage;
import execution.pages.unauth.RegisterPage;
import execution.pages.unauth.UnauthHomePage;

public class PageFactory {
    private PageFactory() {
    }

    public static Page getPage(String pageName) {
        return switch (pageName) {
            case "login" -> LoginPage.getInstance();
            case "register" -> RegisterPage.getInstance();
            case "movies" -> MoviesPage.getInstance();
            case "upgrades" -> UpgradesPage.getInstance();
            case "see details" -> SeeDetailsPage.getInstance();
            case "logout" -> LogoutPage.getInstance();
            case "auth-homepage" -> AuthHomePage.getInstance();
            case "unauth-homepage" -> UnauthHomePage.getInstance();
            default -> null;
        };
    }
}
