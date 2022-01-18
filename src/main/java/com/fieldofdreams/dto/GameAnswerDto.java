package com.fieldofdreams.dto;

import com.fieldofdreams.entities.Game;

import lombok.Builder;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
@Builder
public class GameAnswerDto
{
    public Game game;

    public String message;

    public Integer customResponseCode;
}
