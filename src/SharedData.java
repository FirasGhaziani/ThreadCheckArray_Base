import java.util.ArrayList;

/**
 * This class represents shared data that can be accessed and modified by multiple threads or processes.
 */
public class SharedData {
    private ArrayList<Integer> array;
    private boolean[] winArray;
    private boolean flag;
    private final int b;

    /**
     * Constructs a new instance of the {@code SharedData} class with the specified parameters.
     *
     * @param array an integer array representing the initial data
     * @param b an integer value that is not modified after initialization
     */
    public SharedData(ArrayList<Integer> array, int b) {
        this.array = array;
        this.b = b;
        // Initialize winArray with the same size as the array
        winArray = new boolean[array.size()];
    }

    /**
     * Returns an integer array representing the current data.
     *
     * @return an integer array representing the current data
     */
    public ArrayList<Integer> getArray() { return array; }

    /**
     * Returns a boolean array representing the current winning status of each element in the data array.
     *
     * @return a boolean array representing the current winning status of each element in the data array
     */
    public boolean[] getWinArray() { return winArray; }

    /**
     * Sets the boolean array representing the winning status of each element in the data array.
     *
     * @param winArray a boolean array representing the winning status of each element in the data array
     */
    public void setWinArray(boolean[] winArray) { this.winArray = winArray; }

    /**
     * Returns an integer value that is not modified after initialization.
     *
     * @return an integer value that is not modified after initialization
     */
    public int getB() { return b; }

    /**
     * Returns the current flag status.
     *
     * @return the current flag status
     */
    public boolean getFlag() { return flag; }

    /**
     * Sets the current flag status.
     *
     * @param flag the new flag status to set
     */
    public void setFlag(boolean flag) { this.flag = flag; }
}
