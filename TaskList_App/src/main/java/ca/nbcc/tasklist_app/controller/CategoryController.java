package ca.nbcc.tasklist_app.controller;

import ca.nbcc.tasklist_app.model.Category;
import ca.nbcc.tasklist_app.service.CategoryService;
import ca.nbcc.tasklist_app.service.ToDoTaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private ToDoTaskService toDoTaskService;

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService, ToDoTaskService toDoTaskService){
        this.categoryService = categoryService;
        this.toDoTaskService = toDoTaskService;
    }

    @GetMapping(value =  "/categories")
    public String getAllCategories(Model model){
        var values = categoryService.getAllCategories();
        model.addAttribute("categories", values);

        return "categories";
    }

    @GetMapping(value = "/createCategory")
    public String create(Model model){
        model.addAttribute("category", new Category());

        return "create_category";
    }

    @PostMapping(value = "/createCategory")
    public String create(Category category){
        categoryService.save(category);
        return "redirect:categories";
    }

    @GetMapping(value = "/category/edit/{id}")
    public String edit(Model model, @PathVariable long id) {

        var entity = categoryService.get(id);
        model.addAttribute("category", entity);

        return "edit_category";
    }

    @PostMapping(value = "/edit/{id}")
    public String edit(Category category, BindingResult result) {

        categoryService.update(category);

        return "redirect:/categories";
    }

    @GetMapping(value = "/category/delete/{id}")
    public String delete(Model model, @PathVariable long id) {

        var entity = categoryService.get(id);
        var todoTasks = toDoTaskService.getByCategoryId(id);

        model.addAttribute("category", entity);
        model.addAttribute("tasks", todoTasks);

        return "delete_category";
    }

    @PostMapping(value = "/deleteCategory/{id}")
    public String deleteConfirm(@PathVariable long id) {

        categoryService.delete(id);

        return "redirect:/categories";
    }
}
