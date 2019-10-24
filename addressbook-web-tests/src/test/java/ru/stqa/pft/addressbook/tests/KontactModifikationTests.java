package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

public class KontactModifikationTests extends TestBase
{
    @Test
    public void testKontactModification()
    {
        app.getKontactHelper().selectKontact();
        app.getKontactHelper().initKontactModification();
        app.getKontactHelper().fillKontactForm(new KontactData("test11", "test21", "test31",
                "+71234516", "+7131467", "+721456", "+756718", "e11@mail.ru", "e112@mail.ru", "e311@mail.ru"));
        app.getKontactHelper().submitKontactModification();
        app.getKontactHelper().returnToHomePage();
    }
}
