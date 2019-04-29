import java.io.IOException;
import java.util.List;

import learn.nn.core.Example;
import learn.nn.examples.XorExampleGenerator;
import learn.nn.examples.XorNN;

public class MainNNXorNN {
	
	public static void main(String[] argv) throws IOException {
		int epochs = 100;
		double alpha = 0.10;
		if (argv.length > 0) {
			epochs = Integer.parseInt(argv[0]);
		}
		if (argv.length > 1) {
			alpha = Double.parseDouble(argv[1]);
		}
		int nexamples = 1000;
		XorExampleGenerator generator = new XorExampleGenerator();
		XorNN network = new XorNN();
		System.out.println("XorNN: Training on " + nexamples + " examples for " + epochs + " epochs, alpha=" + alpha);
		List<Example> trainingSet = generator.examples(nexamples);
		network.train(trainingSet, epochs, alpha);
		network.dump();
		List<Example> testingSet = generator.examples(nexamples);
		double accuracy = network.test(testingSet);
		System.out.println("XorNN: Testing on " + nexamples + " examples: accuracy=" + accuracy);
		System.out.println();
		System.out.println("Learning Curve testing on all training data");
		System.out.println("EPOCHS\tACCURACY");
		for (epochs=100; epochs <= 3000; epochs+=100) {
			List<Example> examples = generator.examples(nexamples);
			network.train(examples, epochs, alpha);
			accuracy = network.test(examples);
			System.out.format("%d\t%.3f\n",  epochs, accuracy);
		}
	}
}
