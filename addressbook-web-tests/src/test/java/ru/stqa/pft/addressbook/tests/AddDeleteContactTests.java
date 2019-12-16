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

        app.contact().returnToHomePage();
    }

    @Test
    public void testAddContact() {
        KontactData foundContact = null;
        GroupData foundGroup = null;
        for (KontactData contact : app.db().contacts()) {
            Groups contactGroups = contact.getGroups();
            for (GroupData group : app.db().groups()) {
                if (!contactGroups.contains(group)) {
                    foundContact = contact;
                    foundGroup = group;
                    break;
                }
            }
            if (foundContact != null) {
                break;
            }
        }

        if (foundContact == null) {
            // если нет подходящего контакта, то создаем его
            app.contact().create(new KontactData().withFirstname("test1").withLastname("test2").withAddress("test3")
                    .withHome("+7123456").withMobile("+713467").withWork("+75678").withFax("+72456")
                    .withEmail("e@mail.ru").withEmail2("e2@mail.ru").withEmail3("e3@mail.ru"), true);

            // находим только что добавленный контакт c max id и добавляем в группу
            KontactData contact = app.db().selectContactWithMaxId();
            app.contact().selectKontactById(contact.getId());

            GroupData firstGroup = app.db().groups().iterator().next();
            // добавляем контакт в группу
            app.contact().selectGroup(firstGroup);
            app.contact().addInGroup();

            contact = app.db().getContactById(contact.getId());
            Assert.assertTrue(contact.getGroups().contains(firstGroup));
        } else {
            // выбираем контакт
            app.contact().selectKontactById(foundContact.getId());
            // добавляем контакт в группу
            app.contact().selectGroup(foundGroup);
            app.contact().addInGroup();

            foundContact = app.db().getContactById(foundContact.getId());
            Assert.assertTrue(foundContact.getGroups().contains(foundGroup));
        }
    }

    @Test
    public void testDeleteContact() {
        KontactData contact = app.db().contacts().stream().filter(kontact -> kontact.getGroups().size() > 0).findFirst().orElse(null);
        if (contact == null) {
            KontactData firstContact = app.db().contacts().iterator().next();
            GroupData firstGroup = app.db().groups().iterator().next();

            // выбираем контакт
            app.contact().selectKontactById(firstContact.getId());
            // добавляем контакт в группу
            app.contact().selectGroup(firstGroup);
            app.contact().addInGroup();
            app.contact().returnToHomePage();

            firstContact = app.db().getContactById(firstContact.getId());
            Assert.assertTrue(firstContact.getGroups().contains(firstGroup));

            // удаляем контакт из группы
            app.contact().selectContactFromGroup(firstContact, firstGroup);
            app.contact().deleteFromGroup();

            firstContact = app.db().getContactById(firstContact.getId());
            Assert.assertFalse(firstContact.getGroups().contains(firstGroup));
        } else {
            GroupData firstGroup = contact.getGroups().iterator().next();
            // удаляем контакт из группы
            app.contact().selectContactFromGroup(contact, firstGroup);
            app.contact().deleteFromGroup();

            contact = app.db().getContactById(contact.getId());
            Assert.assertFalse(contact.getGroups().contains(firstGroup));
        }
    }
}
