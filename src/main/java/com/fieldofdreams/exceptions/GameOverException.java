package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 4
public class GameOverException extends Exception
{
    public Integer customResponseCode = 4;

    public GameOverException() {
    }

    public GameOverException(String s) {super(s);}
}
