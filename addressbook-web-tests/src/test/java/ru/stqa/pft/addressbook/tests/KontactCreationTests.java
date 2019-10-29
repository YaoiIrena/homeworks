package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

public class KontactCreationTests extends TestBase {

  @Test
  public void testKontactCreation() throws Exception {
    app.getKontactHelper().goToNewKontact();
    app.getKontactHelper().fillKontactForm(new KontactData("test1", "test2", "test3",
            "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test3"), true);
    app.getKontactHelper().submitKontactCreation();
    app.getKontactHelper().returnToHomePage();
    app.Logout();
  }

}
