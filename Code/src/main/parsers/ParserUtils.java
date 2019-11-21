package main.parsers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ParserUtils {

    static public ArrayList<String> parseEducationString(String education) {
        ArrayList<String> educationList = new ArrayList<>();
        int beginning = 0;
        int end = 0;
        String parsedEducation;
        boolean multipleEducation = false;
        for (int i = 0; i < education.length(); i++)
            if (education.charAt(i) == ',') {
                multipleEducation = true;
                if (i > 0)
                    end = i - 1;
                else
                    end = 0;
                parsedEducation = education.substring(beginning, end);
                educationList.add(parsedEducation);
                beginning = i + 1;
            }
        if (!multipleEducation)
            educationList.add(education);
        for (int i = 0; i < educationList.size(); i++) {
            if (educationList.get(i).charAt(0) == ' ')
                educationList.set(i, educationList.get(i).substring(1));
        }
        return educationList;
    }

    static public ArrayList<String> removeSlashes(ArrayList<String> dateOfBirthList) {
        ArrayList<String> tempList = new ArrayList<>();
        for (String dateOfBirth : dateOfBirthList) {
            dateOfBirth = dateOfBirth.replace("/", ".");
            tempList.add(dateOfBirth);
        }
        return tempList;
    }

    static public HashMap<String, ArrayList<String>> mapSubjectsWithClasses(ArrayList<String> subjects, ArrayList<String> classes) {
        HashMap<String, ArrayList<String>> tempMap = new HashMap<>();
        for (int i = 0; i < subjects.size(); i++) {
            tempMap.put(subjects.get(i), parseClassesString(classes.get(i)));
        }
        return tempMap;
    }

    static public ArrayList<String> parseClassesString(String classesToParse) {
        ArrayList<String> tempClassList = new ArrayList<>();
        int beginning = 0;
        int end = 0;
        boolean hasMultipleClasses = false;
        for (int i = 0; i < classesToParse.length(); i++) {
            String tempClass;
            if (i == classesToParse.length() - 1) {
                tempClass = classesToParse.substring(beginning);
                tempClass = tempClass.replace(" ", "");
                tempClassList.add(tempClass);
            }
            if (classesToParse.charAt(i) == ',') {
                hasMultipleClasses = true;
                end = i;
                tempClass = classesToParse.substring(beginning, end);
                tempClass = tempClass.replace(" ", "");
                tempClassList.add(tempClass);
                beginning = i + 1;
            }

        }
        if (!hasMultipleClasses)
            tempClassList.add(classesToParse);
        return tempClassList;
    }

    static public ArrayList<Integer> convertStringArrayList(ArrayList<String> sourceList) {
        ArrayList<Integer> tempList = new ArrayList<>();
        for (String string : sourceList)
            tempList.add(Integer.valueOf(string));

        return tempList;
    }


    static public boolean parseBooleanString(String stringToParse) {
        if (stringToParse.equals("Да") || stringToParse.equals("да"))
            return true;
        else
            return false;
    }
    static public String generateLogin(int loginLength) {


        String symbolsToGrab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder generatedLoginBuilder = new StringBuilder(loginLength);

        for (int i = 0; i < loginLength; i++) {
            int index = (int)(symbolsToGrab.length() * Math.random());

            generatedLoginBuilder.append(symbolsToGrab.charAt(index));
        }
        return generatedLoginBuilder.toString();

    }

    static public String generatePassword(int symbolsLength, int numbersLength) {

        String generatedString = generateLogin(symbolsLength);

        String numbersToGrab = "0123456789";

        StringBuilder generatedPasswordBuilder = new StringBuilder(generatedString);

        for (int i = 0; i < numbersLength; i++) {
            int index = (int)(numbersToGrab.length() * Math.random());

            generatedPasswordBuilder.append(numbersToGrab.charAt(index));
        }
        return generatedPasswordBuilder.toString();
    }

    static public <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
