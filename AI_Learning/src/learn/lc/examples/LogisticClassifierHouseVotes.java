package learn.lc.examples;

import java.io.IOException;

/**
 * Tests the LogisticClassifier on the house-votes-84 data.
 */
public class LogisticClassifierHouseVotes extends LogisticClassifierTest {

	public static void main(String[] args) throws IOException {
		test("src/learn/lc/examples/house-votes-84.data.num.txt", 10000, 0.95);
	}

}
