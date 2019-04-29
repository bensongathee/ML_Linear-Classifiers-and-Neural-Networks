import java.io.IOException;
import java.util.List;

import learn.nn.core.Example;
import learn.nn.examples.IrisExampleGenerator;
import learn.nn.examples.IrisNN;

public class MainNNIris {
	
	public static void main(String[] argv) throws IOException {
		int epochs = 1000;
		double alpha = 0.10;
		if (argv.length > 0) {
			epochs = Integer.parseInt(argv[0]);
		}
		if (argv.length > 1) {
			alpha = Double.parseDouble(argv[1]);
		}
		List<Example> examples = new IrisExampleGenerator("src/learn/nn/examples/iris.data.txt").examples();
		IrisNN network = new IrisNN();
		System.out.println("Training for " + epochs + " epochs with alpha=" + alpha);
		network.train(examples, epochs, alpha);
		network.dump();
		double accuracy = network.test(examples);
		System.out.println("Overall accuracy=" + accuracy);
		System.out.println();
		System.out.println("Confusion matrix:");
		double[][] matrix = network.confusionMatrix(examples);
		System.out.println("\tPredicted");
		System.out.print("Actual");
		for (int i=0; i < matrix.length; i++) {
			System.out.format("\t%d", i);
		}
		System.out.println();
		for (int i=0; i < matrix.length; i++) {
			System.out.format("%d", i);
			for (int j=0; j < matrix[i].length; j++) {
				System.out.format("\t%.3f", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		int n = examples.size();
		int k = 10;
		System.out.println("k-Fold Cross-Validation: n=" + n + ", k=" + k);
		double acc = network.kFoldCrossValidate(examples, k, epochs, alpha);
		System.out.format("average accuracy: %.3f\n", acc);
		System.out.println();
		System.out.println("Learning Curve testing on all training data");
		System.out.println("EPOCHS\tACCURACY");
		for (epochs=100; epochs <= 3000; epochs+=100) {
			network.train(examples, epochs, alpha);
			accuracy = network.test(examples);
			System.out.format("%d\t%.3f\n",  epochs, accuracy);
//			System.out.println(epochs +"\t"+accuracy);
		}
	}
}
