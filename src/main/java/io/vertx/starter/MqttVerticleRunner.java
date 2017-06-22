package io.vertx.starter;

import io.vertx.core.Vertx;

public class MqttVerticleRunner {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MqttVerticle.class.getName());
  }
}
