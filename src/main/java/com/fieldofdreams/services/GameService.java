package com.fieldofdreams.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fieldofdreams.entities.Difficulty;
import com.fieldofdreams.entities.Game;
import com.fieldofdreams.entities.User;
import com.fieldofdreams.exceptions.BadAttemptException;
import com.fieldofdreams.exceptions.GameNotFoundException;
import com.fieldofdreams.exceptions.GameOverException;
import com.fieldofdreams.exceptions.LetterAlreadyOpenedException;
import com.fieldofdreams.exceptions.LetterNotFoundException;
import com.fieldofdreams.repositories.GameRepository;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;
/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Service
public class GameService
{

    @Autowired
    GameRepository gameRepository;

    @Autowired
    SettingsService settingsService;

    public static Random random = new Random();

    public Game StartNewGame(User user, Integer level) {
        Game newGame = new Game();
        Difficulty difficulty = null;
        if (level != null)
            difficulty = settingsService.getByLevel(level);
        // если уровень введненный пользователем не найден, то ставим уровень записанный в пользователе
        if (difficulty == null)
            difficulty = settingsService.getByLevel(user.getDifficultyLevel());
        newGame.setTargetWord(RandomStringUtils.randomAlphabetic(generateRandomLength(difficulty.getMinLength(), difficulty.getMaxLength())));
        newGame.setCurrentState("*".repeat(newGame.getTargetWord().length()));
        newGame.setAttempts((int)(newGame.getTargetWord().length() * difficulty.getAttemptsMultiplier()));
        newGame.setRemainingAttempts(newGame.getAttempts());
        newGame.setUser(user);
        newGame = gameRepository.save(newGame);
        return newGame;
    }

    private int generateRandomLength(Integer min, Integer max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public List<Game> getGames(User user)
    {
        return gameRepository.findAllByUser(user);
    }

    public Game guessLetter(User user, String gameId, String letter)
            throws GameNotFoundException, LetterAlreadyOpenedException, GameOverException, BadAttemptException,
            LetterNotFoundException
    {
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game == null)
        {
            throw new GameNotFoundException("Game with Id " + gameId + " is not found");
        }
        else if (game.getUser().getId().equals(user.getId()))
        {
            if (game.getRemainingAttempts() == 0)
            {
                throw new GameOverException("Game Over! You spent all attempts");
            }
            game.setRemainingAttempts(game.getRemainingAttempts() - 1);
            game = gameRepository.save(game);
            String currentState = game.getCurrentState();
            String targetWord = game.getTargetWord();
            if (letter.length() > 1)
            {
                if (targetWord.equals(letter))
                {
                    game.setCompleted(true);
                    game.setCurrentState(letter);
                    return gameRepository.save(game);
                }
                else
                {
                    throw new BadAttemptException("you didn't guess full word");
                }
            } else {
                if (currentState.contains(letter))
                {
                    throw new LetterAlreadyOpenedException("letter " + letter + " already opened");
                }
                else
                {
                    boolean matched = false;
                    StringBuilder newCurrentState = new StringBuilder();
                    for (int i = 0; i < targetWord.length(); i = i + 1)
                    {
                        if (targetWord.charAt(i) == letter.charAt(0))
                        {
                            matched = true;
                            newCurrentState.append(letter.charAt(0));
                        }
                        else
                        {
                            newCurrentState.append(currentState.charAt(i));
                        }
                    }
                    if (matched)
                    {
                        String newCurrentStateString = newCurrentState.toString();
                        if (newCurrentStateString.equals(targetWord)) {
                            game.setCompleted(true);
                        }
                        game.setCurrentState(newCurrentStateString);
                        return gameRepository.save(game);
                    }
                    else
                    {
                        throw new LetterNotFoundException("letter " + letter + " is not found");
                    }
                }
            }
        }
        else
            throw new GameNotFoundException("Game with Id " + gameId + " is not found");
    }

    public Game getGameById(String gameId)
    {
        return gameRepository.findById(gameId).orElse(null);
    }
}
