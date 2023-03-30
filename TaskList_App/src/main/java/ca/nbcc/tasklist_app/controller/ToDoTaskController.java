package ca.nbcc.tasklist_app.controller;

import ca.nbcc.tasklist_app.model.Category;
import ca.nbcc.tasklist_app.model.ToDoTask;
import ca.nbcc.tasklist_app.service.CategoryService;
import ca.nbcc.tasklist_app.service.ToDoTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ToDoTaskController {

    private ToDoTaskService toDoTaskService;

    private CategoryService categoryService;

    public ToDoTaskController(CategoryService categoryService, ToDoTaskService toDoTaskService){
        this.categoryService = categoryService;
        this.toDoTaskService = toDoTaskService;
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        var entities = categoryService.getAllCategories();
        return entities;
    }



    @GetMapping(value = {"/", "/tasks",  "/tasks/search"})
    public String getAllTasks(Model model) {
        var values = toDoTaskService.getAllTasks();
        model.addAttribute("tasks", values);

        return "todo_tasks";
    }

    @GetMapping(value = {"/tasks/category/{id}"})
    public String getReviewsByRating(Model model, @PathVariable int id) {

        var values = toDoTaskService.searchByCategory(id);
        model.addAttribute("tasks", values);

        return "todo_tasks";
    }

    @PostMapping(value = {"/tasks/search"})
    public String searchTasks(Model model, @RequestParam String search) {

        var values = toDoTaskService.search(search);
        model.addAttribute("tasks", values);

        return "todo_tasks";
    }


    @GetMapping(value = "/createTask")
    public String create(Model model){
        model.addAttribute("task", new ToDoTask());
        return "create_task";
    }

    @PostMapping(value = "/createTask")
    public String create(ToDoTask task){

        toDoTaskService.save(task);

        return "redirect:tasks";
    }

    @GetMapping(value = "/task/edit/{id}")
    public String edit(Model model, @PathVariable long id) {

        var entity = toDoTaskService.get(id);
        model.addAttribute("task", entity);

        return "edit_task";
    }

    @PostMapping(value = "/editTask/{id}")
    public String edit(ToDoTask task, BindingResult result) {

        toDoTaskService.update(task);

        return "redirect:/tasks";
    }

    @GetMapping(value = "/task/delete/{id}")
    public String delete(Model model, @PathVariable long id) {

        var entity = toDoTaskService.get(id);
//        var event = eventService.getBySeatingId(id);

        model.addAttribute("task", entity);
//        model.addAttribute("event", event);

        return "delete_task";
    }

    @PostMapping(value = "/deleteTask/{id}")
    public String deleteConfirm(@PathVariable long id) {

        toDoTaskService.delete(id);

        return "redirect:/";
    }
}
