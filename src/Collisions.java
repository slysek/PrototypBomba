import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Collisions {

    double slowNeutronChance = 0.85;
    double fastNeutronChance = 0.10;

    long slowNeutrons;
    long fastNeutrons;


    static double zeroNeutronChance = 0.027;
    static double oneNeutronChance = 0.158 + zeroNeutronChance;
    static double twoNeutronChance = 0.339 + oneNeutronChance;
    static double threeNeutronChance = 0.476 + twoNeutronChance;

    double probability;

    static int endTime;

    double numberOfUranAtoms = 235*Math.pow(10, 6);

    double sampleLenght;
    double uranToNeutronsCoeff;

    double a = 1;
    double coff = 1;

    List<Long> ListOfNeutrons = new ArrayList<Long>();
    List<Double> listOfUranNumbers = new ArrayList<Double>();
    List<Long> listOfEnergy = new ArrayList<Long>();

    long totalEnergy = 0;

    long reakcje = 0;

    Collisions(double smpl) {

        this.sampleLenght = smpl;
        NeutronsEnergy neutronsEnergy = new NeutronsEnergy(100000,30000, numberOfUranAtoms, sampleLenght);
        System.out.println("First slow: " + neutronsEnergy.getSlowNeutrons());
        System.out.println("First fast: " + neutronsEnergy.getFastNeutrons());
        System.out.println("---------------------------");

        ListOfNeutrons.add(neutronsEnergy.getSlowNeutrons() + neutronsEnergy.getFastNeutrons());
        listOfUranNumbers.add(numberOfUranAtoms);

        int three = 0;
        int two = 0;
        int one = 0;

        while (numberOfUranAtoms > 0) {
            slowNeutrons = neutronsEnergy.getSlowNeutrons();
            fastNeutrons = neutronsEnergy.getFastNeutrons();

            uranToNeutronsCoeff = numberOfUranAtoms / (slowNeutrons + fastNeutrons);

            if(uranToNeutronsCoeff < 1){
                coff = 0.6;
                if(slowNeutrons + fastNeutrons > numberOfUranAtoms){
                    slowNeutrons = (long) numberOfUranAtoms;
                    System.out.println("ZADUZO" + slowNeutrons);
                }
                neutronsEnergy.setSlowNeutrons((long) (slowNeutrons * coff), numberOfUranAtoms);
                neutronsEnergy.setFastNeutrons(fastNeutrons, numberOfUranAtoms);

                slowNeutrons = neutronsEnergy.getSlowNeutrons();
                fastNeutrons = neutronsEnergy.getFastNeutrons();
            }

            System.out.println("Slow begin: " + slowNeutrons);
            System.out.println("Fast begin: " + fastNeutrons);

            System.out.println("Number of Uran: " + numberOfUranAtoms);


            long slowNeutronsTime = slowNeutrons; // during for loop
            long fastNeutronsTime = fastNeutrons; // during for loop

            long energy = 0;
            for (int i = 0; i < slowNeutrons; i++) {
                double rnd = Math.random();

                if (rnd < slowNeutronChance) {
                    probability = Math.random();
                    if (probability < zeroNeutronChance) {
                        slowNeutronsTime--;
                    } else if (probability < oneNeutronChance && probability < zeroNeutronChance) {
                        one++;
                        continue;
                    } else if (probability < twoNeutronChance && probability < oneNeutronChance) {
                        two++;
                        slowNeutronsTime += 1;
                    } else if (probability < threeNeutronChance && probability < twoNeutronChance) {
                        three++;
                        slowNeutronsTime += 2;
                    }
                    energy += 200;
                    totalEnergy += 200;

                } else {
                    slowNeutronsTime--;
                }
            }

            for (int i = 0; i < fastNeutrons; i++) {
                double rnd2 = Math.random();

                if (rnd2 < fastNeutronChance) {
                    probability = Math.random();
                    if (probability < zeroNeutronChance) {
                        fastNeutronsTime--;
                    } else if (probability < oneNeutronChance && probability < zeroNeutronChance) {
                        continue;
                    } else if (probability < twoNeutronChance && probability < oneNeutronChance) {
                        fastNeutronsTime += 1;
                    } else if (probability < threeNeutronChance && probability < twoNeutronChance) {
                        fastNeutronsTime += 2;
                    }

                    energy += 200;
                    totalEnergy += 200;

                } else {
                    fastNeutronsTime--;
                }
            }


            numberOfUranAtoms -= slowNeutronsTime + fastNeutronsTime;

//            if(numberOfUranAtoms < 0){
//                coff = 0.6;
//                if(slowNeutrons + fastNeutrons > numberOfUranAtoms){
//                    slowNeutronsTime = (long) numberOfUranAtoms;
//                    System.out.println("ZADUZO" + slowNeutronsTime);
//                }
//                neutronsEnergy.setSlowNeutrons((long) (slowNeutronsTime * coff), numberOfUranAtoms);
//                neutronsEnergy.setFastNeutrons(fastNeutronsTime, numberOfUranAtoms);
//            }
//            else{
//                neutronsEnergy.setSlowNeutrons(slowNeutronsTime, numberOfUranAtoms);
//                neutronsEnergy.setFastNeutrons(fastNeutronsTime, numberOfUranAtoms);
//            }

            neutronsEnergy.setSlowNeutrons(slowNeutronsTime, numberOfUranAtoms);
            neutronsEnergy.setFastNeutrons(fastNeutronsTime, numberOfUranAtoms);



            if(numberOfUranAtoms > 0){
                ListOfNeutrons.add(neutronsEnergy.getSlowNeutrons() + neutronsEnergy.getFastNeutrons());
                listOfUranNumbers.add(numberOfUranAtoms);
                listOfEnergy.add(totalEnergy);

                reakcje = one + two + three;

                System.out.println("Slow: " + neutronsEnergy.getSlowNeutrons());
                System.out.println("Fast: " + neutronsEnergy.getFastNeutrons());
                System.out.println("Number of Uran: " + numberOfUranAtoms);
                System.out.println("One: " + one);
                System.out.println("Two: " + two);
                System.out.println("Three: " + three);
                System.out.println("Energy: " + energy);
                System.out.println("Size energy: " + listOfEnergy.size());
                System.out.println("---------------------------");
            }

            if(fastNeutrons + slowNeutrons == 0){
                break;
            }

        }
    }

    List<Long> getListOfNeutrons() {
        return ListOfNeutrons;
    }

    List<Double> getListOfUranNumbers() {
        return listOfUranNumbers;
    }

    Long getNeutrons(int i){
        return ListOfNeutrons.get(i);
    }

    Double getUran(int i) {
        return listOfUranNumbers.get(i);
    }

    Long getEnergy(int i) {
        return listOfEnergy.get(i);
    }

    int getEnergySize(){
        return listOfEnergy.size();
    }

    int getListSize(){
        return listOfUranNumbers.size();
    }

    long getTotalEnergy(){
        return totalEnergy;
    }

    long getReakcje(){
        return reakcje;
    }
}