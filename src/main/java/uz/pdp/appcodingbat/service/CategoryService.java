package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Category;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse add(Category category) {
        categoryRepository.save(category);
        return new ApiResponse(true,"successfully");
    }

    public List<Category> getAllService() {
       return categoryRepository.findAll();
    }

    public ApiResponse deleteById(Integer id) {
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return new ApiResponse(true,"succesfully deleted");
        }else return new ApiResponse(false,"not found category");
    }
}
