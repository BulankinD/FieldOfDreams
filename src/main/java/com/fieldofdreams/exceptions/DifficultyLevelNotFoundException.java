package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 2
public class DifficultyLevelNotFoundException extends Exception
{
    public Integer customResponseCode = 2;

    public DifficultyLevelNotFoundException()
    {
    }

    public DifficultyLevelNotFoundException(String s)
    {
        super(s);
    }
}
