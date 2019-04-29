package learn.lc.examples;

import java.io.IOException;
import java.util.List;

import learn.lc.core.DecayingLearningRateSchedule;
import learn.lc.core.Example;
import learn.lc.core.LogisticClassifier;

/**
 * Program for testing the LogisticClassifier.
 */
public class LogisticClassifierTest {
	
	/**
	 * Read the Data from the file with the given filename, create a
	 * LogisticClassifier with the appropriate number of inputs for the data,
	 * and train it on the data, printing its (1.0-squared-error/sample) after
	 * each step. 
	 */
	public static void test(String filename, int nsteps, double alpha) throws IOException {
		System.out.println("filename: " + filename);
		System.out.println("nsteps: " + nsteps);
		System.out.println("alpha: " + alpha);
		
		List<Example> examples = Data.readFromFile(filename);
		int ninputs = examples.get(0).inputs.length; 
		LogisticClassifier classifier = new LogisticClassifier(ninputs) {
			public void trainingReport(List<Example> examples, int stepnum, int nsteps) {
				double oneMinusError = 1.0-squaredErrorPerSample(examples);
				System.out.println(stepnum + "\t" + oneMinusError);
			}
		};
		if (alpha > 0) {
			classifier.train(examples, nsteps, alpha);
		} else {
			classifier.train(examples, 100000, new DecayingLearningRateSchedule());
		}
	}
	
	/**
	 * Train a LogisticClassifier on a file of examples and
	 * print its (1.0-squared-error/sample) after each training step.
	 */
	public static void main(String[] argv) throws IOException {
		if (argv.length < 3) {
			System.out.println("usage: java LogisticClassifierTest data-filename nsteps alpha");
			System.out.println("       specify alpha=0 to use decaying learning rate schedule (AIMA p725)");
			System.exit(-1);
		}
		String filename = argv[0];
		int nsteps = Integer.parseInt(argv[1]);
		double alpha = Double.parseDouble(argv[2]);
		test(filename, nsteps, alpha);
	}

}
