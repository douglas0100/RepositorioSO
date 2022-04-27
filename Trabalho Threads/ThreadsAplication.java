public class ThreadsAplication {

    public static final int NUMBER_OF_THREADS = 8;

    public static void main(String[] args) throws InterruptedException {
        double piSimple;
        
        piSimple = calculatePi();
        System.out.println("valor de pi = " + piSimple);
        
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

    public static double calculatePiThread() throws InterruptedException {
        long initialTime = System.currentTimeMillis();

        double[] values = new double[NUMBER_OF_THREADS];

        double start = 0, end = 9999999;

        PiOperation[] operations = new PiOperation[NUMBER_OF_THREADS];

        for(int i = 0; i < NUMBER_OF_THREADS; i++) {
            operations[i] = new PiOperation(start, end);
            start = start + 10000000;
            end = end + 100000000;
        }


        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; i ++) {
            threads[i] = new Thread(operations[i]);
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i].start();
        }

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            values[i] = operations[i].getSum();
        }

        double pi = sumPiThreadValues(values);

        long finalTime = System.currentTimeMillis();
        System.out.format("%.3f ms (threads)%n", (finalTime - initialTime) / 1000d);
        return pi;
    }

    public static double sumPiThreadValues(double[] values) {
        double total = 0;
        for(int i = 0; i < values.length; i++){
            System.out.format("thread %d  = %.23f %n", i, values[i]);
            total = total + values[i];
        }
        return 4 * total;
    }

    
    
}