package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "access_level")
    private short accessLevel;

    public UserData() {
    }

    public UserData(int id, String username, String email, short accessLevel) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(short accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                accessLevel == userData.accessLevel &&
                Objects.equals(username, userData.username) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, accessLevel);
    }
}
