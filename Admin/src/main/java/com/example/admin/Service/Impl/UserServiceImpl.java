package com.example.admin.Service.Impl;

import com.example.admin.Converter.UserConverter;
import com.example.admin.Domain.ProductDTO;
import com.example.admin.Domain.StaffDTO;
import com.example.admin.Domain.User;
import com.example.admin.Entity.ProductEntity;
import com.example.admin.Entity.UserEntity;
import com.example.admin.Repository.StaffRepository;
import com.example.admin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StaffRepository staffRepository;

    @Value("${imagePathProduct}")
    private String imagePath;
    @Override
    public List<User> getAllStaff() {
        return staffRepository.findAll().stream().map(UserConverter::toModel).toList();
    }
    @Override
    public boolean addStaff(StaffDTO user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();

        Optional<UserEntity> optionalUserName = staffRepository.findAll().stream()
                .filter(entity -> entity.getUsername().equals(user.getUsername()))
                .findFirst();
        Optional<UserEntity> optionalPhoneUser = staffRepository.findAll().stream()
                .filter(entity -> entity.getPhone().equals(user.getPhone()))
                .findFirst();
        if (optionalUserName.isPresent() || optionalPhoneUser.isPresent() ||
                user.getUsername() == null || user.getPassword() == null ||
                user.getFullName() == null || user.getPhone() == null ){
            return false;
        }else {
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
            userEntity.setFullName(user.getFullName());
            forSaveImage(user, userEntity);
            userEntity.setPhone(user.getPhone());
            userEntity.setAddress(user.getAddress());
            userEntity.setBirthday(user.getBirthday());
            userEntity.setSalary(user.getSalary());
            userEntity.setRole(user.getRole());
            userEntity.setDeleted(false);

            staffRepository.save(userEntity);

            return true;
        }
    }
    @Override
    public User getStaffById(Long userId) {
        return UserConverter.toModel(staffRepository.findById(userId).orElseThrow());
    }
    @Override
    public void detailStaff(User user) {
    }
    @Override
    public void updateStaff(StaffDTO user) {
        UserEntity userEntity = staffRepository.findById(user.getId()).orElseThrow();
        MultipartFile imageFile = user.getImage();

        if(imageFile != null){
            forSaveImage(user, userEntity);
        }
        if(user.getBirthday() != null){
            userEntity.setBirthday(user.getBirthday());
        }
        userEntity.setFullName(user.getFullName());
        userEntity.setUsername(user.getUsername());
        userEntity.setPhone(user.getPhone());
        userEntity.setAddress(user.getAddress());
        userEntity.setSalary(user.getSalary());
        userEntity.setRole(user.getRole());

        staffRepository.save(userEntity);
    }
    @Override
    public void deleteStaffById(Long userId) {
        UserEntity userEntity = staffRepository.findById(userId).orElseThrow();
        userEntity.setDeleted(true);
        staffRepository.save(userEntity);
    }
    @Override
    public void restoreStaffById(Long userId) {
        UserEntity userEntity = staffRepository.findById(userId).orElseThrow();
        userEntity.setDeleted(false);
        staffRepository.save(userEntity);
    }
    @Override
    public void resetPassword(Long id) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = staffRepository.findById(id).orElseThrow();

        // Khôi phục mật khẩu thành mật khẩu mặc định (ví dụ: chuỗi rỗng)
        String defaultPassword = "1";
        userEntity.setPassword(passwordEncoder.encode(defaultPassword));

        // Lưu lại thông tin người dùng
        staffRepository.save(userEntity);

        // Rest of your code...
        System.out.println("Reset password completed.");
    }

    private void forSaveImage(StaffDTO staffDTO, UserEntity entity) {
        File file = new File(imagePath + staffDTO.getUsername() + ".png");
        saveImageStaff(staffDTO.getImage(), file);
        entity.setImage(staffDTO.getUsername() + ".png");
    }

    private void saveImageStaff(MultipartFile image, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(image.getBytes());
            // Thêm dòng in để kiểm tra
            System.out.println("File has been saved successfully");
        } catch (IOException e) {
            e.printStackTrace(); // Thêm dòng này để in ra thông báo lỗi chi tiết
        }
    }

    @Override
    public Optional<UserEntity> getUserWithAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Optional<UserEntity> user = staffRepository.findByUsername(currentUserName);
            return user;
        }
        return null;
    }
}
