package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class KontactDeletionTests extends TestBase {

@Test
  public void testKontactDeletion() throws Exception {
  app.getKontactHelper().selectKontact();
  app.getKontactHelper().deleteKontact();
  app.getKontactHelper().deleteTrue();
    }

}
