package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage(); //перейти на страницу групп
    app.getGroupHelper().initGroupCreation(); //инициал. созд. нов группы
    app.getGroupHelper().fillGroupForm(new GroupData("test3", null, null)); //заполнить форму создания
    app.getGroupHelper().submitGroupCreation(); //создать
    app.getGroupHelper().returnToGroupPage(); //вернуться в список групп
    app.Logout(); //выход УЗ
  }

}
