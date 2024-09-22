package utils;

import exceptions.CepInvalidException;

public class CepUtil {

    private CepUtil(){}

    public static boolean isValidCep(String numberCep) throws CepInvalidException {

        if (numberCep.length() != 8) throw new CepInvalidException("Invalid size");
        if (!numberCep.matches("\\d{8}")) throw new CepInvalidException("Just numbers");
        return true;
    }

}
