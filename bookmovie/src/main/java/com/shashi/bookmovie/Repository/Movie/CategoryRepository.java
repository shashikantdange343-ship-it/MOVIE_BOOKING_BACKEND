package com.shashi.bookmovie.Repository.Movie;

import com.shashi.bookmovie.Entity.Movie.Category;
import com.shashi.bookmovie.Entity.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Long> {

}
