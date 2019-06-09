/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import caesarcode.caesarCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 335550372
 */
public class caesarCipherTest
{

    caesarCipher clcaesarCipher = new caesarCipher();

    @Test
    public void testEncode()
    {
        clcaesarCipher.resetCount();
        //tests if encoding it by zero returns the same string
        assertEquals("Nihao", clcaesarCipher.encode("Nihao", 0));
        //tests if it does not shift non-letters, and tests if letters are shifted still
        assertEquals("!@#$%^&*z1234567890", clcaesarCipher.encode("!@#$%^&*a1234567890", 25));
        //tests if it can go above Zz to Aa
        assertEquals("Aa", clcaesarCipher.encode("Zz", 1));
        //tests if it can go below Aa to Zz
        assertEquals("Zz", clcaesarCipher.encode("Aa", -1));
        //tests if it can shift by negative number and tests for case sensitivity
        assertEquals("ZzAaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYy", clcaesarCipher.encode("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz", -1));
        //tests if it can handle large negative numbers
        assertEquals("Nihao are you", clcaesarCipher.encode("Zutma mdq kag", -1000));
        //tests if it can handle large positive numbers
        assertEquals("Nihao are you", clcaesarCipher.encode("Bwvoc ofs mci", 1000));
    }

    @Test
    public void testBreakCode()
    {
        //tests if it can detect a common word and print out the line containing the word
        assertEquals("happy birthday", clcaesarCipher.breakCode("mfuud gnwymifd"));
        clcaesarCipher.resetCount();
        //tests if the text stays the same
        assertEquals("hello man", clcaesarCipher.breakCode("hello man"));
        clcaesarCipher.resetCount();
        //tests if there is no returned String if there are no common words
        assertEquals("", clcaesarCipher.breakCode("asdasd"));
        clcaesarCipher.resetCount();
        //tests if it can handle non-letter characters in the String
        assertEquals("!hello-man!", clcaesarCipher.breakCode("!fcjjm-kyl!"));
    }

    public caesarCipherTest()
    {

    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
