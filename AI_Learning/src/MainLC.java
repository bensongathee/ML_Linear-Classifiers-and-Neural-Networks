import java.io.IOException;
import java.util.List;

import learn.lc.core.DecayingLearningRateSchedule;
import learn.lc.core.Example;
import learn.lc.core.LogisticClassifier;
import learn.lc.core.PerceptronClassifier;
import learn.lc.display.ClassifierDisplay;
import learn.lc.examples.Data;

public class MainLC {
	
	public static void main(String [] args) throws IOException {		
		String prefix = "src/learn/lc/examples/";
		
		String filename = prefix+args[1];
		int nsteps = Integer.parseInt(args[2]);
		System.out.println("filename: " + filename);
		System.out.println("nsteps: " + nsteps);
		
		
		List<Example> examples = Data.readFromFile(filename);
		int ninputs = examples.get(0).inputs.length; 
		
		if(args[0].equals("pC")) { 
			ClassifierDisplay display = new ClassifierDisplay("PerceptronClassifier: " + filename);
			PerceptronClassifier classifier = new PerceptronClassifier(ninputs) {
				@Override
				public void trainingReport(List<Example> examples, int stepnum, int nsteps) {
					double accuracy = accuracy(examples);
					System.out.println(stepnum + "\t" + accuracy);
					display.addPoint(stepnum/(double)nsteps, accuracy);
				}
			};
			if(args.length>3)
				classifier.train(examples, nsteps, Double.parseDouble(args[3]));
			else
				classifier.train(examples, nsteps, new DecayingLearningRateSchedule());
		}else {
			ClassifierDisplay display = new ClassifierDisplay("LogisticClassifier: " + filename);
			LogisticClassifier classifier = new LogisticClassifier(ninputs) {
				@Override
				public void trainingReport(List<Example> examples, int stepnum, int nsteps) {
					double oneMinusError = 1.0-squaredErrorPerSample(examples);
					System.out.println(stepnum + "\t" + oneMinusError);
					display.addPoint(stepnum/(double)nsteps, oneMinusError);
				}
			};
			if(args.length>3)
				classifier.train(examples, nsteps, Double.parseDouble(args[3]));
			else
				classifier.train(examples, nsteps, new DecayingLearningRateSchedule());
		}
	}
}