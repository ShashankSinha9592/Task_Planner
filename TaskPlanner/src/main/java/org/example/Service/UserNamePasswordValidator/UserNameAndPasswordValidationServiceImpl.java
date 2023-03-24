package org.example.Service.UserNamePasswordValidator;

import org.springframework.stereotype.Service;

@Service
public class UserNameAndPasswordValidationServiceImpl implements UserNameAndPasswordValidationService{

    public boolean validateName(String name){

        char[] arr = name.toCharArray();

        for(int i=0;i<name.length();i++){

            if(Character.isDigit(arr[i])){

                return false;

            }
            else if(checkSymbol(arr[i])){

                return false;

            }
        }

        return true;

    }

    public boolean checkSymbol(char c){

        String str = "@#$%^&*";

        return str.contains(String.valueOf(c));

    }

    public boolean validatePassword(String pass){

        char[] arr = pass.toCharArray();

//        if(pass.length()<6 || pass.length()>12) return false;

        for(int i=0;i<pass.length();i++){

            if(Character.isDigit(arr[i])){

                return (checkSpcl(arr));

                }
            }

        return false;

    }

    public boolean checkSpcl(char[] arr){

        String str = "@#$%^&*";

        for(char i:arr){
            if(str.contains(String.valueOf(i))){
                return checkLower(arr);
            }
        }
        return false;
    }

    public boolean checkLower(char[]arr){

        for(char i:arr){
            if(Character.isLowerCase(i)){
                return checkUpper(arr);
            }
        }
        return false;
    }
    public boolean checkUpper(char[]arr){

        for(char i:arr){
            if(Character.isUpperCase(i)){
                return true;
            }
        }
        return false;
    }
}
