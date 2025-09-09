package com.sts.ecommerce.controllers;

import com.sts.ecommerce.dtos.UpdateUserDto;
import com.sts.ecommerce.dtos.UserDto;
import com.sts.ecommerce.dtos.UserProfileAddressDto;
import com.sts.ecommerce.dtos.UserRegisterDto;
import com.sts.ecommerce.entities.User;
import com.sts.ecommerce.mappers.UserMapper;
import com.sts.ecommerce.mappers.UserProfileAddressMapper;
import com.sts.ecommerce.repsitories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;


/**
 * @author saif
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserProfileAddressMapper userProfileAddressMapper = UserProfileAddressMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        var users = userRepository.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileAddressDto> getUserProfileAddressByUserId(@PathVariable Long id) {
        User user = userRepository.findUserWithProfileAndAddressesById(id);
        UserProfileAddressDto dto = userProfileAddressMapper.userToUserProfileAddressDto(user);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfileAddressDto>> getAllUsers(
            @RequestParam  (required = false, defaultValue = "", name = "sort") String sortBy
    ) {
        if(!Set.of("name", "email").contains(sortBy)) sortBy = "name";

        List<UserProfileAddressDto> users = userRepository.findAll(Sort.by(sortBy))
                .stream().map(userProfileAddressMapper::userToUserProfileAddressDto).toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserRegisterDto userRegisterDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        User user = userMapper.userRegisterDtoToUser(userRegisterDto);
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = userMapper.userToUserDto(savedUser);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUserDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(
//            @PathVariable Long id,
//            @RequestBody UpdateUserDto updateUserDto
//    ) {
//        User user = userRepository.findById(id)
//                .orElse(null);
//
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        userMapper.updateUserFromUpdateUserDto(updateUserDto, user);
//
//        User updatedUser = userRepository.save(user);
//
//        UserDto updatedUserDto = userMapper.userToUserDto(updatedUser);
//        return ResponseEntity.ok(updatedUserDto);
//    }

    @PutMapping({"/{id}", "/{id}/name", "/{id}/email"})
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserDto updateUserDto
    ) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Only update fields that are provided (not null)
        if (updateUserDto.getName() != null) {
            user.setName(updateUserDto.getName());
        }

        if (updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }

        User updatedUser = userRepository.save(user);

        UserDto updatedUserDto = userMapper.userToUserDto(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<UserDto> updateUserEmail(
            @PathVariable Long id,
            @RequestBody String email
    ) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setEmail(email);

        User updatedUser = userRepository.save(user);

        UserDto updatedUserDto = userMapper.userToUserDto(updatedUser);
        return ResponseEntity.ok(updatedUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}