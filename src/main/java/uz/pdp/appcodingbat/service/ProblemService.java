package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.entity.Section;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.ProblemDto;
import uz.pdp.appcodingbat.repository.ProblemRepository;
import uz.pdp.appcodingbat.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private SectionRepository sectionRepository;
    public List<Problem> getAll(){
        return problemRepository.findAll();
    }

    public List<Problem> getBySectionId(Integer sectionId){
        return problemRepository.findAllBySectionId(sectionId);
    }

    public ApiResponse addProblem(ProblemDto problemDto) {
        Problem problem = new Problem();
        problem.setDescription(problemDto.getDescription());
        problem.setName(problemDto.getName());
        problem.setTemplateCode(problemDto.getTemplateCode());
        Optional<Section> optionalSection = sectionRepository.findById(problemDto.getSectionId());
        if (!optionalSection.isPresent())
            return new ApiResponse(false,"not found section");
        problem.setSection(optionalSection.get());
        problemRepository.save(problem);
        return new ApiResponse(true,"succesfully added");
    }

    public ApiResponse editService(ProblemDto problemDto, Integer id) {
        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (!optionalProblem.isPresent())
            return new ApiResponse(false,"not found problem with given id");
        Problem problem = optionalProblem.get();
        problem.setName(problemDto.getName());
        problem.setDescription(problemDto.getDescription());
        problem.setTemplateCode(problemDto.getTemplateCode());
        Optional<Section> optionalSection = sectionRepository.findById(problemDto.getSectionId());
        if (!optionalSection.isPresent()){
            return new ApiResponse(false,"section not found");
        }
        Section section = optionalSection.get();
        problem.setSection(section);
        problemRepository.save(problem);
        return new ApiResponse(true,"successfully saved");
    }

    public ApiResponse deleteById(Integer id) {
        if (problemRepository.existsById(id)){
            problemRepository.deleteById(id);
            return new ApiResponse(true,"succesfully deleted");
        }else return new ApiResponse(false,"not found problem");
    }
}
