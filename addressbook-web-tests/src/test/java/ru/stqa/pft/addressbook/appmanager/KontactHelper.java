package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        click(By.linkText("home"));
    }

    public void goToNewKontact() {
      click(By.linkText("add new"));
    }

    public void deleteKontact() {
      click(By.xpath("//input[@value='Delete']"));
    }

    public void selectKontact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectKontactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
           wd.findElement(By.cssSelector("div.msgbox"));
        }
    }

    public void initKontactModification() {
        wd.findElement(By.cssSelector("img[alt=\"Edit\"]")).click();
    }

    public void submitKontactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void create(KontactData kontact, boolean creation)
    {
        goToNewKontact();
        fillKontactForm(kontact, creation);
        submitKontactCreation();
        returnToHomePage();
    }

    public void modify(KontactData contact) {
        selectKontactById(contact.getId());
        initKontactModification(); //модификация последней группы
        fillKontactForm(contact, false);
        submitKontactModification();
        returnToHomePage();
    }

    public void delete(int index) {
        selectKontact(index);
        deleteKontact();
        deleteTrue();
        returnToHomePage();
    }

    public void delete(KontactData contact) {
        selectKontactById(contact.getId());
        deleteKontact();
        deleteTrue();
        returnToHomePage();
    }

    public boolean isThereKontact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getKontaktCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<KontactData> list() {
        List<KontactData> contacts = new ArrayList<KontactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new KontactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public Set<KontactData> all() {
        Set<KontactData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new KontactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}
