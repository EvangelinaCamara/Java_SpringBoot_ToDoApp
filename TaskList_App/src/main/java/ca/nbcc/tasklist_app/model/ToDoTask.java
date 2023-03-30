package ca.nbcc.tasklist_app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TODO_TASK_TABLE")
public class ToDoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_TASK_ID")
    private long id;

    private String status;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ToDoTask() {
    }

    public ToDoTask(String status, String name, String description, Category category) {
        this.status = status;
        this.name = name;
        this.description = description;
        this.category = category;
    }
}
