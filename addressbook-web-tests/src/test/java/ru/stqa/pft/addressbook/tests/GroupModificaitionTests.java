package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificaitionTests extends TestBase {

    @Test
    public void testGroupModificaition()
    {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().initGroupModificaition();
        app.getGroupHelper().fillGroupForm(new GroupData("1", "2", "3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
