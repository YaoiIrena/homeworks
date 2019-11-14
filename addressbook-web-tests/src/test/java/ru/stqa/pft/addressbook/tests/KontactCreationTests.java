package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.KontactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KontactCreationTests extends TestBase {

  @Test
  public void testKontactCreation() throws Exception {
    Contacts before = app.contact().all();
    KontactData contact = new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
            .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456").withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test1");
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    app.Logout();
  }

}
