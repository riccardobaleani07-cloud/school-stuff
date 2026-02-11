public class Buffer {
    private int[] buffer;
    private int capacity;
    private int size; //is used as 'legal' size of the buffer

    public Buffer(int capacity){
        if (capacity <= 0){
            throw new IllegalArgumentException("capacity must be strictly positive");
        }
        this.capacity = capacity;
        size = 0; //Default setting
        buffer = new int[this.capacity];
    }

    public void add(int value){
        if (size < capacity){
            buffer[size] = value;
            size++;
        } else {
            int[] shiftedBuffer = new int[capacity];
            for (int i = 0; i < size - 1; i++){
                shiftedBuffer[i] = buffer[i + 1]; //i could write this instead of i++ in the third element of the for cycle, but this way is more readeable
            }
            shiftedBuffer[size - 1] = value;
            buffer = shiftedBuffer;
        }
    }

    public void addAll(int[] values){
        if (values == null){
            throw new IllegalArgumentException("cannot resolve for a null array");
        }
        for (int i = 0; i < values.length; i++){
            add(values[i]);
        }
    }

    public int get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        return buffer[index];
    }

    public int[] toArray(){
        int[] copy = new int[size];
        for (int i = 0; i < size; i++){
            copy[i] = buffer[i];
        }
        return copy;
    }

    private int abs(int number){
        if (number < 0){
            return -1 * number;
        }
        return number;
    }

    private int getMax(int n1, int n2){
        if (n1 > n2){
            return n1;
        }
        return n2;
    }

    public int longestStableSegment(){
        int segmentOne = 1;
        int segmentTwo = 0;

        if (size == 0){
            return 0;
        }

        for (int i = 0; i < size - 1; i++){
            if (abs(buffer[i] - buffer[i + 1]) <= 1){
                segmentOne++;
            } else {
                segmentTwo = getMax(segmentOne, segmentTwo);
                segmentOne = 1;
            }
        }

        return getMax(segmentOne, segmentTwo);
    }
}
