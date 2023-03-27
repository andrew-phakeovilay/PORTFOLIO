
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author vferries
 * @note Tests JUnit 4 by vferries 
 */
public class SuperCanardTest2 {
	// On redirige la sortie standard dans un ByteArray qu'on contrôle
	private PrintStream oldStream;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		oldStream = System.out;
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(oldStream);
	}

	@Test
	public void testComportementColvert() {
		Canard c1 = new Colvert();
		c1.cancaner();
		assertEquals("Je cancane!", readLine());
		c1.nager();
		assertEquals("Je nage comme un Canard!", readLine());
		c1.afficher();
		assertEquals("Je suis un Colvert", readLine());
		c1.voler();
		assertEquals("Je vole avec des ailes!", readLine());
	}

	@Test
	public void testComportementCanardEnPlastique() {
		Canard c1 = new CanardEnPlastique();
		c1.cancaner();
		assertEquals("Je cancane!", readLine());
		c1.nager();
		assertEquals("Je nage comme un Canard!", readLine());
		c1.afficher();
		assertEquals("Je suis un CanardEnPlastique!", readLine());
		c1.voler();
		String result = readLine();
		assertTrue("".equals(result) || "Je ne vole pas!".equals(result));
	}

	private String readLine() {
		String content = outContent.toString();
		outContent.reset();
		//return content.split("\n")[0]; //potential Windows problem
		return content.split("\r\n|\n")[0];
	}
}
