package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressesEmailsPhonesTests extends TestBase {

    public KontactData contact;
    public KontactData contactInfoFromEditForm;

    public void bekommeContacts(){
        app.contact().returnToHomePage();
        this.contact = app.contact().all().iterator().next();
        this.contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }

    @Test
    public void testContactAddress(){
        bekommeContacts();

        assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    @Test
    public void testContactEmail(){
        bekommeContacts();

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    @Test
    public void testContactPhones(){
        bekommeContacts();

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(KontactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork()).stream().filter((s) -> ! s.equals("")).map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(KontactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().
                filter((s) -> ! s.equals("")).map(ContactEmailTests::cleaned).collect(Collectors.joining("\n"));
    }

    public String cleaned(String address){
        return address.replaceAll("\\s","").replaceAll("[-()']","");
    }
}
