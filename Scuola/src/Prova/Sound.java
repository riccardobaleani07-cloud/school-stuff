public class Sound {
    private final double[] sounds;
    private final int size;
    private final double maxRange;
    private int nValues = 0;

    public Sound(int size, double maxRange){
        if (size < 1000){
            throw  new IllegalArgumentException("size must be at least 1000");
        }
        if (maxRange < 0){
            throw new IllegalArgumentException("maxRange must be strictly positive");
        }
        this.size = size;
        this.maxRange = maxRange;
        this.sounds = new double[size];
    }

    public int size(){
        return size;
    }

    public int elements(){
        return nValues;
    }

    public boolean put(double v){
        if (nValues < size && -maxRange < v && v < maxRange){
            sounds[nValues++] = v;
            return true;
        }
        return false;
    }

    public double get(int i){
        if (i < 0 || i >= nValues){
            throw new IllegalArgumentException("index out of bound");
        }
        return sounds[i];
    }

    public boolean isAPointOfInversion(int i){
        if (i >= nValues - 1){
            return false;
        }
        return sounds[i] * sounds[i + 1] < 0;
    }

    public int[] between(int from, int to){
        int[] indexes = new int[0];

        for (int i = 0; i < nValues; i++) {
            if (sounds[i] >= from && sounds[i] <= to) {

                indexes = new int[indexes.length + 1];
                indexes[indexes.length - 1] = i;
            }
        }

        return indexes;
    }
}
