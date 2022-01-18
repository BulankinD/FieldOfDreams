package com.fieldofdreams.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fieldofdreams.entities.Game;
import com.fieldofdreams.entities.User;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Repository
public interface GameRepository extends CrudRepository<Game, String>
{
    List<Game> findAllByUser(User user);
}
