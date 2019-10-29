package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

public class KontactModifikationTests extends TestBase
{
    @Test
    public void testKontactModification()
    {
        if (! app.getKontactHelper().isThereKontact())
        {
            app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
                    "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test3"), true);
        }
        app.getKontactHelper().selectKontact();
        app.getKontactHelper().initKontactModification();
        app.getKontactHelper().fillKontactForm(new KontactData("test11", "test21", "test31",
                "+71234516", "+7131467", "+721456", "+756718", "e11@mail.ru", "e112@mail.ru", "e311@mail.ru", null), false);
        app.getKontactHelper().submitKontactModification();
        app.getKontactHelper().returnToHomePage();
    }
}
