package uz.pdp.appcodingbat.controler;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Section;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.SectionDto;
import uz.pdp.appcodingbat.service.SectionService;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/api/section")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @PostMapping
    public HttpEntity<ApiResponse> addSection(@Valid @RequestBody SectionDto sectionDto){
        ApiResponse apiResponse = sectionService.addSection(sectionDto);
        return ResponseEntity.status(apiResponse.getSuccess()? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<List<Section>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(sectionService.getAllSections());
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = sectionService.deleteById(id);
        return ResponseEntity.status(apiResponse.getSuccess()?HttpStatus.ACCEPTED:HttpStatus.NOT_FOUND).body(apiResponse);
    }

}
