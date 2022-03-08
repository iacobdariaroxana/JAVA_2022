package lab.model;

public interface Storage {
    int getStorageCapacity();

    default int convert(int storageCapacity, String unitOfStorage) {
        switch (unitOfStorage) {
            case "MB":
                return storageCapacity * 1000;
            case "KB":
                return storageCapacity * (int) Math.pow(10, 6);
            case "B":
                return storageCapacity * (int) Math.pow(10, 9);
            default:
                return storageCapacity;
        }
    }
}
