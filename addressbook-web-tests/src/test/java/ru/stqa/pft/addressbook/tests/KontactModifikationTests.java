package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

public class KontactModifikationTests extends TestBase
{
    @Test
    public void testKontactModification()
    {
        int before = app.getKontactHelper().getKontaktCount();
        if (! app.getKontactHelper().isThereKontact())
        {
            app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
                    "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test1"), true);
        }
        app.getKontactHelper().selectKontact(before - 1); //модификация последней группы
        app.getKontactHelper().initKontactModification();
        app.getKontactHelper().fillKontactForm(new KontactData("test113", "test343", "test31333",
                "+71234516", "+7131467", "+721456", "+756718", "e11@mail.ru", "e112@mail.ru", "e311@mail.ru", null), false);
        app.getKontactHelper().submitKontactModification();
        app.getKontactHelper().returnToHomePage();
        int after = app.getKontactHelper().getKontaktCount();
        Assert.assertEquals(after, before);
    }
}
