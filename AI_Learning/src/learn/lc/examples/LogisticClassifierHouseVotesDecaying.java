package learn.lc.examples;

import java.io.IOException;

/**
 * Tests the LogisticClassifier on the house-votes-84 data with a decaying
 * learning rate.
 */
public class LogisticClassifierHouseVotesDecaying extends LogisticClassifierTest {

	public static void main(String[] args) throws IOException {
		test("src/learn/lc/examples/house-votes-84.data.num.txt", 10000, 0);
	}

}
