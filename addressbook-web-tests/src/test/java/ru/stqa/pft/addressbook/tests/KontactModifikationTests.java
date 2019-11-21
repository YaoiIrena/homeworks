package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.KontactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class KontactModifikationTests extends TestBase
{
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
                    .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                    .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru").withGroup("test 1"), true);
        }
    }

    @Test
    public void testKontactModification()
    {
        Contacts before = app.contact().all();
        KontactData modifiedContact = before.iterator().next();
        KontactData contact = new KontactData().withId(modifiedContact.getId()).withFirstname("test 1").withLastname("test 2").withAddress("test 3")
                .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
