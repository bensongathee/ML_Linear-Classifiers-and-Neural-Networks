package learn.lc.examples;

import java.io.IOException;

/**
 * Tests the LogisticClassifier on the noisy earthquake data with a decaying
 * learning schedule.
 */
public class LogisticClassifierEarthquakeNoisyDecaying extends LogisticClassifierTest {

	public static void main(String[] args) throws IOException {
		test("src/learn/lc/examples/earthquake-noisy.data.txt", 10000, 0);
	}

}
