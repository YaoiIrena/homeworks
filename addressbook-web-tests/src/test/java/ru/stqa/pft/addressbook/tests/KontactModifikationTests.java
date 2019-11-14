package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class KontactModifikationTests extends TestBase
{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
                    .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                    .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test1"), true);
        }
    }

    @Test
    public void testKontactModification()
    {
        Set<KontactData> before = app.contact().all();
        KontactData modifiedContact = before.iterator().next();
        KontactData contact = new KontactData().withId(modifiedContact.getId()).withFirstname("test1").withLastname("test2").withAddress("test3")
                .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru");
        app.contact().modify(contact);
        Set<KontactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
