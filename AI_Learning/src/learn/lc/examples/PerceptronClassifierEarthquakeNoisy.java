package learn.lc.examples;

import java.io.IOException;

/**
 * Tests the PerceptronClassifier on the noisy earthquake data.
 */
public class PerceptronClassifierEarthquakeNoisy extends PerceptronClassifierTest {

	public static void main(String[] args) throws IOException {
		test("src/learn/lc/examples/earthquake-noisy.data.txt", 10000, 0.95);
	}

}
