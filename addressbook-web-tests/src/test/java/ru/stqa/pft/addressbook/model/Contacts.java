package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<KontactData> {

    private Set<KontactData> delegate;

    public Contacts (Contacts contacts){
        this.delegate = new HashSet<KontactData>(contacts.delegate);
    }

    public Contacts (){
        this.delegate = new HashSet<KontactData>();
    }

    @Override
    protected Set<KontactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(KontactData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(KontactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
