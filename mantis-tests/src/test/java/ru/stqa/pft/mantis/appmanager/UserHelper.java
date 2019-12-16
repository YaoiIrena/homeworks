package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void signUp(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), username);
        click(By.xpath("//input[2]"));
        type(By.name("password"), password);
        click(By.xpath("//input[3]"));
    }

    public void resetPasswordForUser(String username) {
        wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
        click(By.linkText(username));
        click(By.xpath("//fieldset/span/input"));
    }

    public void changePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button/span"));
    }
}
