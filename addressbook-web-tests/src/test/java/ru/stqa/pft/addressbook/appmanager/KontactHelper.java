package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.ArrayList;
import java.util.List;

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
      attach(By.name("photo"), kontactData.getPhoto());

      if (creation)
      {
          if (kontactData.getGroups().size() > 0){
            Assert.assertTrue(kontactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(kontactData.getGroups().iterator().next().getName());
          }
      } else {
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

    private void initKontactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            contacts.add(new KontactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public KontactData infoFromEditForm(KontactData contact) {
        initKontactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new KontactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHome(home).withMobile(mobile).withWork(work);
    }

    public void selectGroup(GroupData group) {
        wd.findElement(By.name("to_group")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        wd.findElement(By.cssSelector("select[name=\"to_group\"] > option[value='" + group.getId() + "']")).click();
    }

    public void addInGroup() {
        wd.findElement(By.name("add")).click();
    }

    public void selectFromGroup(GroupData group) {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
        wd.findElement(By.cssSelector("option[value='" + group.getId() + "']")).click();
    }

    public void deleteFromGroup() {
        wd.findElement(By.name("remove")).click();
    }

    public void selectContactFromGroup(KontactData contact, GroupData group) {
        selectFromGroup(group);
        selectKontactById(contact.getId());
    }
}
