package lab.model;

import java.util.Objects;

public class Router extends Node implements Identifiable {
    private String ipAddress;

    public Router(String name, String location, String ipAddress) {
        super(name, location);
        this.ipAddress = ipAddress;
    }

    public Router(String name, String hardwareAddress, String location, String address) {
        super(name, hardwareAddress, location);
        this.setAddress(address);
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    public void setAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Router router = (Router) o;
        return Objects.equals(ipAddress, router.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ipAddress);
    }

    @Override
    public String toString() {
        return "Router{" + "name='" + getName() + '\'' +
                ", hardwareAddress='" + getHardwareAddress() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", IP address='" + ipAddress + '\'' +
                '}';
    }
}
