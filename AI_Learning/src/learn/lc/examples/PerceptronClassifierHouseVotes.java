package learn.lc.examples;

import java.io.IOException;

/**
 * Tests the PerceptronClassifier on the house-votes-84 data.
 */
public class PerceptronClassifierHouseVotes extends PerceptronClassifierTest {

	public static void main(String[] args) throws IOException {
		test("src/learn/lc/examples/house-votes-84.data.num.txt", 10000, 0.95);
	}

}
