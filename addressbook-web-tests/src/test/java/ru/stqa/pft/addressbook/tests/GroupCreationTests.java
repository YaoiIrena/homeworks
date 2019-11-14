package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage(); //перейти на страницу групп
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test1");
    //int before = app.getGroupHelper().getGroupCount();
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
    app.Logout(); //выход УЗ
  }

}
