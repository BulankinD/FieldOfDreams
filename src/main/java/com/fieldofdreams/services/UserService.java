package com.fieldofdreams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.fieldofdreams.dto.UserDto;
import com.fieldofdreams.entities.User;
import com.fieldofdreams.exceptions.DifficultyLevelNotFoundException;
import com.fieldofdreams.exceptions.UserAlreadyExistsException;
import com.fieldofdreams.repositories.UserRepository;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingsService settingsService;

    public User createNewUser(UserDto userDto) throws UserAlreadyExistsException {
        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("User " + userDto.toString() + " already exists!");
        } else {
            User user = new User(userDto);
            user.setDifficultyLevel(1);
            userRepository.save(user);
            return user;
        }
    }

    @Nullable
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Nullable
    public User getUserByName(String name) {
        return userRepository.findByUserName(name).orElse(null);
    }

    public User changeDifficulty(User user, Integer level) throws DifficultyLevelNotFoundException {
        if (settingsService.getByLevel(level) != null) {
            user.setDifficultyLevel(level);
            return userRepository.save(user);
        } else throw new DifficultyLevelNotFoundException("Difficulty with level " + level + " not found!");
    }
}
