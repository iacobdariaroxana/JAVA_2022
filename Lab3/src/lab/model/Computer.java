package lab.model;

import java.util.Objects;

public class Computer extends Node implements Identifiable, Storage {
    private String ipAddress;
    private int storageCapacity; //GB

    public Computer(String name, String location, String ipAddress, int storageCapacity) {
        super(name, location);
        this.ipAddress = ipAddress;
        this.storageCapacity = storageCapacity;
    }

    public Computer(String name, String hardwareAddress, String location, String ipAddress, int storageCapacity) {
        super(name, hardwareAddress, location);
        this.ipAddress = ipAddress;
        this.storageCapacity = storageCapacity;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return Objects.equals(ipAddress, computer.ipAddress);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ipAddress);
    }

    @Override
    public String toString() {
        return "Computer{" + "name='" + getName() + '\'' +
                ", hardwareAddress='" + getHardwareAddress() + '\'' +
                ", location='" + getLocation() + '\'' +
                ",IP address='" + ipAddress + '\'' +
                ", storageCapacity=" + convert(storageCapacity, "MB") + "MB" +
                '}';
    }
}
