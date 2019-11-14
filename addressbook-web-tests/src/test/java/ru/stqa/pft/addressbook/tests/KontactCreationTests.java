package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class KontactCreationTests extends TestBase {

  @Test
  public void testKontactCreation() throws Exception {
    Set<KontactData> before = app.contact().all();
    KontactData contact = new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
            .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456").withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test1");
    app.contact().create(contact, true);
    Set<KontactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
    app.Logout();
  }

}
