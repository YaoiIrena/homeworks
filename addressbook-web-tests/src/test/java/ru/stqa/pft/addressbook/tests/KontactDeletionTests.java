package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.Comparator;
import java.util.List;

public class KontactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().list().size() == 0)
    {
      app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
              .withHome("+7123456").withMobile("+713467").withWork("+75678")
              .withFax("+72456").withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test1"), true);
    }
  }

@Test
  public void testKontactDeletion() throws Exception {
  List<KontactData> before = app.contact().list();
  int index = before.size() - 1;
  app.contact().delete(index);
  List<KontactData> after = app.contact().list();
  Assert.assertEquals(after.size(), before.size() - 1);

  before.remove(index);
  Comparator<? super KontactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(before, after);
  }

}
