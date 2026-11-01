package com.shashi.bookmovie.DTOs.MovieDTOs;

public class CategoryDTOs {

    public record CategoryDTO(
            Long id,

            String categoryName
    ){}
}
