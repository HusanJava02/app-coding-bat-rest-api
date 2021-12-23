package uz.pdp.appcodingbat.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.ProblemInputs;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ProblemInputsDto;
import uz.pdp.appcodingbat.service.ProblemInputsService;

import java.util.List;

@RequestMapping(value = "/api/problem-input")
@RestController
public class ProblemInputsController {
    @Autowired
    ProblemInputsService problemInputsService;

    @PostMapping
    public HttpEntity<ApiResponse> add(@RequestBody ProblemInputsDto problemInputsDto){
        ApiResponse apiResponse = problemInputsService.addInput(problemInputsDto);
        return ResponseEntity.status(apiResponse.getSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<List<ProblemInputs>> get(@PathVariable Integer id){
        List<ProblemInputs> problemInputById = problemInputsService.getProblemInputById(id);
        return ResponseEntity.status(problemInputById.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK).body(problemInputById);
    }

    @GetMapping
    public HttpEntity<List<ProblemInputs>> get(){
        List<ProblemInputs> allProblemInputs = problemInputsService.getAllProblemInputs();
        return ResponseEntity.status(allProblemInputs.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK).body(allProblemInputs);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = problemInputsService.deleteById(id);
        return ResponseEntity.status(apiResponse.getSuccess()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
    }

}
