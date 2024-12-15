package com.libraray.service;



import com.libraray.dto.PersonDTO;
import com.libraray.dto.PersonRequest;

import java.util.List;

public interface PersonService {
    String updatePersonalData(PersonRequest personRequest);

    List<PersonDTO> getAll();
}
