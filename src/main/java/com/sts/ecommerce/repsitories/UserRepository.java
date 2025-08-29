package com.sts.ecommerce.repsitories;

import com.sts.ecommerce.dtos.UserDto;
import com.sts.ecommerce.entities.User;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saif
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT new com.sts.ecommerce.dtos.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDto> findAllUsers();
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.profile LEFT JOIN FETCH u.addresses WHERE u.id = :id")
    User findUserWithProfileAndAddressesByUserId(@Param("id") Long id);

    @Override
    @EntityGraph(attributePaths = {"profile", "addresses"})
    @NonNull
    List<User> findAll();

    @Override
    @EntityGraph(attributePaths = {"profile", "addresses"})
    @NonNull
    List<User> findAll(Sort sort);

}