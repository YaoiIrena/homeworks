package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<KontactData> result = session.createQuery("from KontactData where deprecated = '0000-00-00'", KontactData.class).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public KontactData getContactById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        KontactData contact = session.get(KontactData.class, id);
        session.getTransaction().commit();
        session.close();
        return contact;
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData", GroupData.class).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public GroupData selectGroupForDeleteContact() {
        Groups groups = groups();
        for (GroupData group : groups) {
            if (group.getContacts().size() > 0) {
                return group;
            }
        } return null;
    }

    public GroupData selectedGroup(Groups groups, KontactData contact) {
        Groups selectedGroups = contact.getGroups();
        for (GroupData group : groups) {
            if (! selectedGroups.contains(group)) {
                return group;
            }
        }
        return null;
    }

    public KontactData selectContactWithMaxId() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        KontactData result = (KontactData) session.createQuery("from KontactData where deprecated = '0000-00-00' and" +
                "id = (select max(KontactData.id) from KontactData)").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
