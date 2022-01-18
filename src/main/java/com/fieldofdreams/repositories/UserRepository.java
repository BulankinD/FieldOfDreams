package com.fieldofdreams.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fieldofdreams.entities.User;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Repository
public interface UserRepository extends CrudRepository<User, String>
{
    Optional<User> findByUserName(String userName);
}
