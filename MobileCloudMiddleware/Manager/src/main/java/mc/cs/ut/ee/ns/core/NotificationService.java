package mc.cs.ut.ee.ns.core;


public interface NotificationService {
	NotificationService withDeviceData(String deviceID, String... args);
	NotificationService withMessage(String message);
    NotificationService withPayload(String payload);
    void sendMessage();
}
