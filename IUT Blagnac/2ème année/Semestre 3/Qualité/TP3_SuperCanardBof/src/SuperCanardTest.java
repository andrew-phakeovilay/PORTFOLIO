
import junit.framework.TestSuite;
import junit.framework.TestCase;
import java.io.*;

/**
 * @author bruel
 *
 */
public class SuperCanardTest extends TestCase {
  static int totalAssertions = 0;
  static int bilanAssertions = 0;

  static String programmeATester = "SuperCanard" ;
    
  Process executionProgrammeATester ;
  BufferedReader ecranProgrammeATester ;
  BufferedWriter clavierProgrammeATester ;

  String finDeLigne = System.getProperty("line.separator") ;

  public static void main(String[] args) {
    if ( args.length > 0 ) { programmeATester = args[0] ; }
    System.out.println("Tests du programme : " + programmeATester);
    junit.textui.TestRunner.run(new TestSuite(SuperCanardTest.class));
  }

  @Override
protected void setUp() throws IOException {
          //executionProgrammeATester = Runtime.getRuntime().exec("\"c:\\Program Files\\Java\\jre7\\bin\\java.exe\" -cp U:\\E\\MPA\\projet\\workspace\\G0A1\\bin "+programmeATester);
          //executionProgrammeATester = Runtime.getRuntime().exec("java.exe -cp U:\\E\\MPA\\projet\\workspace\\G0A1\\bin "+programmeATester);
          executionProgrammeATester = Runtime.getRuntime().exec("java -cp .;./bin/ "+programmeATester);
          ecranProgrammeATester = new BufferedReader(new  InputStreamReader( executionProgrammeATester.getInputStream() ));
          clavierProgrammeATester  = new BufferedWriter(new OutputStreamWriter( executionProgrammeATester.getOutputStream() ));
  }

    public void test_ligne1() throws IOException {
        String expected = "Je cancane!";
        assertEquals(expected,expected,ecranProgrammeATester.readLine());
        assertTrue("Seconde ligne'",ecranProgrammeATester.readLine() instanceof String);
        assertTrue("Troisième ligne'",ecranProgrammeATester.readLine()instanceof String);
        assertTrue("Quatrième ligne'",ecranProgrammeATester.readLine()instanceof String);
    }
    public void test_ligne2() throws IOException {
        assertTrue("Première ligne'",ecranProgrammeATester.readLine() instanceof String);
        String expected = "Je nage comme un Canard!";
        assertEquals(expected,expected,ecranProgrammeATester.readLine());
        assertTrue("Troisième ligne'",ecranProgrammeATester.readLine()instanceof String);
        assertTrue("Quatrième ligne'",ecranProgrammeATester.readLine()instanceof String);
    }
    public void test_ligne3() throws IOException {
        assertTrue("Première ligne'",ecranProgrammeATester.readLine() instanceof String);
        assertTrue("Seconde ligne'",ecranProgrammeATester.readLine() instanceof String);
        String expected = "Je suis un Colvert";
        assertEquals(expected,expected,ecranProgrammeATester.readLine());
        assertTrue("Quatrième ligne'",ecranProgrammeATester.readLine()instanceof String);
    }
    public void test_ligne4() throws IOException {
        assertTrue("Première ligne'",ecranProgrammeATester.readLine() instanceof String);
        assertTrue("Seconde ligne'",ecranProgrammeATester.readLine() instanceof String);
        assertTrue("Troisième ligne'",ecranProgrammeATester.readLine()instanceof String);
        String expected = "Je vole avec des ailes!";
        assertEquals(expected,expected,ecranProgrammeATester.readLine());
    }
  }
 

