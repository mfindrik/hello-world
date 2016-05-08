package org.terrier.matching.models;

public class BM25L extends WeightingModel {
	
    private static final long serialVersionUID = 1L;
    /** The constant k_1.*/
    private double k_1 = 1.2d;

    /** The constant k_3.*/
    private double k_3 = 8d;

    /** The parameter b.*/
    private double b = 0.75d;

    /** The shift parameter s.*/
    private double s = 0.5d;
    
    /** A default constructor.*/
    public BM25L() {
        super();
        k_1 = Double.parseDouble(System.getProperty("k_1", "1.2d"));
        b = Double.parseDouble(System.getProperty("b", "0.75d"));
        k_3 = Double.parseDouble(System.getProperty("k_3", "8d"));
        s = Double.parseDouble(System.getProperty("s", "0.5d"));
    }
    /**
     * Returns the name of the model.
     * @return the name of the model
     */
    @Override
    public final String getInfo() {
        return "BM25L.k_1"+k_1+".b"+b+".k_3"+k_3 + ".s" + s;
    }
    
    @Override
    public double score(double tf, double docLength) {
    	double K = 0;
		double c = tf / ((1 - b) + b * docLength / averageDocumentLength);
		if ( c > 0 ) {
			double a = c + s;
			K = ((k_1 + 1)*a)/(k_1 + a);
		}

			
		return WeightingModelLibrary.log((numberOfDocuments - documentFrequency + 0.5d) / (documentFrequency + 0.5d)) *
				K *
				((k_3+1)*keyFrequency/(k_3+keyFrequency));
    }
}
