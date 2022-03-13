package lab.model;

import java.util.HashMap;
import java.util.Map;

public class Switch extends Node {
    public Switch(String name, String location) {
        super(name, location);
    }

    public Switch(String name, String hardwareAddress, String location) {
        super(name, hardwareAddress, location);
    }

    @Override
    public String toString() {
        return "Switch{" + "name='" + getName() + '\'' +
                ", hardwareAddress='" + getHardwareAddress() + '\'' +
                ", location='" + getLocation() + '\''+
                '}';
    }
}
