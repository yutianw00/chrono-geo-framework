package framework.core.utils;

public class Location {
    private double longtitude;
    private double latitude;

    public Location(double longtitude, double latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(long longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    @Override
    public int hashCode() {
        return (int)(this.getLongtitude() * this.getLatitude());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Location)) {
            return false;
        }

        return (((Location) obj).getLatitude() == this.getLatitude() &&
                ((Location) obj).getLongtitude() == this.getLongtitude());
    }

    @Override
    public String toString() {
        return "Location{" +
                "longtitude=" + longtitude +
                ", latitude=" + latitude +
                '}';
    }
}
