
import java.lang.Math;

public class NeutronsEnergy {
//    List<Integer> slowNeutrons = new ArrayList<Integer>(); // 0.025eV E < 1keV
//    List<Integer> fastNeutrons = new ArrayList<Integer>(); 1keV < E < 1MeV

    long slowNeutrons = 0;
    long fastNeutrons = 0;

    long slowNeutronsInSample;
    long fastNeutronsInSample;

    static double uranN235 = 4.89 * Math.pow(10, 22);
    static double sigmaSlow = 580 * Math.pow(10, -28);
    static double sigmaFast = (Math.random() * 90 + 10) * Math.pow(10, -28);
    static double lSlow = 1/uranN235/sigmaSlow;
    static double lFast = 1/uranN235/sigmaFast;
    double sampleLenght;
    double x;

    double uranToNeutronsCoeff = 1;

    NeutronsEnergy(int slow, int fast, double noOfAtoms, double smpl) {
        this.slowNeutrons = slow;
        this.fastNeutrons = fast;
        this.sampleLenght = smpl;
        calculateNeutrons(noOfAtoms);

        System.out.println(sampleLenght);
    }

    void calculateNeutrons(double noOfAtoms){

        uranToNeutronsCoeff = noOfAtoms / (slowNeutrons + fastNeutrons);

        slowNeutronsInSample = 0;
        fastNeutronsInSample = 0;

        double coff = 1.0;


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

        slowNeutronsInSample = (long) (slowNeutronsInSample * coff);
        fastNeutronsInSample = (long) (fastNeutronsInSample * coff);


    }

    long getSlowNeutrons(){
        return slowNeutronsInSample;
    }

    long getFastNeutrons(){
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

    void setSampleLenght(double smpl){
        sampleLenght = smpl;
    }

}