package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.Comparator;
import java.util.List;

public class KontactCreationTests extends TestBase {

  @Test
  public void testKontactCreation() throws Exception {
    List<KontactData> before = app.contact().list();
    KontactData contact = new KontactData("test1", "test2", "test3",
            "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "1");
    app.contact().create(contact, true);
    List<KontactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super KontactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
    app.Logout();
  }

}
