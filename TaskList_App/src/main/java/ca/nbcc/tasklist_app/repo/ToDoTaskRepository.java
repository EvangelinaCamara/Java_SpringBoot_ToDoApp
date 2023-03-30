package ca.nbcc.tasklist_app.repo;

import ca.nbcc.tasklist_app.model.Category;
import ca.nbcc.tasklist_app.model.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoTaskRepository extends JpaRepository<ToDoTask, Long> {
    List<ToDoTask> searchByNameContainingIgnoreCase(String content);

    List<ToDoTask> findAllByCategory(Category category);

    List<ToDoTask> getTasksByCategoryId(long categoryId);


    List<ToDoTask> findAllByCategoryId(int id);
}
