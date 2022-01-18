package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 5
public class LetterAlreadyOpenedException extends Exception
{
    public Integer customResponseCode = 5;

    public LetterAlreadyOpenedException() {
    }

    public LetterAlreadyOpenedException(String s) {super(s);}
}
