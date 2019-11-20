package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class KontactData {
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String address;
    @Expose
    private String home;
    @Expose
    private String mobile;
    @Expose
    private String work;
    private String fax;
    @Expose
    private String email;
    @Expose
    private String email2;
    @Expose
    private String email3;
    @Expose
    private String group;
    private String allPhones;
    private String allEmails;
    private File photo;

    public File getPhoto() {
        return photo;
    }

    public KontactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public KontactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public KontactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

        public KontactData withId(int id) {
        this.id = id;
        return this;
    }

    public KontactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public KontactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public KontactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public KontactData withHome(String home) {
        this.home = home;
        return this;
    }

    public KontactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public KontactData withWork(String work) {
        this.work = work;
        return this;
    }

    public KontactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public KontactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public KontactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public KontactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public KontactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public int getId() {return id; }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHome() {
        return home;
    }

    public String getMobile() {
        return mobile;
    }

    public String getWork() {
        return work;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    @Override
    public String toString() {
        return "KontactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KontactData that = (KontactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
