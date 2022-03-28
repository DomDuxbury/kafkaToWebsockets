package connector.server;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.java_websocket.WebSocket;

import java.net.InetSocketAddress;

public class KafkaConnectorServer extends FrameworkServer {

    private final KafkaProducer<String, String> producer;

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        Message message = Message.deserialize(s);
        String topic = message.getType();
        handleMessage(webSocket, topic, message.getPayload());
    }

    private void handleMessage(WebSocket socket, String topic, Object payload) {
        User user = socket.getAttachment();
        switch (topic) {
        }
    }

    private void sendKafkaMessage(String type, Integer userId, Object payload) {
        Message kafkaMessage = new Message(type, userId, 0, payload);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(type, kafkaMessage.serialize());
        producer.send(record);
    }

    public void handleFrontendMessage(Message message, String topic) {
        sendFrontendMessage(message, false);
    }

    public KafkaConnectorServer(int port, KafkaProducer<String, String> producer, int maxConcurrentSessions) {
        super(new InetSocketAddress(port), maxConcurrentSessions);
        this.producer = producer;
    }
}
