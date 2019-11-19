package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.KontactData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new KontactData().withFirstname("test01").withLastname("test2").withAddress("test3")
            .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456").withEmail("e@mail.ru")
            .withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test4").withPhoto(new File("resources/shark_attack.jpg"))});
    list.add(new Object[] {new KontactData().withFirstname("test01").withLastname("test2").withAddress("test3")
            .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456").withEmail("e@mail.ru")
            .withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test4").withPhoto(new File("resources/shark_attack.jpg"))});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testKontactCreation(KontactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    app.Logout();
  }

  @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("resources/shark_attack.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
