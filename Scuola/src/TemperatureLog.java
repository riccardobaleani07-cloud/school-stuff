public class TemperatureLog {
    private int[] measurements = new int[0];
    private String sensorId;
    private int minAllowed;
    private int maxAllowed;

    public TemperatureLog(String sensorId, int minAllowed, int maxAllowed){
        if (sensorId == null || sensorId.isBlank()){
            throw new IllegalArgumentException("sensorID cannot be null or empty");
        } else if (minAllowed >= maxAllowed){
            throw new IllegalArgumentException("minAllowed must be strictly less than maxAllowed");
        }
        this.sensorId = sensorId;
        this.minAllowed = minAllowed;
        this.maxAllowed = maxAllowed;
    }

    public void addMeasurements(int value){
        if (value < minAllowed || value > maxAllowed){
            throw new IllegalArgumentException("value out of boundaries");
        }

        int[] newMeasurements = new int[measurements.length+1];
        for (int i = 0; i < measurements.length; i++){
            newMeasurements[i] = measurements[i];
        }
        newMeasurements[measurements.length] = value;
        measurements = newMeasurements;
    }

    public void addMeasurements(int[] values){


        for (int i = 0; i < values.length; i++){
            if (values[i] < minAllowed || values[i] > maxAllowed){
                throw new IllegalArgumentException("value out of boundaries");
            }
        }

        int newLength = measurements.length + values.length;
        int[] newMeasurements = new int[newLength];
        for (int i = 0; i < measurements.length; i++){
            newMeasurements[i] = measurements[i];
        }
        for (int i = measurements.length; i < newLength; i++){
            newMeasurements[i] = values[i-values.length];
        }
        measurements = newMeasurements;
    }

    public int[] getMeasurements(){
        return measurements.clone();
    }

    public int getMin(){
        int min = maxAllowed;
        //teoretically i don't allow adding values over the maxAllowed so i can exploit this
        if (measurements.length != 0){
            for (int i = 0; i < measurements.length; i++){
                if (measurements[i] < min){
                    min = measurements[i];
                }
            }           
        } else {
            throw new IllegalStateException("there are no measurements");
        }
        return min;
    }

    public int getMax(){
        int max = minAllowed;
        //teoretically i don't allow adding values under the minAllowed so i can exploit this
        if (measurements.length != 0){
            for (int i = 0; i < measurements.length; i++){
                if (measurements[i] > max){
                    max = measurements[i];
                }
            }           
        } else {
            throw new IllegalStateException("there are no measurements");
        }
        return max;
    }

    public double getAverage(){
        int average = 0;
        if (measurements.length != 0){
            for (int i = 0; i < measurements.length; i++){
                average += measurements[i];
            }           
        } else {
            throw new IllegalStateException("there are no measurements");
        }
        return (double) average / measurements.length;
    }

    public int getMeasurementsAt(int index){
        if (index < 0 || index >= measurements.length){
            throw new IndexOutOfBoundsException();
        }
        return measurements[index];
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof TemperatureLog){
            TemperatureLog second = (TemperatureLog) o;
            return this.sensorId.equals(second.sensorId);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 31 * sensorId.hashCode();
    }

    @Override
    public String toString() {
        try {
            return "TemperatureLog[sensor=" + sensorId + ", count=" + measurements.length + ", avg=" + getAverage() + "]";
        } catch (IllegalStateException e){
            return "TemperatureLog[sensor=" + sensorId + ", count=" + measurements.length + ", avg=" + e + "]";

        }
    }

}
