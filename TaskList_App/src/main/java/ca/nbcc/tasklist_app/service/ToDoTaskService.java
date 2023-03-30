package ca.nbcc.tasklist_app.service;

import ca.nbcc.tasklist_app.model.Category;
import ca.nbcc.tasklist_app.model.ToDoTask;
import ca.nbcc.tasklist_app.repo.ToDoTaskRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoTaskService {
    private ToDoTaskRepository todotaskRepo;

    public ToDoTaskService(ToDoTaskRepository todotaskRepo){
        this.todotaskRepo = todotaskRepo;
    }

    public List<ToDoTask> getAllTasks() {
        return todotaskRepo.findAll();
    }

    public void save(ToDoTask task){
        todotaskRepo.save(task);
    }

    public ToDoTask get(long id) {
        var entity = todotaskRepo.findById(id);
        if(entity.isPresent()){
            return entity.get();
        }
        return null;
    }

    public void update(ToDoTask task) {
        todotaskRepo.save(task);
    }

    public void delete(long id) {
        todotaskRepo.deleteById(id);
    }

    public List<ToDoTask> getByCategoryId(long categoryId) {
        return todotaskRepo.getTasksByCategoryId(categoryId);
    }

    public List<ToDoTask> search(String content) {
        return todotaskRepo.searchByNameContainingIgnoreCase(content);
    }

    public List<ToDoTask> search(Category category) {
        return todotaskRepo.findAllByCategory(category);
    }

    public List<ToDoTask> searchByCategory(int id) {
        return todotaskRepo.findAllByCategoryId(id);
    }
}
