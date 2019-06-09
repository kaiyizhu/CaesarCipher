/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caesarcode;

/**
 *
 * @author Kaiyi
 */

/*  
Encode:
Ask user for input: String and a NUMBER
 Create a method that gets a numeric value from each characters of the string
 Get a character, and seperate it with upper and lowercase letters
 Uppercase = 65-90
 Lowercase = 97-122
 Anything else = unchanged
 UPPERCASE: Create a method that adds the number to the numeric value of the characters
 shift = NUMBER%26 to get the number that is needed to add to the char
 If the char + amountNeedTobeAdded exceeds 90, 64 + shift
 Return value
 LOWERCASE: Same thing as UPPERCASE method but the number exceed is 122. 
 If the char + amountNeedTobeAdded exceeds 122, 96 + shift
BreakCode:
Create a method that gets a String input
Get each character and shift it by 1
Create a for looop: Every time a word is shifted, increase the amount to be shifted by 1.
Print out all the different combinations (26 in total, including original message)
Use an array to input common words
If a common word is found in one of the decoded Strings
    Store it into a variable, and print it out again at the end.
 */
public class caesarCipher
{
    static String storeWords[] = new String[26];
    static String storeOneWord;
    static int count = 0;

    public static void main(String[] args)
    {
        //breakCode(""); or encode(""+n); 
    }

    /**
     * Shifts every letter of a String that's given. Returns an encoded, shifted
     * version of the String.
     *
     * @param text The String you want to shift
     * @param shift How many numbers you want to shift the letters by
     * @return returns the new String
     */
    public static String encode(String text, int shift)
    {
        //initialize variables
        char getChar;
        storeOneWord = "";
        for (int i = 0; i < text.length(); i++)
        {
            //takes in a character of the word, from the first letter
            getChar = text.charAt(i);
            //if getChar is from A-Z, goto method uppercaseEncode
            if (((int) getChar >= 65) && ((int) getChar <= 90))
            {
                uppercaseEncode(getChar, shift);
                //uppercaseEncode returns the shifted character, adds the character to storeOneWord
                storeOneWord += ((char) uppercaseEncode(getChar, shift));
            } //if getChar is from a-z, goto method lowercaseCode;
            else if (((int) getChar >= 97) && ((int) getChar <= 122))
            {
                lowercaseEncode(getChar, shift);
                //lowercaseEncode returns the shifted character, adds the character to storeOneWord
                storeOneWord += ((char) lowercaseEncode(getChar, shift));
            } //if the character is not a letter, then just adds the character to storeOneWord
            else
            {
                storeOneWord += getChar;
            }
        }
        //
        storeWords[count] = storeOneWord;
        System.out.print(storeWords[count]);
        count++;
        return storeOneWord;
    }

    /**
     * Decodes a given String. Method will find out different combinations of
     * the String. Returns a String that is possibly the decoded message, using
     * common English words.
     *
     * @param text Takes in the String, supposedly an encoded message
     * @return Returns a possible decoded String
     */
    public static String breakCode(String text)
    {
        //Initialize variables
        String decodedText = "";
        //Stores a list of common words in the array
        String[] commonWords =
        {
            " the ",
            " it ",
            "hello",
            " and ",
            " is ",
            " am ",
            " can ",
            " to ",
            " too ",
            " an ",
            "happy",
            "you",
            " of ",
            " I ",
            " RD ",
            " be ",
            " which ",
            " that ",
            " when ",
            " if ",
            " who ",
            " what ",
            " this ",
            "abcdefghijklmnopqrstuvwxyz"
        };
        //prints 26 possible Strings from the given encoded text
        for (int c = 0; c < 26; c++)
        {
            encode(text, c);
            System.out.print("\n");
        }
        //Checks if any words in the possible Strings are from the common words list, to find a highly possible decoded message
        for (int j = 0; j < commonWords.length; j++)
        {
            for (int c = 0; c < storeWords.length; c++)
            {
                int checkForWord = storeWords[c].indexOf(commonWords[j]);
                //if there is a match, then store the word in decodedText, and print it out
                if (checkForWord >= 0)
                {
                    decodedText = storeWords[c];
                    System.out.println(storeWords[c]);
                }
            }
        }
        //returns the highly possible decoded word
        return decodedText;
    }

    public static int uppercaseEncode(char getChar, int shift)
    {
        int num = (int) getChar;
        int round = 0;
        //if shift is greater or equal to 26, than modulus it by 26 to shrink the number
        if (shift >= 26)
        {
            round = shift % 26;
        } //same thing as the above, but for negative numbers
        else if (shift <= -26)
        {
            round = shift % -26;
        } //for any char that stays within range of A-Z, shift the letter normally
        else
        {
            round = shift;
        }
        int convertChar = 0;
        //if the number added to the char normally (A-Z) without exceeding 90 or lower than 65
        if ((getChar + round) <= 90 && ((getChar + round) >= 65))
        {
            convertChar = getChar + round;
        } //if round is positive
        else if (round > 0)
        {
            if ((getChar + round) > 90)
            {
                //stores the shifted character in convertChar
                convertChar = ((getChar - 64) + round) % 26 + 64;
            }
        } //if round is negative
        else if (round < 0)
        {
            //stores the shifted character in convertChar
            convertChar = 91 + ((getChar - 65) + round);
        }
        return convertChar;
    }

    public static int lowercaseEncode(char getChar, int shift)
    {
        int round = 0;
        //if shift is greater or equal to 26, than modulus it by 26 to shrink the number
        if (shift >= 26)
        {
            round = shift % 26;
        } //same thing as the above, but for negative numbers
        else if (shift <= -26)
        {
            round = shift % -26;
        } //for any char that stays within range of a-z
        else
        {
            //for any char that stays within the range of a-z, shift the letter normally
            round = shift;
        }
        int convertChar = 0;
        //if the number added to the char normally (a-z) without exceeding 122 or lower than 96
        if ((getChar + round) <= 122 && ((getChar + round) >= 97))
        {
            convertChar = getChar + round;
        } //if round is positive
        else if (round > 0)
        {
            if ((getChar + round) > 122)
            {
                //stores the shifted letter in convertChar
                convertChar = ((getChar - 96) + round) % 26 + 96;
            }
        } //if round is negative
        else if (round < 0)
        {
            //stores the character in convertChar
            convertChar = 123 + ((getChar - 97) + round);
        }
        return convertChar;
    }

    //resets count, for jUnit testing purposes only
    public static void resetCount()
    {
        count = 0;
    }
}
