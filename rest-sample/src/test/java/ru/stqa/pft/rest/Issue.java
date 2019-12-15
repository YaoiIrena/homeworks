package ru.stqa.pft.rest;

import java.util.Objects;

public class Issue {

    private int id;
    private String status;
    private String subject;
    private String describtion;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return describtion;
    }

    public Issue withDescription(String describtion) {
        this.describtion = describtion;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(status, issue.status) &&
                Objects.equals(subject, issue.subject) &&
                Objects.equals(describtion, issue.describtion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, subject, describtion);
    }
}
