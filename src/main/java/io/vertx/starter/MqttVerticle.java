package io.vertx.starter;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.mqtt.MqttServer;

import java.util.ArrayList;
import java.util.List;

public class MqttVerticle extends AbstractVerticle{
  @Override
  public void start() throws Exception {
    MqttServer server = MqttServer.create(vertx);

    server.endpointHandler(endpoint -> {
      endpoint.subscribeHandler(s -> {
        List<MqttQoS> grantedQosLevels = new ArrayList<>();
        s.topicSubscriptions().stream().forEach(su -> {
          System.out.println("topic-subscription "+su.topicName());
          grantedQosLevels.add(su.qualityOfService());
        });
        endpoint.subscribeAcknowledge(s.messageId(), grantedQosLevels);
      });

      endpoint.publishHandler(s -> {
        System.out.println("targettopic: "+s.topicName() + " content "+s.payload().toString());
      });

      // accept connection from the remote client
      endpoint.accept(false);
    });

    server.listen(1883);
  }
}
