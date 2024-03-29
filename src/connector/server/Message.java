package connector.server;

import com.google.gson.Gson;

public class Message {
    private String type;
    private final Object payload;
    private final Integer userId;
    private final Integer timeStep;
    private final int MAX_TIME_STEPS = 300;

    public Message(String type, Integer id, Integer timeStep, Object payload) {
        this.type = type;
        this.userId = id;
        this.payload = payload;
        this.timeStep = timeStep;
    }

    String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Message deserialize(String serializedMessage) {
        Gson gson = new Gson();
        return gson.fromJson(serializedMessage, Message.class);
    }

    public static Message deserialize(String serializedMessage, String type) {
        Gson gson = new Gson();
        Message message = gson.fromJson(serializedMessage, Message.class);
        message.setType(type);
        return message;
    }

    public boolean isFinalTimeStep() {
        return (timeStep + 1) % MAX_TIME_STEPS == 0;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }

    public Integer getTimeStep() {
        return timeStep;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", payload=" + payload +
                ", userId=" + userId +
                '}';
    }
}
