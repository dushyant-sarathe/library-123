package com.libraray.service.impl;



import com.libraray.dto.PersonDTO;
import com.libraray.dto.PersonRequest;
import com.libraray.entity.Person;
import com.libraray.enums.Gender;
import com.libraray.mapper.UserMapper;
import com.libraray.repository.PersonRepository;
import com.libraray.service.PersonService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@NoArgsConstructor
public abstract class PersonServiceImpl<T extends Person> implements PersonService {
    public abstract PersonRepository<T> getRepository();
    @Autowired
    @Setter
    protected UserMapper<T> userMapper;

    public String updatePersonalData(PersonRequest request) {
        T person = getRepository().findById(request.getId()).orElseThrow(() -> new RuntimeException("Person not found"));


        long personId = person.getId();
        boolean updateOccurred = false;






        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            getRepository().updateFirstName(personId, request.getFirstName());
            updateOccurred = true;
        }

        if (request.getLastName() != null && !request.getLastName().isEmpty()) {
            getRepository().updateLastName(personId, request.getLastName());
            updateOccurred = true;
        }

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            getRepository().updatePhone(personId, request.getPhone());
            updateOccurred = true;
        }


        if (request.getAddress() != null) {
            getRepository().updateAddress(personId, request.getAddress());
            updateOccurred = true;
        }
//        if (request.getContactDetails() != null) {
//            getRepository().updateContactDetails(personId, request.getContactDetails());
//            updateOccurred = true;
//        }



        if (request.getGender() != null) {
            Gender newGender = Gender.valueOf(request.getGender().name());
            getRepository().updateGender(personId, newGender);
            updateOccurred = true;
        }

        if (updateOccurred) {
            log.info("Update successful");
            return "Personal data updated successfully!";
        } else {
            throw new IllegalArgumentException("No valid field specified for update.");
        }
    }

    @Override
    public List<PersonDTO> getAll() {
        List<T> persons = getRepository().findAll();
        return persons.stream().map(this.userMapper::convertToDTO).toList();
    }
}
