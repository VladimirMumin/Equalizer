package equalizer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EqualizerFourthApp {
    private short[] outputSignal;
    private Filter[] filters;
    private final static char COUNT_OF_THREADS = 4;
    ExecutorService pool;

    public EqualizerFourthApp() {
        pool = Executors.newFixedThreadPool(COUNT_OF_THREADS);
        this.createFilters();
    }

    public void setInputSignal(short[] inputSignal) {
        this.outputSignal = new short[inputSignal.length];
        this.filters[0].settings(inputSignal, FilterInfo.COFFS_NUM_OF_BAND_0);
        this.filters[1].settings(inputSignal, FilterInfo.COFFS_NUM_OF_BAND_1);
        this.filters[2].settings(inputSignal, FilterInfo.COFFS_NUM_OF_BAND_2);
        this.filters[3].settings(inputSignal, FilterInfo.COFFS_NUM_OF_BAND_3);
        this.filters[4].settings(inputSignal, FilterInfo.COFFS_NUM_OF_BAND_4);
        this.filters[5].settings(inputSignal, FilterInfo.COFFS_NUM_OF_BAND_5);

    }

    private void createFilters() {
        this.filters = new Filter[FilterInfo.COUNT_OF_BANDS];
        for (int i = 0; i < FilterInfo.COUNT_OF_BANDS; i++)
            this.filters[i] = new Filter();
    }

    public void equalization() throws InterruptedException, ExecutionException {
        Future<short[]>[] fs = new Future[FilterInfo.COUNT_OF_BANDS];
        for (int i = 0; i < FilterInfo.COUNT_OF_BANDS; i++) {
            fs[i] = pool.submit(this.filters[i]);
        }

        for (int i = 0; i < this.outputSignal.length; i++) {
            this.outputSignal[i] += (short) (
                    fs[0].get()[i] +
                            fs[1].get()[i] +
                            fs[2].get()[i] +
                            fs[3].get()[i] +
                            fs[4].get()[i] +
                            fs[5].get()[i]);
        }
    }

    public short[] getOutputSignal() {
        return this.outputSignal;
    }

    public Filter getFilter(int numberFilter) {
        return this.filters[numberFilter];
    }

    public void close() {
        if (this.pool != null) {
            this.pool.shutdown();
        }
    }
}