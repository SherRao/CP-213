package cp213;

/**
 * Stores a data value and an occurrence count for that value.
 *
 * @author David Brown
 * @version 2021-02-18
 */
public class CountedData<T extends Comparable<T>> {

    // Attributes.
    private T data = null; // data
    private int count = 0; // data count

    /**
     * Constructor for key version of object. (data count defaults to 0)
     *
     * @param data A data.
     */
    public CountedData(final T data) {
	this.data = data;
    }

    /**
     * Constructor.
     *
     * @param data A data.
     * @param count     The data count.
     */
    public CountedData(final T data, final int count) {
	this.data = data;
	this.count = count;
    }

    /**
     * Copy constructor.
     *
     * @param source Another CountedData object.
     */
    public CountedData(final CountedData<T> source) {
	this.data = source.data;
	this.count = source.count;
    }

    /**
     * Comparison method.
     *
     * @param target Object to compare against.
     * @return less than 0 if this data comes before target data, greater
     *         than 0 if this data comes after target data, 0 if the
     *         data are the same.
     */
    public int compareTo(final CountedData<T> target) {
	return this.data.compareTo(target.data);
    }

    /**
     * Decrements the data count.
     */
    public void decrementCount() {
	this.count--;
    }

    /**
     * Returns this data.
     *
     * @return this data.
     */
    public T getData() {
	return this.data;
    }

    /**
     * Returns this data count.
     *
     * @return this data count.
     */
    public int getCount() {
	return this.count;
    }

    /**
     * Increments the data count.
     */
    public void incrementCount() {
	this.count++;
    }

    /**
     * Sets the data count.
     *
     * @param count the new data count.
     */
    public void setCount(final int count) {
	this.count = count;
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{" + this.data + ": " + this.count + "}";
    }

}
