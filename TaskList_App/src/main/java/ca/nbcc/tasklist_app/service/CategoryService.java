package ca.nbcc.tasklist_app.service;

import ca.nbcc.tasklist_app.model.Category;
import ca.nbcc.tasklist_app.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepo;

    public CategoryService (CategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public void save(Category category){
        categoryRepo.save(category);
    }

    public Category get(long id) {
        var entity = categoryRepo.findById(id);
        if(entity.isPresent()){
            return entity.get();
        }
        return null;
    }

    public void update(Category category) {
        categoryRepo.save(category);
    }

    public void delete(long id) {
        categoryRepo.deleteById(id);
    }


}
