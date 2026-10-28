package com.shashi.bookmovie.Repository.Movie;

import com.shashi.bookmovie.Entity.Movie.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Long> {
}
