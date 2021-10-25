/*
 * Copyright (c)
 */

package firefly.repository;

import firefly.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findById(long id);

    Category findByName(String name);

    Category save(Category category);

}
