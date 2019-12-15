package ru.stqa.pft.mantis.model;

public class Issue {

    private int id;
    private int status_id;
    private String status;
    private String summary;
    private String description;
    private Project project;

    public int getStatus_id() {
        return status_id;
    }

    public Issue withStatus_id(int status_id) {
        this.status_id = status_id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
