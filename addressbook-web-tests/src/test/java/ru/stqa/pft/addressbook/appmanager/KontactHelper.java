package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.KontactData;

public class KontactHelper extends HelperBase {
    private boolean acceptNextAlert;

    public KontactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitKontactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillKontactForm(KontactData kontactData, boolean creation) {
      type(By.name("firstname"), kontactData.getFirstname());
      type(By.name("lastname"), kontactData.getLastname());
      type(By.name("address"), kontactData.getAddress());
      type(By.name("home"), kontactData.getHome());
      type(By.name("mobile"), kontactData.getMobile());
      type(By.name("work"), kontactData.getWork());
      type(By.name("fax"), kontactData.getFax());
      type(By.name("email"), kontactData.getEmail());
      type(By.name("email2"), kontactData.getEmail2());
      type(By.name("email3"), kontactData.getEmail3());

      if (creation)
      {
          new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(kontactData.getGroup());
      } else
      {
          Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable")))
        {
            return;
        }
        click(By.linkText("home page"));
    }

    public void goToNewKontact() {
      click(By.linkText("add new"));
    }

    public void deleteKontact() {
      click(By.xpath("//input[@value='Delete']"));
    }

    public void selectKontact() {
      click(By.name("selected[]"));
    }

    public String deleteTrue() {
       acceptNextAlert = true;

        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else
                {
                alert.dismiss();
                }
            return alertText;
        }
        finally
        {
           acceptNextAlert = true;
        }
    }

    public void initKontactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitKontactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void createKontact(KontactData kontact, boolean creation)
    {
        goToNewKontact();
        fillKontactForm(kontact, creation);
        submitKontactCreation();
        returnToHomePage();
    }

    public boolean isThereKontact() {
        return isElementPresent(By.name("selected[]"));
    }
}
