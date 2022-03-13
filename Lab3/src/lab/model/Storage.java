package lab.model;

public interface Storage {
    int getStorageCapacity();

    default int convert(int storageCapacity, String unitOfStorage) {
        return switch (unitOfStorage) {
            case "MB" -> storageCapacity * 1000;
            case "KB" -> storageCapacity * (int) Math.pow(10, 6);
            case "B" -> storageCapacity * (int) Math.pow(10, 9);
            default -> storageCapacity;
        };
    }
}
