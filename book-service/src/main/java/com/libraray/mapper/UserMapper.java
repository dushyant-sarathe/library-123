package com.libraray.mapper;



import com.libraray.dto.PersonDTO;
import com.libraray.dto.SignUp;
import com.libraray.entity.User;
import com.libraray.enums.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper<T> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonDTO convertToDTO(T entity) {
        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(entity, personDTO);
        return personDTO;
    }

    public User convertToEntity(SignUp signUpDto){
        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setFirstName(signUpDto.getFirstName());
        user.setEmail(signUpDto.getEmail());
        user.setPhone(signUpDto.getPhone());
//        user.setContactDetails(signUpDto.getContactDetails());

        user.setGender(signUpDto.getGender());
        user.setAddress(signUpDto.getAddress());
        user.setLastName(signUpDto.getLastName());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = determineUserRole(signUpDto.getRole());
        user.setRole(role);
        return user;
    }
    private Role determineUserRole(String requestedRole) {
        return (requestedRole != null) ? Role.valueOf(requestedRole.toUpperCase()) : Role.ROLE_CLIENT;
    }

}
