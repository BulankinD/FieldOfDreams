package com.fieldofdreams.exceptions;

/**
 * @author : Bulankin_D
 * @created : 18.01.2022, вторник
 **/
// customResponseCode = 7
public class UserAlreadyExistsException extends Exception
{
    public Integer customResponseCode = 7;

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String s) {super(s);}
}
