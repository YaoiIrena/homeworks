package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage(); //перейти на страницу групп
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("test1");
    //int before = app.getGroupHelper().getGroupCount();
    app.group().create(group);
    List<GroupData> after = app.group().list();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);

    //список - поток, в потоке сравниваются объекты и выбирается max, после берется ID
    group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    app.Logout(); //выход УЗ
  }

}
