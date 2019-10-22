package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage(); //перейти на страницу групп
    initGroupCreation(); //инициал. созд. нов группы
    fillGroupForm(new GroupData("test3", "test4", "test5")); //заполнить форму создания
    submitGroupCreation(); //создать
    returnToGroupPage(); //вернуться в список групп
    Logout(); //выход УЗ
  }

}
