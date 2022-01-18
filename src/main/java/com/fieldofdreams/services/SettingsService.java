package com.fieldofdreams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fieldofdreams.entities.Difficulty;
import com.fieldofdreams.repositories.DifficultyRepository;

import io.swagger.models.auth.In;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Service
public class SettingsService {

    DifficultyRepository difficultyRepository;

    @Autowired
    public SettingsService(DifficultyRepository difficultyRepository) {
        this.difficultyRepository = difficultyRepository;
        Difficulty difficulty1 = Difficulty.builder()
                .minLength(5).maxLength(10).attemptsMultiplier(2.0).build();
        this.difficultyRepository.save(difficulty1);
        Difficulty difficulty2 = Difficulty.builder()
                .minLength(10).maxLength(15).attemptsMultiplier(1.7).build();
        this.difficultyRepository.save(difficulty2);
        Difficulty difficulty3 = Difficulty.builder()
                .minLength(15).maxLength(20).attemptsMultiplier(1.5).build();
        this.difficultyRepository.save(difficulty3);
    }

    List<Difficulty> getDifficulties() {
        return (List<Difficulty>)difficultyRepository.findAll();
    }

    void editDifficulty(Difficulty difficulty) {
        difficultyRepository.save(difficulty);
    }

    public Difficulty getByLevel(Integer level) {
        return difficultyRepository.findByLevel(level).orElse(null);
    }

    public List<Difficulty> getAllDifficulties() {
        return (List<Difficulty>)difficultyRepository.findAll();
    }
}