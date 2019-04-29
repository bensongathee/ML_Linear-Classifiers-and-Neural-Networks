package learn.lc.examples;

import java.io.IOException;

/**
 * Tests the LogisticClassifier on the clean earthquake data.
 */
public class LogisticClassifierEarthquakeClean extends LogisticClassifierTest {

	public static void main(String[] args) throws IOException {
		test("src/learn/lc/examples/earthquake-clean.data.txt", 10000, 0.95);
	}

}
