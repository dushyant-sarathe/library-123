package com.libraray.utils;

import com.libraray.entity.Address;
import com.libraray.entity.User;
import com.libraray.enums.Gender;
import com.libraray.enums.Role;
import com.libraray.exception.UnauthorizedException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationUtils {

    public static List<Predicate> createPersonPredicates(Root<?> root, CriteriaBuilder cb, String email, String firstName, String lastName, String phone, Gender gender, Address address) {

        List<Predicate> predicates = new ArrayList<>();

        if (email != null && !email.isEmpty()) {
            predicates.add(cb.like(root.get("email"), "%" + email + "%"));
        }

        if (firstName != null && !firstName.isEmpty()) {
            predicates.add(cb.like(root.get("firstName"), "%" + firstName + "%"));
        }

        if (lastName != null && !lastName.isEmpty()) {
            predicates.add(cb.like(root.get("lastName"), "%" + lastName + "%"));
        }

        if (phone != null && !phone.isEmpty()) {
            predicates.add(cb.like(root.get("phone"), "%" + phone + "%"));
        }



        if (gender != null) {
            predicates.add(cb.equal(root.get("gender"), gender));
        }



//        if (contactDetails != null) {
//
//            if (contactDetails.getFaxNumber() != null && !contactDetails.getFaxNumber().isEmpty()) {
//                predicates.add(cb.like(root.get("contactDetails").get("faxNumber"), "%" + contactDetails.getFaxNumber() + "%"));
//            }
//            if (contactDetails.getOfficeNumber() != null && !contactDetails.getOfficeNumber().isEmpty()) {
//                predicates.add(cb.like(root.get("contactDetails").get("officeNumber"), "%" + contactDetails.getOfficeNumber() + "%"));
//            }
//
//        }

        if (address != null) {
//            if (address.getCompCity() != null && !address.getCompCity().isEmpty()) {
//                predicates.add(cb.like(root.get("address").get("city"), "%" + address.getCompCity() + "%"));
//            }

            if (address.getStreetName() != null && !address.getStreetName().isEmpty()) {
                predicates.add(cb.like(root.get("address").get("streetAddress"), "%" + address.getStreetName() + "%"));
            }

            if (address.getPostalCode() != null && !address.getPostalCode().isEmpty()) {
                predicates.add(cb.like(root.get("address").get("postalCode"), "%" + address.getPostalCode() + "%"));
            }

//            if (address.getState() != null && !address.getState().isEmpty()) {
//                predicates.add(cb.like(root.get("address").get("state"), "%" + address.getState() + "%"));
//            }

            if (address.getCountry() != null && !address.getCountry().isEmpty()) {
                predicates.add(cb.like(root.get("address").get("country"), "%" + address.getCountry() + "%"));
            }
        }

        return predicates;
    }

    public static Specification<User> createRoleBasedSpecification(Role currentUserRole) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Role.ROLE_ADMIN.equals(currentUserRole)) {
                // Admin can search for everyone, so we don't add any additional condition
            } else if (Role.ROLE_LIBRARY.equals(currentUserRole)) {
                // The hotel can only search for customers
                predicates.add(cb.equal(root.get("role"), Role.ROLE_CLIENT));
            } else {
                throw new UnauthorizedException("Clients are not allowed to search for users");
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
