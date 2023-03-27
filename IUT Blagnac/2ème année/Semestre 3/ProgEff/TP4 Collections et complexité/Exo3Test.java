import stone.AbstractGrinderTest;
import stone.Grinder;

public class Exo3Test extends AbstractGrinderTest {

    @Override
    protected Grinder makeGrinder() {
            return new MyGrinder();
    }

}