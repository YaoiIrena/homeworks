package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificaitionTests extends TestBase {

    @Test
    public void testGroupModificaition()
    {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereGroup())
        {
            app.getGroupHelper().createGroup(new GroupData("test3", null, null));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModificaition();
        app.getGroupHelper().fillGroupForm(new GroupData("1", "2", "3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }
}
