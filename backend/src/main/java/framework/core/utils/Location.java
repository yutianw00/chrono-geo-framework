package framework.core.utils;

public class Location {
    private long longtitude;
    private long latitude;

    public Location(long longtitude, long latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public long getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(long longtitude) {
        this.longtitude = longtitude;
    }

    public long getLatitude() {
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
}
