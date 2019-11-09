package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.List;

public class KontactDeletionTests extends TestBase {

@Test
  public void testKontactDeletion() throws Exception {
  if (! app.getKontactHelper().isThereKontact())
  {
    app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
            "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test1"), true);
  }
  List<KontactData> before = app.getKontactHelper().getKontaktList();
  app.getKontactHelper().selectKontact(before.size() - 1);
  app.getKontactHelper().deleteKontact();
  app.getKontactHelper().deleteTrue();
  app.getKontactHelper().returnToHomePage();
  List<KontactData> after = app.getKontactHelper().getKontaktList();
  Assert.assertEquals(after.size(), before.size() - 1);

  before.remove(before.size() - 1);
  Assert.assertEquals(before, after);
  }

}
