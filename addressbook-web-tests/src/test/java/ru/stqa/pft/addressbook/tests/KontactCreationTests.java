package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.KontactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontactCreationTests extends TestBase {

  @Test
  public void testKontactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File("resources/shark_attack.jpg");
    KontactData contact = new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
            .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456").withEmail("e@mail.ru")
            .withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("1").withPhoto(photo);
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
