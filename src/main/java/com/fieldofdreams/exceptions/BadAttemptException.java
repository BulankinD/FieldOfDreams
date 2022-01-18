package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 1
public class BadAttemptException extends Exception
{
    public Integer customResponseCode = 1;

    public BadAttemptException() {
    }

    public BadAttemptException(String s) {super(s);}
}
