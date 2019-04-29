import java.io.IOException;
import java.util.Date;
import java.util.List;

import learn.nn.core.Example;
import learn.nn.core.NeuralNetwork;
import learn.nn.core.NeuralNetworkListener;
import learn.nn.examples.MNIST;
import learn.nn.examples.MNISTNN;

public class MainNNMNIST {
	
	public static void main(String[] argv) throws IOException {
		int epochs = 100;
		double alpha = 0.10;
		if (argv.length > 0) {
			epochs = Integer.parseInt(argv[0]);
		}
		if (argv.length > 1) {
			alpha = Double.parseDouble(argv[1]);
		}
		MNISTNN network = new MNISTNN();
		System.out.println("MNIST: reading training data...");
		String DATADIR = "src/learn/nn/examples";
		List<Example> trainingSet = MNIST.read(DATADIR+"/train-images-idx3-ubyte", DATADIR+"/train-labels-idx1-ubyte");
		System.out.println("MNIST: reading testing data...");
		List<Example> testingSet = MNIST.read(DATADIR+"/t10k-images-idx3-ubyte", DATADIR+"/t10k-labels-idx1-ubyte");
		System.out.println("MNIST: training on " + trainingSet.size() + " examples for " + epochs + " epochs, alpha=" + alpha);
		System.out.println("MNIST: testing on " + testingSet.size() + " examples");
		System.out.println("EPOCH\tACC\tTIMEms\tHHMMSS");;
		network.addListener(new NeuralNetworkListener() {
			protected long startTime;
			public void trainingEpochStarted(NeuralNetwork network, int epoch) {
				startTime = new Date().getTime();
			}
			public boolean trainingEpochCompleted(NeuralNetwork network, int epoch) {
				//network.dump();
				long now = new Date().getTime(); // Stop the clock before testing accuracy
				double accuracy = network.test(testingSet);
				long elapsed = now - startTime;
				long s = elapsed / 1000;
				long h = s / (60*60);
				s -= h*60*60; 
				long m = s / 60;
				s -= m*60;
				System.out.format("%d\t%.3f\t%d\t%02d:%02d:%02d\n", epoch, accuracy, elapsed, h, m, s);
				return true;
			}
		});
		network.train(trainingSet, epochs, alpha);
	}
}
