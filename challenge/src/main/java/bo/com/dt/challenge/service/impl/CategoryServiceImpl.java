package bo.com.dt.challenge.service.impl;

import bo.com.dt.challenge.constants.Constants;
import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.exception.message.Message;
import bo.com.dt.challenge.exception.message.MessageError;
import bo.com.dt.challenge.model.entity.Category;
import bo.com.dt.challenge.model.entity.Dish;
import bo.com.dt.challenge.model.entity.QCategory;
import bo.com.dt.challenge.model.repository.CategoryRepository;
import bo.com.dt.challenge.service.CategoryService;
import bo.com.dt.challenge.service.DishService;
import bo.com.dt.challenge.service.dto.category.CategoryAddDto;
import bo.com.dt.challenge.service.dto.category.CategoryQueryDto;
import bo.com.dt.challenge.service.dto.category.CategoryUpdateDto;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    private DishService dishService;

    @Override
    public Category getCategoryById(long pCategoryId) throws NotFoundException {
        Category vCategory = categoryRepository.getCategoryById(pCategoryId).orElse(null);
        if (vCategory == null) {
            Object[] vObj = {Constants.CATEGORY, pCategoryId};
            throw new NotFoundException(MessageError.GetExceptionBasic(Message.NOT_FOUND, vObj), CategoryService.class);
        }
        return vCategory;
    }

    @Override
    public List<CategoryQueryDto> getCategories(State state, String filter) {
        QCategory vQCategory = QCategory.category;
        List<Category> vCategories;
        if (filter != null) {
            BooleanExpression vCondition = vQCategory.name.containsIgnoreCase(filter)
                    .and(vQCategory.state.eq(state))
                    .and(vQCategory.description.containsIgnoreCase(filter));
            vCategories = Lists.newArrayList(categoryRepository.findAll(vCondition));
        } else {
            vCategories = categoryRepository.getCategoriesByState(state);
        }

        List<CategoryQueryDto> vCategoryQueryDtoList = new ArrayList<>();
        for (Category vCategory : vCategories) {
            vCategoryQueryDtoList.add(this.generateCategoryQueryDto(vCategory));
        }
        return vCategoryQueryDtoList;
    }

    @Override
    public List<CategoryQueryDto> getCategoriesFilterByName(State state, String filterName) {
        QCategory vQCategory = QCategory.category;
        BooleanBuilder vBooleanBuilder = new BooleanBuilder();
        vBooleanBuilder.and(vQCategory.state.eq(state));
        if (filterName != null) {
            vBooleanBuilder.and(vQCategory.name.containsIgnoreCase(filterName));
        }

        List<Category>vCategories = Lists.newArrayList(categoryRepository.findAll(vBooleanBuilder));
        List<CategoryQueryDto> vCategoryQueryDtoList = new ArrayList<>();
        for (Category vCategory : vCategories) {
            vCategoryQueryDtoList.add(this.generateCategoryQueryDto(vCategory));
        }
        return vCategoryQueryDtoList;
    }

    @Override
    public CategoryQueryDto addCategory(CategoryAddDto pCategoryAddDto) throws BadRequestException {
        if (categoryRepository.isCategoryNameRepeated(pCategoryAddDto.getName())) {
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.CATEGORY_NAME_DUPLICATE, pCategoryAddDto.getName()), CategoryService.class);
        }

        Category vCategory = new Category();
        BeanUtils.copyProperties(pCategoryAddDto, vCategory);
        categoryRepository.save(vCategory);
        return this.generateCategoryQueryDto(vCategory);
    }

    @Override
    public CategoryQueryDto updateCategory(long pCategoryId, CategoryUpdateDto pCategoryUpdateDto) throws NotFoundException, BadRequestException {
        Category vCategory = this.getCategoryById(pCategoryId);

        if (!vCategory.getName().equals(pCategoryUpdateDto.getName()) && categoryRepository.isCategoryNameRepeated(pCategoryUpdateDto.getName())) {
            throw new BadRequestException(MessageError.GetExceptionBasic(Message.CATEGORY_NAME_DUPLICATE, pCategoryUpdateDto.getName()), CategoryService.class);
        }

        BeanUtils.copyProperties(pCategoryUpdateDto, vCategory);
        categoryRepository.save(vCategory);
        return this.generateCategoryQueryDto(vCategory);
    }

    @Override
    public void deleteCategory(long pCategoryId) throws NotFoundException {
        Category vCategory = this.getCategoryById(pCategoryId);
        vCategory.setDeletedDate(LocalDateTime.now());
        categoryRepository.save(vCategory);
    }

    @Override
    public void addDishToCategory(long pCategoryId, long pDishId) throws NotFoundException {
        Category vCategory = this.getCategoryById(pCategoryId);
        Dish vDish = dishService.getDishById(pDishId);
        vCategory.getDishes().add(vDish);
        categoryRepository.save(vCategory);
    }

    private CategoryQueryDto generateCategoryQueryDto(Category pCategory) {
        CategoryQueryDto vCategoryQueryDto = new CategoryQueryDto();
        BeanUtils.copyProperties(pCategory, vCategoryQueryDto);
        return vCategoryQueryDto;
    }
}
