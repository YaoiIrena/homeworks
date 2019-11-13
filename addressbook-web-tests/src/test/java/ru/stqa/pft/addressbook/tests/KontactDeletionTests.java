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
    if (! app.getKontactHelper().isThereKontact())
    {
      app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
              "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test1"), true);
    }
  }

@Test
  public void testKontactDeletion() throws Exception {
  List<KontactData> before = app.getKontactHelper().getKontaktList();
  int index = before.size() - 1;
  app.getKontactHelper().selectKontact(index);
  app.getKontactHelper().deleteKontact();
  app.getKontactHelper().deleteTrue();
  app.getKontactHelper().returnToHomePage();
  List<KontactData> after = app.getKontactHelper().getKontaktList();
  Assert.assertEquals(after.size(), index);

  before.remove(index);
  Comparator<? super KontactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
  before.sort(byId);
  after.sort(byId);
  Assert.assertEquals(before, after);
  }

}
