package com.fieldofdreams.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fieldofdreams.entities.Difficulty;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Repository
public interface DifficultyRepository extends CrudRepository<Difficulty, String>
{
    Optional<Difficulty> findByLevel(Integer level);
}
