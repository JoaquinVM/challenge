package bo.com.dt.challenge.test;

import bo.com.dt.challenge.exception.BadRequestException;
import bo.com.dt.challenge.exception.NotFoundException;
import bo.com.dt.challenge.exception.message.Message;
import bo.com.dt.challenge.exception.message.MessageError;
import bo.com.dt.challenge.model.entity.Category;
import bo.com.dt.challenge.model.entity.Dish;
import bo.com.dt.challenge.model.entity.Restaurant;
import bo.com.dt.challenge.model.repository.CategoryRepository;
import bo.com.dt.challenge.model.repository.DishRepository;
import bo.com.dt.challenge.service.dto.restaurant.RestaurantAddDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoUnit.MONTHS;

@Api(tags = "Test Api Rest", description = "Endpoint para realizar pruebas")
@Slf4j
@RestController
@RequestMapping("api/test")
public class TestController {
    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    public TestController(CategoryRepository categoryRepository, DishRepository dishRepository) {
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    @ApiOperation(value = "test")
    @PutMapping(path = "")
    public ResponseEntity<String> test(@ApiParam(value = "Datos de la sucursal") @Valid @RequestBody RestaurantAddDto restaurantAddDto) throws BadRequestException, NotFoundException, ParseException {
        String format = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        Date vDate = new SimpleDateFormat(format).parse("2022-12-10T23:54:00.000");
        String vFormattedDate = new SimpleDateFormat(format).format(vDate);
        LocalDateTime vLocalDateTime = LocalDateTime.parse(vFormattedDate, DateTimeFormatter.ofPattern(format));
        vLocalDateTime = vLocalDateTime.with((temporal) -> temporal.with(DAY_OF_MONTH, 9).plus(1, MONTHS)).withHour(23).withMinute(59).withSecond(59);
        return new ResponseEntity<>(vLocalDateTime.format(DateTimeFormatter.ISO_DATE_TIME), HttpStatus.OK);
    }

    @ApiOperation(value = "test")
    @GetMapping(path = "getDishesByCategory")
    public ResponseEntity<List<Long>> getDishesByCategory(@ApiParam(value = "Datos de la sucursal") @RequestParam long categoyId) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(dishRepository.getDishesByCategory(categoyId).stream().map(Dish::getId).collect(Collectors.toList()), HttpStatus.OK);
    }

    @ApiOperation(value = "test")
    @GetMapping(path = "getCategoriesByDish")
    public ResponseEntity<List<Long>> getCategoriesByDish(@ApiParam(value = "Datos de la sucursal") @RequestParam long dishId) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(categoryRepository.getCategoriesByDish(dishId).stream().map(Category::getId).collect(Collectors.toList()), HttpStatus.OK);
    }
}
