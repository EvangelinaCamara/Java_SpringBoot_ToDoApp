package ca.nbcc.tasklist_app.repo;

import ca.nbcc.tasklist_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
