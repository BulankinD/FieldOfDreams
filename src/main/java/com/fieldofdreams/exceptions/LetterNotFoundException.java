package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 6
public class LetterNotFoundException extends Exception
{
    public Integer customResponseCode = 6;

    public LetterNotFoundException() {
    }

    public LetterNotFoundException(String s) {super(s);}
}
