package com.pdurasek.demo.util;

import com.pdurasek.demo.dto.OCR.DataDTO;
import com.pdurasek.demo.model.enums.CheckDigit;

public class OcrParseUtil {

    /**
     * Method that parses,extracts and validates the first row of a MRZ identity card
     *
     * @param firstRow the upper row of the back side of MRZ identity card
     * @param dataDTO object to store parsed data
     * @return data object with populated fields
     */
    public static DataDTO analyzeFirstRow(String firstRow, DataDTO dataDTO) {
        dataDTO.setOfficialTravelDocument(firstRow.substring(0, 2));
        dataDTO.setIssuingState(firstRow.substring(2, 5));
        dataDTO.setDocumentNumber(firstRow.substring(5, 14));
        dataDTO.setCheckDocumentCodeDigit(Character.getNumericValue(firstRow.charAt(14)));
        dataDTO.setOptional1(firstRow.substring(15));

        return dataDTO;
    }

    /**
     * Method that parses,extracts and validates the second row of a MRZ identity card
     *
     * @param secondRow the middle row of the back side of MRZ identity card
     * @param dataDTO object to store parsed data
     * @return data object with populated fields
     */
    public static DataDTO analyzeSecondRow(String secondRow, DataDTO dataDTO) {
        dataDTO.setBirthDate(secondRow.substring(0, 6));
        dataDTO.setCheckBirthDateDigit(Character.getNumericValue(secondRow.charAt(6)));
        dataDTO.setSex(String.valueOf(secondRow.charAt(6)));
        dataDTO.setExpiryDate(secondRow.substring(8, 14));
        dataDTO.setCheckExpiryDateDigit(Character.getNumericValue(secondRow.charAt(14)));
        dataDTO.setNationality(secondRow.substring(15, 18));
        dataDTO.setOptional2(secondRow.substring(18, secondRow.length() - 1));
        dataDTO.setCheckOverallDigit(Character.getNumericValue(secondRow.charAt(29)));

        return dataDTO;
    }

    /**
     * Method that parses,extracts and validates the third row of a MRZ identity card
     *
     * @param thirdRow the bottom row of the back side of MRZ identity card
     * @param dataDTO object to store parsed data
     * @return data object with populated fields
     */
    public static DataDTO analyzeThirdRow(String thirdRow, DataDTO dataDTO) {
        int firstDelimiter = thirdRow.indexOf("<");
        dataDTO.setPrimaryIdentifier(thirdRow.substring(0, firstDelimiter));
        dataDTO.setSecondaryIdentifier(thirdRow.substring(firstDelimiter + 1).replace("<", " ").trim());

        return dataDTO;
    }

    /**
     *  Method that calculates the digit using the formula provided in the Microblink docs
     *
     * @param targetCharacters string that requires validation
     * @return calculated check digit
     */
    public static int checkDigit(String targetCharacters) {
        int[] weights = {7, 3, 1};

        int sum = 0;
        for (int i = 0; i < targetCharacters.length(); i++) {
            String currentChar = String.valueOf(targetCharacters.charAt(i));
            int currentWeight = weights[i % 3];

            if (currentChar.matches("\\d+")) {
                sum += Integer.parseInt(currentChar) * currentWeight;
            } else {
                if (currentChar.equals("<")) {
                    continue;
                }
                sum += CheckDigit.valueOf(currentChar).value * currentWeight;
            }
        }

        return sum % 10;
    }

    /**
     * Method to determine if all fields of the card were read properly
     *
     * @param dataDTO object with all fields populated
     * @return boolean that confirms user validity
     */
    public static boolean validateUser(DataDTO dataDTO) {
        boolean documentNumberValid = checkDigit(dataDTO.getDocumentNumber()) == dataDTO.getCheckDocumentCodeDigit();
        boolean birthDateValid = checkDigit(dataDTO.getBirthDate()) == dataDTO.getCheckBirthDateDigit();
        boolean expiryDateValid = checkDigit(dataDTO.getExpiryDate()) == dataDTO.getCheckExpiryDateDigit();
        boolean overallValid =
                checkDigit(dataDTO.getDocumentNumber()
                        + dataDTO.getCheckDocumentCodeDigit()
                        + dataDTO.getOptional1()
                        + dataDTO.getBirthDate()
                        + dataDTO.getCheckBirthDateDigit()
                        + dataDTO.getExpiryDate()
                        + dataDTO.getCheckExpiryDateDigit()
                        + dataDTO.getOptional2()) == dataDTO.getCheckOverallDigit();

        return documentNumberValid && birthDateValid && expiryDateValid && overallValid;
    }
}
