package io.vertx.starter;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttTopicSubscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MqttVerticle extends AbstractVerticle{
  @Override
  public void start() throws Exception {
    MqttServer server = MqttServer.create(vertx);

    Map<String, MqttEndpoint> topicToEndpoint = new HashMap<>();

    server.endpointHandler(endpoint -> {
      endpoint.subscribeHandler(s -> {
        List<MqttQoS> grantedQosLevels = new ArrayList<>();
        for(MqttTopicSubscription sub:s.topicSubscriptions()) {
          System.out.println(sub.topicName());
          topicToEndpoint.put(sub.topicName(), endpoint);
          grantedQosLevels.add(sub.qualityOfService());
        }
        endpoint.subscribeAcknowledge(s.messageId(), grantedQosLevels);
      });

      endpoint.publishHandler(s -> {
        topicToEndpoint.get(s.topicName());
        topicToEndpoint.values().forEach(ep -> ep.publish("hackathon/rainbow", Buffer.buffer(s.payload().toString()), MqttQoS.AT_MOST_ONCE, false, false));
      });

      // accept connection from the remote client
      endpoint.accept(false);
    });

    server.listen(1883);
  }
}
