package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage(); //перейти на страницу групп
    app.getGroupHelper().createGroup(new GroupData("test3", null, null));
    app.Logout(); //выход УЗ
  }

}
