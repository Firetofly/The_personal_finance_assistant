/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.model.Category;
import firefly.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category findById(long id){
        return categoryRepository.findById(id);
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }
}
