package com.fieldofdreams.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties
@Entity(name = "games")
@Data
public class Game
{
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    private String Id;

    @ManyToOne
    private User user;

    private String targetWord;

    private String currentState;

    private Integer attempts;

    private Integer RemainingAttempts = attempts;

    private Boolean completed = false;

    public String getId()
    {
        return Id;
    }

    public User getUser()
    {
        return user;
    }

    public String getTargetWord()
    {
        return targetWord;
    }

    public String getCurrentState()
    {
        return currentState;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public void setTargetWord(String targetWord)
    {
        this.targetWord = targetWord;
    }

    public void setCurrentState(String currentState)
    {
        this.currentState = currentState;
    }

    @Override
    public String toString()
    {
        return "Game{" + "id='" + Id + '\'' + ", user=" + user + ", targetWord='" + targetWord + '\'' +
                ", currentState='" + currentState + '\'' + '}';
    }
}
