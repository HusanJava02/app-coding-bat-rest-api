package uz.pdp.appcodingbat.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.service.CategoryService;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<ApiResponse> addCategory(@Valid @RequestBody Category category){
        ApiResponse add = categoryService.add(category);
        return ResponseEntity.status(HttpStatus.OK).body(add);
    }

    @GetMapping
    public HttpEntity<List<Category>> getAll(){
        List<Category> allService = categoryService.getAllService();
        return ResponseEntity.status(HttpStatus.OK).body(allService);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteById(id);
        return ResponseEntity.status(apiResponse.getSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
