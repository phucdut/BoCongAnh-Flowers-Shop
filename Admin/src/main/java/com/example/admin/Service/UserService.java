package com.example.admin.Service;

import com.example.admin.Domain.StaffDTO;
import com.example.admin.Domain.User;
import com.example.admin.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllStaff();
    boolean addStaff(StaffDTO user);

    User getStaffById(Long userId);

    void detailStaff(User user);

    void updateStaff(StaffDTO user);

    void deleteStaffById(Long userId);

    void restoreStaffById(Long userId);

    void resetPassword(Long id);

    Optional<UserEntity> getUserWithAuthority();
}
