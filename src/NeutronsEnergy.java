
import java.lang.Math;

public class NeutronsEnergy {
//    List<Integer> slowNeutrons = new ArrayList<Integer>(); // 0.025eV E < 1keV
//    List<Integer> fastNeutrons = new ArrayList<Integer>(); 1keV < E < 1MeV

    long slowNeutrons = 0;
    long fastNeutrons = 0;

    double slowNeutronsInSample;
    double fastNeutronsInSample;

    static double uranN235 = 4.89 * Math.pow(10, 22);
    static double sigmaSlow = 580 * Math.pow(10, -28);
    static double sigmaFast = (Math.random() * 90 + 10) * Math.pow(10, -28);
    static double lSlow = 1/uranN235/sigmaSlow;
    static double lFast = 1/uranN235/sigmaFast;
    static double sampleLenght = 1000;
    double x;

    double uranToNeutronsCoeff = 1;

    NeutronsEnergy(int slow, int fast, double noOfAtoms) {
        this.slowNeutrons = slow;
        this.fastNeutrons = fast;
        calculateNeutrons(noOfAtoms);
    }

    void calculateNeutrons(double noOfAtoms){

        uranToNeutronsCoeff = noOfAtoms / (slowNeutrons + fastNeutrons);

        slowNeutronsInSample = 0;
        fastNeutronsInSample = 0;

        double coff = 1.0;

        if(uranToNeutronsCoeff < 1){ coff = uranToNeutronsCoeff;}

        System.out.println(coff);

        for (int i = 0; i < slowNeutrons; i++) {
            x = -coff*lSlow*Math.log(Math.random());

            if(x < sampleLenght){
                slowNeutronsInSample++;
            }
        }
        for (int i = 0; i < fastNeutrons; i++) {
            x = -coff*lFast*Math.log(Math.random());

            if(x < sampleLenght){
                fastNeutronsInSample++;
            }
        }

        slowNeutronsInSample *= coff;
        fastNeutronsInSample *= coff;
    }

    double getSlowNeutrons(){
        return slowNeutronsInSample;
    }

    double getFastNeutrons(){
        return fastNeutronsInSample;
    }

    void setSlowNeutrons(long neutrons, double atoms){
        slowNeutrons = neutrons;
        calculateNeutrons(atoms);
    }

    void setFastNeutrons(long neutrons, double atoms){
        fastNeutrons = neutrons;
        calculateNeutrons(atoms);
    }

}