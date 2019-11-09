package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.List;

public class KontactCreationTests extends TestBase {

  @Test
  public void testKontactCreation() throws Exception {
    List<KontactData> before = app.getKontactHelper().getKontaktList();
    //int before = app.getKontactHelper().getKontaktCount();
    app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
            "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test1"), true);
    //int after = app.getKontactHelper().getKontaktCount();
    List<KontactData> after = app.getKontactHelper().getKontaktList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.Logout();
  }

}
