public class PiOperation implements Runnable {

    private double start;
    private double end;
    private double sum;

    public PiOperation(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public double getSum() {
        return this.sum;
    }

    @Override
    public void run() {
        for(double n = this.start; n < this.end; n ++) {
            this.sum = this.sum + (Math.pow(-1, n) / ((2 * n) + 1));
        }
    }
    
}
