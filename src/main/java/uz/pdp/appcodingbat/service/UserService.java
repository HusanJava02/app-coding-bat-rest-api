package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse addUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setSolvedProblems(new ArrayList<>());
        userRepository.save(user);
        userRepository.save(user);
        return new ApiResponse(true,"succesfully added user");
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public ApiResponse deleteUser(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new ApiResponse(true,"succesfully deleted");
        }
        else return new ApiResponse(false,"not found");
    }
}
