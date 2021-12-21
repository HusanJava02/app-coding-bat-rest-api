package uz.pdp.appcodingbat.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ProblemDto;
import uz.pdp.appcodingbat.service.ProblemService;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/problem")
public class ProblemController {
    @Autowired
    ProblemService problemService;


    /**
     *
     * @return All Problems in db
     */
    @GetMapping
    public HttpEntity<List<Problem>> getAllProblems(){
        List<Problem> problems = problemService.getAll();
        return ResponseEntity.status(problems.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK).body(problems);
    }

    /**
     *
     * @param sectionId id of section (bolimni id si)
     * @return List of problems with given section id
     */
    @GetMapping("/BySection")
    public HttpEntity<List<Problem>> getProblemsBySectionId(@RequestParam(name = "section") Integer sectionId){
        List<Problem> problems = problemService.getBySectionId(sectionId);
        return ResponseEntity.status(problems.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK).body(problems);
    }

    /**
     *
     * @param problemDto data transfer object of Problem entity
     * @return ApiResponse tells that about how operation had been
     */
    @PostMapping
    public HttpEntity<ApiResponse> add(@Valid @RequestBody ProblemDto problemDto){
        ApiResponse apiResponse = problemService.addProblem(problemDto);
        return ResponseEntity.status(apiResponse.getSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    /**
     *
     * @param problemDto editing problem data transfer object
     * @param id of editing problem
     * @return Apiresponse
     */
    @PutMapping(value = "/{id}")
    public HttpEntity<ApiResponse> edit(@Valid @RequestBody ProblemDto problemDto, @PathVariable Integer id){
        ApiResponse apiResponse = problemService.editService(problemDto, id);
        return ResponseEntity.status(apiResponse.getSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = problemService.deleteById(id);
        return ResponseEntity.status(apiResponse.getSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
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
