public class ThreadsAplication {
    public static void main(String[] args) {
        double piSimple, piThreadMedia;
        double[] piArray = new double[4];
        
        piSimple = calculatePi();
        System.out.println("valor de pi = " + piSimple);

        /*

        for (int i = 0; i < 4; i ++){
            double piThread = calculatePiThread();
            System.out.println("valor de pi (threads) = " + piThread);
            piArray[i] = piThread;
        }

        piThreadMedia = calculatePiThreadMedian(piArray);
        System.out.println("valor medio de pi (threads) = " + piThreadMedia);

        */
        
        double piThread = calculatePiThread();
        System.out.println("valor de pi (threads) = " + piThread);
        
    }

    public static double calculatePi() {
        long initialTime = System.currentTimeMillis();

        PiOperation operation = new PiOperation(0, 100000000);
        operation.run();
        double sum = operation.getSum();
        double pi;
        pi = 4 * sum;

        long finalTime = System.currentTimeMillis();
        System.out.format("%.3f ms %n", (finalTime - initialTime) / 1000d);
        return pi;
    }

    

    public static double calculatePiThread() {
        long initialTime = System.currentTimeMillis();

        double[] values = new double[10];

        PiOperation operation = new PiOperation(0, 9999999);
        PiOperation operation1 = new PiOperation(10000000, 19999999);
        PiOperation operation2 = new PiOperation(20000000, 29999999);
        PiOperation operation3 = new PiOperation(30000000, 39999999);
        PiOperation operation4 = new PiOperation(40000000, 49999999);
        PiOperation operation5 = new PiOperation(50000000, 59999999);
        PiOperation operation6 = new PiOperation(60000000, 69999999);
        PiOperation operation7 = new PiOperation(70000000, 79999999);
        PiOperation operation8 = new PiOperation(80000000, 89999999);
        PiOperation operation9 = new PiOperation(90000000, 100000000);

        new Thread(operation).start();
        new Thread(operation1).start();
        new Thread(operation2).start();
        new Thread(operation3).start();
        new Thread(operation4).start();
        new Thread(operation5).start();
        new Thread(operation6).start();
        new Thread(operation7).start();
        new Thread(operation8).start();
        new Thread(operation9).start();

        values[0] = operation.getSum();
        values[1] = operation1.getSum();
        values[2] = operation2.getSum();
        values[3] = operation3.getSum();
        values[4] = operation4.getSum();
        values[5] = operation5.getSum();
        values[6] = operation6.getSum();
        values[7] = operation7.getSum();
        values[8] = operation8.getSum();
        values[9] = operation9.getSum();

        double pi = sumPiThreadValues(values);

        long finalTime = System.currentTimeMillis();
        System.out.format("%.3f ms (threads)%n", (finalTime - initialTime) / 1000d);
        return pi;
    }

    public static double calculatePiThreadMedian(double[] values){
        double sum = 0;
        for(int i = 0; i < values.length; i ++){
            sum = sum + values[i];
        }
        double median = sum / values.length;
        return median;
    }


    public static double sumPiThreadValues(double[] values) {
        double total = 0;
        for(int i = 0; i < values.length; i++){
            total = total + values[i];
        }
        return 4 * total;
    }

    
    
}