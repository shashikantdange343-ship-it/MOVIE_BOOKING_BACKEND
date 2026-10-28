package com.shashi.bookmovie.Services.Movie;

import com.shashi.bookmovie.Entity.Movie.Category;
import com.shashi.bookmovie.Exceptons.ResourceNotFoundException;
import com.shashi.bookmovie.Repository.Movie.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long categoryId) {

       Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

       if (optionalCategory.isEmpty()){
           throw new ResourceNotFoundException("Category Not Found !");
       }

        return optionalCategory.get();
    }
}
