import java.util.ArrayList;

public class ThreadCheckArray implements Runnable {
    private boolean flag;
    private boolean[] winArray;
    SharedData sd;
    ArrayList<Integer> array;
    int b;

    /**
     * Constructor for ThreadCheckArray.
     *
     * @param sd the shared data object
     */
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        synchronized (sd) {
            array = new ArrayList<>(sd.getArray());
            b = sd.getB();
        }
        winArray = new boolean[array.size()];
    }

    /**
     * Recursive method to check if the subset sum equals 'b'.
     *
     * @param n the current index
     * @param b the remaining sum
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        if (n == 1) {
            if(b == 0 || b == array.get(n-1)) {
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
            if (b == array.get(n-1)) winArray[n-1] = true;
            return;
        }

        rec(n-1, b - array.get(n-1));
        if (flag) winArray[n-1] = true;
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        rec(n-1, b);
    }

    @Override
    public void run() {
        if (!array.isEmpty())
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.size()-1, b - array.get(array.size() - 1));
            else
                rec(array.size()-1, b);
        if (array.size() == 1)
            if (b == array.get(0) && !flag) {
                winArray[0] = true;
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.size() - 1] = true;
            synchronized (sd) { sd.setWinArray(winArray); }
        }
    }
}
