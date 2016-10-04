/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yanti
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import javax.swing.JTextArea;
import weka.classifiers.RandomizableIteratedSingleClassifierEnhancer;
import weka.core.AdditionalMeasureProducer;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;
import weka.core.Randomizable;
import weka.core.RevisionUtils;
import weka.core.SerializationHelper;
import weka.core.TechnicalInformation;
import weka.core.TechnicalInformationHandler;
import weka.core.Utils;
import weka.core.WeightedInstancesHandler;

/**
 *
 * @author sartikahasibuan
 */
public class BaggingImprove extends RandomizableIteratedSingleClassifierEnhancer
        implements WeightedInstancesHandler, AdditionalMeasureProducer,
        TechnicalInformationHandler {

    /**
     * for serialization
     */
    private static final long serialVersionUID = -5178288489778728847L;

    /**
     * The size of each bag sample, as a percentage of the training size
     */
    protected int m_BagSizePercent = 100;

    /**
     * Whether to calculate the out of bag error
     */
    protected boolean m_CalcOutOfBag = false;

    /**
     * The out of bag error that has been calculated
     */
    protected double m_OutOfBagError;

    /**
     * Constructor.
     */
    public BaggingImprove() {

        m_Classifier = new weka.classifiers.trees.REPTree();
    }

    /**
     * Returns a string describing classifier
     *
     * @return a description suitable for displaying in the
     * explorer/experimenter gui
     */
    public String globalInfo() {

        return "Class for bagging a classifier to reduce variance. Can do classification "
                + "and regression depending on the base learner. \n\n"
                + "For more information, see\n\n"
                + getTechnicalInformation().toString();
    }

    /**
     * Returns an instance of a TechnicalInformation object, containing detailed
     * information about the technical background of this class, e.g., paper
     * reference or book this class is based on.
     *
     * @return the technical information about this class
     */
    public TechnicalInformation getTechnicalInformation() {
        TechnicalInformation result;

        result = new TechnicalInformation(TechnicalInformation.Type.ARTICLE);
        result.setValue(TechnicalInformation.Field.AUTHOR, "Leo Breiman");
        result.setValue(TechnicalInformation.Field.YEAR, "1996");
        result.setValue(TechnicalInformation.Field.TITLE, "Bagging predictors");
        result.setValue(TechnicalInformation.Field.JOURNAL, "Machine Learning");
        result.setValue(TechnicalInformation.Field.VOLUME, "24");
        result.setValue(TechnicalInformation.Field.NUMBER, "2");
        result.setValue(TechnicalInformation.Field.PAGES, "123-140");

        return result;
    }

    /**
     * String describing default classifier.
     *
     * @return the default classifier classname
     */
    @Override
    protected String defaultClassifierString() {

        return "weka.classifiers.trees.REPTree";
    }

    /**
     * Returns an enumeration describing the available options.
     *
     * @return an enumeration of all the available options.
     */
    @Override
    public Enumeration listOptions() {

        Vector newVector = new Vector(2);

        newVector.addElement(new Option(
                "\tSize of each bag, as a percentage of the\n"
                + "\ttraining set size. (default 100)", "P", 1, "-P"));
        newVector.addElement(new Option("\tCalculate the out of bag error.", "O",
                0, "-O"));

        Enumeration enu = super.listOptions();
        while (enu.hasMoreElements()) {
            newVector.addElement(enu.nextElement());
        }
        return newVector.elements();
    }

    /**
     * Parses a given list of options.
     * <p/>
     *
     * <!-- options-start -->
     * Valid options are:
     * <p/>
     *
     * <pre>
     * -P
     *  Size of each bag, as a percentage of the
     *  training set size. (default 100)
     * </pre>
     *
     * <pre>
     * -O
     *  Calculate the out of bag error.
     * </pre>
     *
     * <pre>
     * -S &lt;num&gt;
     *  Random number seed.
     *  (default 1)
     * </pre>
     *
     * <pre>
     * -I &lt;num&gt;
     *  Number of iterations.
     *  (default 10)
     * </pre>
     *
     * <pre>
     * -D
     *  If set, classifier is run in debug mode and
     *  may output additional info to the console
     * </pre>
     *
     * <pre>
     * -W
     *  Full name of base classifier.
     *  (default: weka.classifiers.trees.REPTree)
     * </pre>
     *
     * <pre>
     * Options specific to classifier weka.classifiers.trees.REPTree:
     * </pre>
     *
     * <pre>
     * -M &lt;minimum number of instances&gt;
     *  Set minimum number of instances per leaf (default 2).
     * </pre>
     *
     * <pre>
     * -V &lt;minimum variance for split&gt;
     *  Set minimum numeric class variance proportion
     *  of train variance for split (default 1e-3).
     * </pre>
     *
     * <pre>
     * -N &lt;number of folds&gt;
     *  Number of folds for reduced error pruning (default 3).
     * </pre>
     *
     * <pre>
     * -S &lt;seed&gt;
     *  Seed for random data shuffling (default 1).
     * </pre>
     *
     * <pre>
     * -P
     *  No pruning.
     * </pre>
     *
     * <pre>
     * -L
     *  Maximum tree depth (default -1, no maximum)
     * </pre>
     *
     * <!-- options-end -->
     *
     * Options after -- are passed to the designated classifier.
     * <p>
     *
     * @param options the list of options as an array of strings
     * @throws Exception if an option is not supported
     */
    @Override
    public void setOptions(String[] options) throws Exception {

        String bagSize = Utils.getOption('P', options);
        if (bagSize.length() != 0) {
            setBagSizePercent(Integer.parseInt(bagSize));
        } else {
            setBagSizePercent(100);
        }

        setCalcOutOfBag(Utils.getFlag('O', options));

        super.setOptions(options);
    }

    /**
     * Gets the current settings of the Classifier.
     *
     * @return an array of strings suitable for passing to setOptions
     */
    @Override
    public String[] getOptions() {

        String[] superOptions = super.getOptions();
        String[] options = new String[superOptions.length + 3];

        int current = 0;
        options[current++] = "-P";
        options[current++] = "" + getBagSizePercent();

        if (getCalcOutOfBag()) {
            options[current++] = "-O";
        }

        System.arraycopy(superOptions, 0, options, current, superOptions.length);

        current += superOptions.length;
        while (current < options.length) {
            options[current++] = "";
        }
        return options;
    }

    /**
     * Returns the tip text for this property
     *
     * @return tip text for this property suitable for displaying in the
     * explorer/experimenter gui
     */
    public String bagSizePercentTipText() {
        return "Size of each bag, as a percentage of the training set size.";
    }

    /**
     * Gets the size of each bag, as a percentage of the training set size.
     *
     * @return the bag size, as a percentage.
     */
    public int getBagSizePercent() {

        return m_BagSizePercent;
    }

    /**
     * Sets the size of each bag, as a percentage of the training set size.
     *
     * @param newBagSizePercent the bag size, as a percentage.
     */
    public void setBagSizePercent(int newBagSizePercent) {

        m_BagSizePercent = newBagSizePercent;
    }

    /**
     * Returns the tip text for this property
     *
     * @return tip text for this property suitable for displaying in the
     * explorer/experimenter gui
     */
    public String calcOutOfBagTipText() {
        return "Whether the out-of-bag error is calculated.";
    }

    /**
     * Set whether the out of bag error is calculated.
     *
     * @param calcOutOfBag whether to calculate the out of bag error
     */
    public void setCalcOutOfBag(boolean calcOutOfBag) {

        m_CalcOutOfBag = calcOutOfBag;
    }

    /**
     * Get whether the out of bag error is calculated.
     *
     * @return whether the out of bag error is calculated
     */
    public boolean getCalcOutOfBag() {

        return m_CalcOutOfBag;
    }

    /**
     * Gets the out of bag error that was calculated as the classifier was
     * built.
     *
     * @return the out of bag error
     */
    public double measureOutOfBagError() {

        return m_OutOfBagError;
    }

    /**
     * Returns an enumeration of the additional measure names.
     *
     * @return an enumeration of the measure names
     */
    public Enumeration enumerateMeasures() {
        Vector newVector = new Vector(1);
        newVector.addElement("measureOutOfBagError");
        return newVector.elements();
    }

    /**
     * Returns the value of the named measure.
     *
     * @param additionalMeasureName the name of the measure to query for its
     * value
     * @return the value of the named measure
     * @throws IllegalArgumentException if the named measure is not supported
     */
    public double getMeasure(String additionalMeasureName) {

        if (additionalMeasureName.equalsIgnoreCase("measureOutOfBagError")) {
            return measureOutOfBagError();
        } else {
            throw new IllegalArgumentException(additionalMeasureName
                    + " not supported (Bagging)");
        }
    }

    /**
     * Bagging method.
     *
     * @param data the training data to be used for generating the bagged
     * classifier.
     * @throws Exception if the classifier could not be built successfully
     */
    public static void writePrediction(Instances bag) throws IOException {

        System.out.println("Finish to write each bag data");
    }

    /**
     * Bagging method.
     *
     * @param data the training data to be used for generating the bagged
     * classifier.
     * @throws Exception if the classifier could not be built successfully
     */
    public void buildClassifier(Instances data) throws Exception {

        // can classifier handle the data?
        getCapabilities().testWithFail(data);

        // remove instances with missing class
        data = new Instances(data);
        //data.deleteWithMissingClass();

        super.buildClassifier(data);

        if (m_CalcOutOfBag && (m_BagSizePercent != 100)) {
            throw new IllegalArgumentException("Bag size needs to be 100% if "
                    + "out-of-bag error is to be calculated!");
        }
        //+
        System.out.println("Classifier length" + m_Classifiers.length);

        int bagSize = data.numInstances() * m_BagSizePercent / 100;
        //+
        System.out.println("Bag Size " + bagSize);

        Random random = new Random(m_Seed);

        boolean[][] inBag = null;
        if (m_CalcOutOfBag) {
            inBag = new boolean[m_Classifiers.length][];
        }

        //+
        //inisialisasi nama penamaan model
        BufferedWriter writer = new BufferedWriter(new FileWriter("Bootstrap.txt"));

        for (int j = 0; j < m_Classifiers.length; j++) {

            Instances bagData = null;

            // create the in-bag dataset
            if (m_CalcOutOfBag) {
                inBag[j] = new boolean[data.numInstances()];

                //System.out.println("Inbag1 " + inBag[0][1]);
                //bagData = resampleWithWeights(data, random, inBag[j]);
                bagData = data.resampleWithWeights(random, inBag[j]);
                //System.out.println("num after resample " + bagData.numInstances());
                //+
//                for (int k = 0; k < bagData.numInstances(); k++) {
//                    System.out.println("Bag Data after resample [calc out bag]" + bagData.instance(k));
//                }

            } else {
                //+
                System.out.println("Not m_Calc out of bag");
                System.out.println("Please configure code inside!");

                bagData = data.resampleWithWeights(random);
                if (bagSize < data.numInstances()) {
                    bagData.randomize(random);
                    Instances newBagData = new Instances(bagData, 0, bagSize);
                    bagData = newBagData;
                }
            }

            if (m_Classifier instanceof Randomizable) {
                //+
                System.out.println("Randomizable");
                ((Randomizable) m_Classifiers[j]).setSeed(random.nextInt());
            }

            //write bootstrap into file
            writer.write("Bootstrap " + j);
            writer.newLine();
            writer.write(bagData.toString());
            writer.newLine();
            
            System.out.println("Berhasil menyimpan bootstrap ke file ");

            System.out.println("Bootstrap " + j+1);
//            textarea.append("\nBootsrap " + (j + 1));
            //System.out.println("num instance kedua kali "+bagData.numInstances());

           for (int b = 1; b < bagData.numInstances(); b++) {
                System.out.println("" + bagData.instance(b));
//                textarea.append("\n" + bagData.instance(b));
           }
//            //+

            // build the classifier
            m_Classifiers[j].buildClassifier(bagData);
//            //+
//            
//            SerializationHelper serialization = new SerializationHelper();
//            serialization.write("KnnData"+model+".model", m_Classifiers[j]);
//            System.out.println("Finish write into model");
//            model++;
        }

        writer.flush();
        writer.close();
        // calc OOB error?
        if (getCalcOutOfBag()) {
            double outOfBagCount = 0.0;
            double errorSum = 0.0;
            boolean numeric = data.classAttribute().isNumeric();

            for (int i = 0; i < data.numInstances(); i++) {
                double vote;
                double[] votes;
                if (numeric) {
                    votes = new double[1];
                } else {
                    votes = new double[data.numClasses()];
                }

                // determine predictions for instance
                int voteCount = 0;
                for (int j = 0; j < m_Classifiers.length; j++) {
                    if (inBag[j][i]) {
                        continue;
                    }
                    voteCount++;
                    // double pred = m_Classifiers[j].classifyInstance(data.instance(i));
                    if (numeric) {
                        // votes[0] += pred;
                        votes[0] = m_Classifiers[j].classifyInstance(data.instance(i));
                    } else {
                        // votes[(int) pred]++;
                        double[] newProbs = m_Classifiers[j].distributionForInstance(data.instance(i));
                        //-
//                        for(double a : newProbs)
//                        {
//                            System.out.println("Double new probs %.f "+a);
//                        }
                        // average the probability estimates
                        for (int k = 0; k < newProbs.length; k++) {
                            votes[k] += newProbs[k];
                        }

                    }
                }
                System.out.println("Vote count %d" + voteCount);

                // "vote"
                if (numeric) {
                    vote = votes[0];
                    if (voteCount > 0) {
                        vote /= voteCount; // average
                    }
                } else {
                    if (Utils.eq(Utils.sum(votes), 0)) {
                    } else {
                        Utils.normalize(votes);

                    }
                    vote = Utils.maxIndex(votes); // predicted class
                    //-
                    System.out.println("Vote " + vote);

                }

                // error for instance
                outOfBagCount += data.instance(i).weight();
                if (numeric) {
                    errorSum += StrictMath.abs(vote - data.instance(i).classValue()) * data.instance(i).weight();
                } else if (vote != data.instance(i).classValue()) {
                    //+
                    System.out.println("Vote terakhir" + data.instance(i).classValue());
                    errorSum += data.instance(i).weight();
                }
            }

            m_OutOfBagError = errorSum / outOfBagCount;
        } else {
            m_OutOfBagError = 0;
        }
    }

    public Instances getEachBag(Instances bag) {
        return bag;
    }

    /**
     * Calculates the class membership probabilities for the given test
     * instance.
     *
     * @param instance the instance to be classified
     * @return predicted class probability distribution
     * @throws Exception if distribution can't be computed successfully
     */
    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {

        double[] sums = new double[instance.numClasses()], newProbs;
        //-
        //System.out.println("\nDistribution For Instance\n");
        for (int i = 0; i < m_NumIterations; i++) {
            if (instance.classAttribute().isNumeric() == true) {
                //System.out.println(m_Classifiers[i].classifyInstance(instance));
                sums[0] += m_Classifiers[i].classifyInstance(instance);
            } else {
                //System.out.println(m_Classifiers[i].distributionForInstance(instance));
                newProbs = m_Classifiers[i].distributionForInstance(instance);
                //-
//                for (int j = 0; j < newProbs.length; j++) {
//                    sums[j] += newProbs[j];
//                    System.out.println("Sums "+sums[j]);
//                }
                //+

            }
        }
        if (instance.classAttribute().isNumeric() == true) {
            sums[0] /= m_NumIterations;
            return sums;
        } else if (Utils.eq(Utils.sum(sums), 0)) {
            return sums;
        } else {
            Utils.normalize(sums);
            return sums;
        }
    }

    /**
     * Returns description of the bagged classifier.
     *
     * @return description of the bagged classifier as a string
     */
    @Override
    public String toString() {

        if (m_Classifiers == null) {
            return "Bagging: No model built yet.";
        }
        StringBuffer text = new StringBuffer();
        text.append("All the base classifiers: \n\n");
        for (int i = 0; i < m_Classifiers.length; i++) {
            text.append(m_Classifiers[i].toString() + "\n\n");
        }

        if (m_CalcOutOfBag) {
            text.append("Out of bag error: "
                    + Utils.doubleToString(m_OutOfBagError, 4) + "\n\n");
        }

        return text.toString();
    }

    /**
     * Returns the revision string.
     *
     * @return the revision
     */
    @Override
    public String getRevision() {
        return RevisionUtils.extract("$Revision: 9370 $");
    }

    /**
     * Main method for testing this class.
     *
     * @param argv the options
     */
    public static void main(String[] argv) {
        runClassifier(new BaggingImprove(), argv);
    }
}