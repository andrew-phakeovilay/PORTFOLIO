package tp2.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tp2.StrLen;

public class BenchFrequent {

	public static final int N = 100 * 1000;
	public static final int FACTOR = 10;
	public static final int REPEAT = 3;

	public static List<String> makeList(int n) {
		List<String> res = new ArrayList<String>(n);
		for (int i = 1; i <= n; i++) {
			res.add(Integer.toString(i));
		}
		Collections.shuffle(res);
		return res;
	}

	public static long bench(int n) {
		List<String> li = makeList(n);
		System.out.println("Benchmark with " + n + " elements...");
		System.out.flush();
		long t0 = System.currentTimeMillis();
		int lg = StrLen.mostFrequent(li);
		long duration = System.currentTimeMillis() - t0;
		System.out.println("Duration = " + duration + "ms; result = " + lg);
		return duration;
	}

	/**
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		long normalDuration = 0;
		long factoredDuration = 0;
		for (int i = 1; i <= REPEAT; i++) {
			/* One series of benchmarks. */
			normalDuration += bench(N);
			factoredDuration += bench(FACTOR * N);
		}
		
		/* Computing and printing the statistics. */
		double normalAverage = normalDuration / REPEAT;
		double factoredAverage = factoredDuration / REPEAT;
		double ratio = factoredAverage / normalAverage;
		System.out
				.println("x" + FACTOR + " length -> x" + ratio + " time");
	}

}
