package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 3
public class GameNotFoundException extends Exception
{
    public Integer customResponseCode = 3;

    public GameNotFoundException() {
    }

    public GameNotFoundException(String s) {super(s);}
}
