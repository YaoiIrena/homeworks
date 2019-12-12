package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.KontactData;

public class AddDeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        if (app.db().contacts().size() == 0) {
            app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
                    .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                    .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru"), true);
        }
    }

    @Test
    public void testAddDeleteContact() {
        if (app.group().selectGroupForDeleteContact() == null) {
            app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
                    .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                    .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru"), true);
        }

        KontactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();

        if (contact.getGroups().contains(group)) {
            // удаляем контакт из группы
            app.contact().selectContactFromGroup(contact, group);
            app.contact().deleteFromGroup();
            app.contact().returnToHomePage();

            contact = app.db().getContactById(contact.getId());
            Assert.assertFalse(contact.getGroups().contains(group));
        }

        Groups groups = app.db().groups();
        GroupData foundGroup = app.group().selectedGroup(groups, contact);
        if (foundGroup != null) {
            app.contact().returnToHomePage();
            app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
                    .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                    .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru"), true);
        } //находим только что добавленный контакт c max id и добавляем в группу
        app.db().selectContactWithMaxId();
        app.contact().selectKontactById(contact.getId());
        // добавляем контакт в группу group.getName()
        app.contact().selectGroup(group);
        app.contact().addInGroup();
        app.contact().returnToHomePage();

        contact = app.db().getContactById(contact.getId());
        Assert.assertTrue(contact.getGroups().contains(group));
    }
}
