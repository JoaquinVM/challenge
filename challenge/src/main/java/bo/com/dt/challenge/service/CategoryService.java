package bo.com.dt.challenge.service;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.model.entity.Category;
import bo.com.dt.challenge.service.dto.category.CategoryAddDto;
import bo.com.dt.challenge.service.dto.category.CategoryQueryDto;
import bo.com.dt.challenge.service.dto.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(long pCategoryId) throws NotFoundException;
    List<CategoryQueryDto> getCategories(State state, String filter);
    List<CategoryQueryDto> getCategoriesFilterByName(State state, String filterName);
    CategoryQueryDto addCategory(CategoryAddDto pCategoryAddDto) throws BadRequestException;
    CategoryQueryDto updateCategory(long pCategoryId, CategoryUpdateDto pCategoryUpdateDto) throws NotFoundException, BadRequestException;
    void deleteCategory(long pCategoryId) throws NotFoundException;
    void addDishToCategory(long pCategoryId, long pDishId) throws NotFoundException;
}
