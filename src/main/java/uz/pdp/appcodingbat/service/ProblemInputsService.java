package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.entity.ProblemInputs;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ProblemInputsDto;
import uz.pdp.appcodingbat.repository.ProblemInputsRepository;
import uz.pdp.appcodingbat.repository.ProblemRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemInputsService {

    @Autowired
    ProblemInputsRepository problemInputsRepository;

    @Autowired
    ProblemRepository problemRepository;

    public ApiResponse addInput(ProblemInputsDto problemInputsDto) {
        ProblemInputs problemInputs = new ProblemInputs();
        Integer problemId = problemInputsDto.getProblemId();
        Optional<Problem> optionalProblem = problemRepository.findById(problemId);
        if (!optionalProblem.isPresent())
            return new ApiResponse(false, "problem not foun");
        Problem problem = optionalProblem.get();
        problemInputs.setProblem(problem);
        Object[] inputs = problemInputsDto.getInput();
        problemInputs.setInput(inputs);
        problemInputs.setOutput(problemInputsDto.getOutput());
        problemInputsRepository.save(problemInputs);
        return new ApiResponse(true, "succesfully saved");
    }

    public List<ProblemInputs> getAllProblemInputs() {
        return problemInputsRepository.findAll();
    }

    public List<ProblemInputs> getProblemInputById(Integer id){
        Optional<ProblemInputs> optional = problemInputsRepository.findById(id);
        return optional.map(Collections::singletonList).orElse(Collections.emptyList());
    }
    public ApiResponse deleteById(Integer id){
        if (problemInputsRepository.existsById(id)){
            problemInputsRepository.deleteById(id);
            return new ApiResponse(true,"deleted");
        }else return new ApiResponse(false,"not found");
    }
}
