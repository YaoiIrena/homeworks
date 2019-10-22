package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage(); //перейти на страницу групп
    app.initGroupCreation(); //инициал. созд. нов группы
    app.fillGroupForm(new GroupData("test3", "test4", "test5")); //заполнить форму создания
    app.submitGroupCreation(); //создать
    app.returnToGroupPage(); //вернуться в список групп
    app.Logout(); //выход УЗ
  }

}
