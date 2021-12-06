package _1irda.socket.models.db;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListAuthTest {

    private ListAuth listAuth;

    @Before
    public void before() {
        listAuth = new ListAuth();
    }

    @Test
    public void testCreateOk() {
        assertNotNull(listAuth.create("", ""));
    }

    @Test
    public void testCreateNotOk() {
        assertNotNull(listAuth.create("Titi", "Titi"));
    }

    @Test
    public void testUpdateOk() {
        assertTrue(listAuth.update("Titi", "irdA"));
    }

    @Test
    public void testUpdateNotOk() {
        assertFalse(listAuth.update("", ""));
    }

    @Test
    public void testDeleteOk() {
        assertTrue(listAuth.delete("Toto", "Toto"));
    }

    @Test
    public void testDeleteNotOk() {
        assertFalse(listAuth.delete("", ""));
    }

    @Test
    public void testTestLoginPasswordOk() {
        assertTrue(listAuth.testLoginPassword("Toto", "Toto"));
    }

    @Test
    public void testLoginPasswordNotOk() {
        assertFalse(listAuth.testLoginPassword("", ""));
    }

    @Test
    public void testLoginOk() {
        assertTrue(listAuth.testLogin("Tata"));
    }

    @Test
    public void testLoginNotOk() {
        assertFalse(listAuth.testLogin(""));
    }
}