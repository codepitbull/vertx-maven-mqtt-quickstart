package io.vertx.starter;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttConsumeClientMain {
  public static void main(String[] args) {

    String broker       = "tcp://localhost:1883";
    String clientId     = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);
      System.out.println("Connecting to broker: "+broker);
      sampleClient.connect(connOpts);
      sampleClient.subscribe("test", new IMqttMessageListener() {
        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
          System.out.printf("wuhuuu");
          System.out.println(s);
          System.out.println(mqttMessage.getPayload());
        }
      });
    } catch(MqttException me) {
      me.printStackTrace();
    }
  }

}
