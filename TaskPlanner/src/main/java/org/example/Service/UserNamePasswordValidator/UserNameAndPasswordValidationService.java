package org.example.Service.UserNamePasswordValidator;

public interface UserNameAndPasswordValidationService {

    public boolean validateName(String name);

    public boolean checkSymbol(char c);

    public boolean validatePassword(String pass);

    public boolean checkSpcl(char[] arr);

    public boolean checkLower(char[]arr);


    public boolean checkUpper(char[]arr);

}
