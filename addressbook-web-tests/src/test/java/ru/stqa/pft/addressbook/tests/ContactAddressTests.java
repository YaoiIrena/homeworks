package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.KontactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @Test
    public void testContactAddress(){
        app.contact().returnToHomePage();
        KontactData contact = app.contact().all().iterator().next();
        KontactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    public String cleaned(String address){
        return address.replaceAll("\\s","").replaceAll("[-()']","");
    }
}
