package com.libraray.service;

import com.libraray.entity.User;
import com.libraray.enums.Role;
import java.util.Collection;

public interface UserValidator {
    void validateUsernameAndEmailAvailability(String username, String email);
    void validateEmailAvailability(String email);
    void validatePassword(String encodedPassword, String providedPassword);
    void validateAdminPermissions(User user);
    void validateHotelPermissions(User user);
    void validateAdminOrHotelPermissions(User user);
    void validateAdminPermissions();
    void validateHotelPermissions();
    void validateAdminOrHotelPermissions();
    void validatePermissions(User user, Role role);
    void validatePermissions(User user, Collection<Role> allowedRoles);
    Long getCurrentLoggedInUserId();
}
