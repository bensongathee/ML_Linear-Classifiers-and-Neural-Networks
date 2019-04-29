package learn.lc.examples;

import java.io.IOException;
import java.util.List;

import learn.lc.core.DecayingLearningRateSchedule;
import learn.lc.core.Example;
import learn.lc.core.PerceptronClassifier;

/**
 * Program for testing the PerceptronClassifier.
 */
public class PerceptronClassifierTest {

	/**
	 * Read the Data from the file with the given filename, create a
	 * PerceptronClassifier with the appropriate number of inputs for the data,
	 * and train it on the data, printing its accuracy after each step. 
	 */
	public static void test(String filename, int nsteps, double alpha) throws IOException {
		System.out.println("filename: " + filename);
		System.out.println("nsteps: " + nsteps);
		System.out.println("alpha: " + alpha);
		List<Example> examples = Data.readFromFile(filename);
		int ninputs = examples.get(0).inputs.length;
		PerceptronClassifier classifier = new PerceptronClassifier(ninputs) {
			@Override
			public void trainingReport(List<Example> examples, int stepnum, int nsteps) {
				double accuracy = accuracy(examples);
				System.out.println(stepnum + "\t" + accuracy);
			}
		};
		if (alpha > 0) {
			classifier.train(examples, nsteps, alpha);
		} else {
			classifier.train(examples, 100000, new DecayingLearningRateSchedule());
		}
	}
	
	/**
	 * Train a PerceptronClassifier on a file of examples and
	 * print its accuracy after each training step.
	 */
	public static void main(String[] argv) throws IOException {
		if (argv.length < 3) {
			System.out.println("usage: java PerceptronClassifierTest data-filename nsteps alpha");
			System.out.println("       specify alpha=0 to use decaying learning rate schedule");
			System.exit(-1);
		}
		String filename = argv[0];
		int nsteps = Integer.parseInt(argv[1]);
		double alpha = Double.parseDouble(argv[2]);
		test(filename, nsteps, alpha);
	}

}
