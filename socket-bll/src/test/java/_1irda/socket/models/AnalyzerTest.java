package _1irda.socket.models;

import _1irda.socket.models.db.ListAuth;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnalyzerTest {

    private Analyzer analyzer;

    @Before
    public void before() {
        analyzer = new Analyzer(new ListAuth());
    }

    @Test
    public void checkCommandBadFormat() {
        assertEquals("ERROR unknown command or auth required to ADD, DEL, MOD",
                analyzer.checkCommand("UnknownCommand"));
    }

    @Test
    public void checkCommandTestBad() {
        assertEquals("BAD token:", analyzer.checkCommand("CHK Adri irdA"));
    }

    @Test
    public void checkCommandTestGood() {
        assertEquals("GOOD token:Titi", analyzer.checkCommand("CHK Titi Titi"));
    }

    @Test
    public void checkExtractInfos() {
        assertArrayEquals(new String[] { "CHK", "Toto", "Toto", "Toto" },
                Analyzer.extractInfos("CHK Toto Toto token:Toto"));
    }

    @Test
    public void checkCommandTestAddErrorAuth() {
        String response =  analyzer.checkCommand("ADD Titi Titi");
        assertTrue(response.contains("ERROR unknown command or auth required to ADD, DEL, MOD"));
    }

    @Test
    public void checkCommandTestAddErrorExists() {
        String response =  analyzer.checkCommand("ADD Titi Titi token:Toto");
        assertTrue(response.contains("ERROR cannot be added"));
    }

    @Test
    public void checkCommandTestAddOk() {
        String response =  analyzer.checkCommand("ADD Adri irdA token:Toto");
        assertTrue(response.contains("DONE"));
    }

    @Test
    public void checkCommandTestModErrorAuth() {
        assertEquals("ERROR unknown command or auth required to ADD, DEL, MOD",
                analyzer.checkCommand("MOD New Titi"));
    }

    @Test
    public void checkCommandTestModErrorNotExists() {
        assertEquals("ERROR cannot be updated", analyzer.checkCommand("MOD Adri Adri token:Toto"));
    }

    @Test
    public void checkCommandTestModOk() {
        assertEquals("DONE", analyzer.checkCommand("MOD Titi itiT token:Toto"));
    }
}