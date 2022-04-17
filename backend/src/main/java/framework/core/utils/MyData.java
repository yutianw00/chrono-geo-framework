package framework.core.utils;

public class MyData {
    private Location location;
    private long time;
    private double data;

    public MyData(Location location, long time, double data) {
        this.location = location;
        this.time = time;
        this.data = data;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "location=" + location +
                ", time=" + time +
                ", data=" + data +
                '}';
    }
}
