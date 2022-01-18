package com.fieldofdreams.controllers.api.v1;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fieldofdreams.dto.GameAnswerDto;
import com.fieldofdreams.dto.UserDto;
import com.fieldofdreams.entities.Game;
import com.fieldofdreams.entities.User;
import com.fieldofdreams.exceptions.BadAttemptException;
import com.fieldofdreams.exceptions.DifficultyLevelNotFoundException;
import com.fieldofdreams.exceptions.GameNotFoundException;
import com.fieldofdreams.exceptions.GameOverException;
import com.fieldofdreams.exceptions.LetterAlreadyOpenedException;
import com.fieldofdreams.exceptions.LetterNotFoundException;
import com.fieldofdreams.exceptions.UserAlreadyExistsException;
import com.fieldofdreams.services.GameService;
import com.fieldofdreams.services.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @ApiOperation(value = "Create user", notes = "You can create new user")
    @PostMapping
    public User createUser(HttpServletResponse response, @RequestBody UserDto userDto) {
       try {
           User user = userService.createNewUser(userDto);
           return user;
       } catch (UserAlreadyExistsException e) {
           log.warn(e.getMessage());
           response.setStatus(409);
           return null;
       }
    }

    @ApiOperation(value = "Get all users", notes = "You can get all users")
    @GetMapping
    public List<User> GetUsers(HttpServletResponse response) {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Change difficulty", notes = "You can change difficulty of user")
    @PostMapping("/{id}/difficulty")
    public User changeDifficulty(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String id, @RequestParam Integer level) {
        User user = userService.getUserById(id);
        if (user != null) {
            try
            {
                return userService.changeDifficulty(user, level);
            } catch (DifficultyLevelNotFoundException e) {
                log.info(e.getMessage());
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            }
        } else
            response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

    @ApiOperation(value = "Start game", notes = "You can start new game")
    @PostMapping("/{id}/games")
    public Game startNewGame(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String id, @RequestParam(required = false) Integer level) {
        User user = userService.getUserById(id);
        if (user != null) {
            Game newGame = gameService.StartNewGame(user, level);
            return newGame;
        } else
            response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

    @ApiOperation(value = "Get Users Games", notes = "You can get all Users Games")
    @GetMapping("/{id}/games")
    public List<Game> getUsersGames(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return gameService.getGames(user);
        } else
            response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

    @ApiOperation(value = "Guess Letter", notes = "You can Guess Letter")
    @PostMapping("/{id}/games/{gameId}")
    public GameAnswerDto guessLetter(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String id, @PathVariable String gameId, @RequestParam String letter) {
        User user = userService.getUserById(id);
        if (user != null) {
            try
            {
                return GameAnswerDto.builder()
                        .game(gameService.guessLetter(user, gameId, letter))
                        .message("Congratulates!")
                        .customResponseCode(8)
                        .build();
            } catch (GameNotFoundException e) {
                log.info(e.getMessage());
                response.setStatus(HttpStatus.NOT_FOUND.value());
            } catch (BadAttemptException e) {
                log.info(e.getMessage());
                return GameAnswerDto.builder()
                        .game(gameService.getGameById(gameId))
                        .message(e.getMessage())
                        .customResponseCode(e.customResponseCode)
                        .build();
            } catch (GameOverException e) {
                log.info(e.getMessage());
                return GameAnswerDto.builder()
                        .game(gameService.getGameById(gameId))
                        .message(e.getMessage())
                        .customResponseCode(e.customResponseCode)
                        .build();
            } catch (LetterAlreadyOpenedException e) {
                log.info(e.getMessage());
                return GameAnswerDto.builder()
                        .game(gameService.getGameById(gameId))
                        .message(e.getMessage())
                        .customResponseCode(e.customResponseCode)
                        .build();
            } catch (LetterNotFoundException e) {
                log.info(e.getMessage());
                return GameAnswerDto.builder()
                        .game(gameService.getGameById(gameId))
                        .message(e.getMessage())
                        .customResponseCode(e.customResponseCode)
                        .build();
            }
        } else
            response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

    @ApiOperation(value = "Get Game", notes = "You can get users game by Id")
    @GetMapping("/{id}/games/{gameId}")
    public Game getUsersGame(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String id, @PathVariable String gameId) {
        User user = userService.getUserById(id);
        if (user != null) {
            Game game = gameService.getGameById(gameId);
            if (game.getUser().getId().equals(id))
                return game;
            else
                response.setStatus(HttpStatus.NOT_FOUND.value());
        } else
            response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
}
