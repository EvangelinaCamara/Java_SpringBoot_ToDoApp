package ca.nbcc.tasklist_app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATEGORY_TABLE")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ToDoTask> tasks;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToDoTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<ToDoTask> tasks) {
        this.tasks = tasks;
    }

    public Category() {
    }

    public Category(String name, List<ToDoTask> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
}
