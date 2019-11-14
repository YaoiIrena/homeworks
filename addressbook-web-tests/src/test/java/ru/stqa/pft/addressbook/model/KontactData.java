package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class KontactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String home;
    private String mobile;
    private String work;
    private String fax;
    private String email;
    private String email2;
    private String email3;
    private String group;

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
        return Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
    }

}
