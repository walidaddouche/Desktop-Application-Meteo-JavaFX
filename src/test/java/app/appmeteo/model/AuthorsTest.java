package app.appmeteo.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorsTest {

    private Authors authors = new Authors(Arrays.asList("ADDOUCHE Walid , Amir Redjem, Tassadit Kettouche ,Dekkar Lamia , Adnane AFIFI"));

    @Test
    public void testToString() {
        String expected = "ADDOUCHE Walid , Amir Redjem, Tassadit Kettouche ,Dekkar Lamia , Adnane AFIFI" ;
        assertEquals(expected, authors.toString());
    }
}

