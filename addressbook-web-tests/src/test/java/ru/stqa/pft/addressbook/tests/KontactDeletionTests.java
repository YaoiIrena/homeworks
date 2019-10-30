package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

public class KontactDeletionTests extends TestBase {

@Test
  public void testKontactDeletion() throws Exception {
  if (! app.getKontactHelper().isThereKontact())
  {
    app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
            "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test3"), true);
  }
  app.getKontactHelper().selectKontact();
  app.getKontactHelper().deleteKontact();
  app.getKontactHelper().deleteTrue();
    }

}
