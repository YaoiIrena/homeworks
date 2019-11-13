package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;
import java.util.Comparator;
import java.util.List;

public class KontactModifikationTests extends TestBase
{
    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getKontactHelper().isThereKontact()) {
            app.getKontactHelper().createKontact(new KontactData("test1", "test2", "test3",
                    "+7123456", "+713467", "+72456", "+75678", "e@mail.ru", "e2@mail.ru", "e3@mail.ru", "test1"), true);
        }
    }

    @Test
    public void testKontactModification()
    {
        List<KontactData> before = app.getKontactHelper().getKontaktList();
        int index = before.size() - 1;
        KontactData contact = new KontactData(before.get(index).getId(),"test0", "test343", "test31333",
                "+71234516", "+7131467", "+721456", "+756718", "e11@mail.ru", "e112@mail.ru", "e311@mail.ru", null);
        app.getKontactHelper().modifyContact(index, contact);
        List<KontactData> after = app.getKontactHelper().getKontaktList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super KontactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
