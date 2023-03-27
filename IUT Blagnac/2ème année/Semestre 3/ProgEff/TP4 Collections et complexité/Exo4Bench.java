
import stone.Grinder;
import stone.GrinderBench;
import stone.Stone;

public class Exo4Bench {

        public static void main(String[] args) {
                Stone s = Stone.makeHugeStone();
                Grinder g = new MyGrinder();
                GrinderBench.benchmark(g, 4, s);
        }

}