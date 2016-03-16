package net.transitdata.pelias_java_sdk;

public interface PeliasLocationProvider {
    public double getLat();
    public double getLon();
    public BoundingBox getBoundingBox();
}
