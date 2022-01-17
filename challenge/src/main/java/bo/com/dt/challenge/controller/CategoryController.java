package bo.com.dt.challenge.controller;

import bo.com.dt.challenge.constants.State;
import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.service.CategoryService;
import bo.com.dt.challenge.service.dto.category.CategoryAddDto;
import bo.com.dt.challenge.service.dto.category.CategoryQueryDto;
import bo.com.dt.challenge.service.dto.category.CategoryUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Categoy Api Rest", description = "Endpoint para gestionar categorias")
@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Lista categorias")
    @GetMapping(path = "")
    public ResponseEntity<List<CategoryQueryDto>> getCategories(@ApiParam(value = "Estado") @RequestParam State state,
                                                                @ApiParam(value = "Filtro") @RequestParam(required = false) String filter) {
        return new ResponseEntity<>(categoryService.getCategories(state, filter), HttpStatus.OK);
    }

    @ApiOperation(value = "Crea una categoria")
    @PostMapping(path = "")
    public ResponseEntity<CategoryQueryDto> addCategory(@ApiParam(value = "Datos de categoria") @Valid @RequestBody CategoryAddDto categoryAddDto) throws BadRequestException {
        return new ResponseEntity<>(categoryService.addCategory(categoryAddDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualzia una categoria")
    @PutMapping(path = "{categoryId}")
    public ResponseEntity<CategoryQueryDto> updateCategory(@ApiParam(value = "Identificador de categoria") @PathVariable long categoryId,
                                                           @ApiParam(value = "Datos de categoria") @Valid @RequestBody CategoryUpdateDto categoryUpdateDto) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryUpdateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Elimina una categoria")
    @DeleteMapping(path = "{categoryId}")
    public ResponseEntity<Void> deleteCategory(@ApiParam(value = "Identificador de categoria") @PathVariable long categoryId) throws NotFoundException {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Asigna un plato a una categoria")
    @PutMapping(path = "{categoryId}/addDish")
    public ResponseEntity<Void> addDish(@ApiParam(value = "Identificador de categoria") @PathVariable long categoryId,
                                        @ApiParam(value = "Identificador del plato") @RequestParam long dishId) throws NotFoundException {
        categoryService.addDishToCategory(categoryId, dishId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
