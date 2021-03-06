package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class KontactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    GroupData group = new GroupData();
    if (app.db().contacts().size() == 0)
    {
      app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
              .withHome("+7123456").withMobile("+713467").withWork("+75678")
              .withFax("+72456").withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").inGroup(group), true);
    }
  }

@Test
  public void testKontactDeletion() throws Exception {
  Contacts before = app.db().contacts();
  KontactData deletedContact = before.iterator().next();
  app.contact().delete(deletedContact);
  Contacts after = app.db().contacts();
  //assertEquals(after.size(), before.size() - 1);
  assertThat(after, equalTo(before.without(deletedContact)));
  verifyContactListInUI();
  }

}
