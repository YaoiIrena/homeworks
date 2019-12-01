package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class KontactData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String home;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String work;
    @Column(name = "fax")
    @Type(type = "text")
    private String fax;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }

    public KontactData withPhoto(File photo) {
        this.photo = photo.getPath();
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

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public Groups getGroups() {
        return new Groups(groups);
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
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(address, that.address) &&
                Objects.equals(home, that.home) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(work, that.work) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address, home, mobile, work, email, email2, email3);
    }
}
