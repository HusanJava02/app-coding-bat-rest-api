package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.entity.Section;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.SectionDto;
import uz.pdp.appcodingbat.repository.CategoryRepository;
import uz.pdp.appcodingbat.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addSection(SectionDto sectionDto){
        Integer categoryId = sectionDto.getCategoryId();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent())
            return new ApiResponse(false,"category not found");
        Category category = optionalCategory.get();
        Section section = new Section();
        section.setDescription(sectionDto.getDescription());
        section.setName(sectionDto.getName());
        section.setCategory(category);
        sectionRepository.save(section);
        return new ApiResponse(true,"succesfully saved");
    }

    public List<Section> getAllSections(){
        return sectionRepository.findAll();
    }


    public ApiResponse deleteById(Integer id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return new ApiResponse(true,"successfully deleted");
        }else return new ApiResponse(false,"not found");
    }
}
