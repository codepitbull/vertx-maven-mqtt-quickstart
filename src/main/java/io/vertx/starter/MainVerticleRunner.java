package io.vertx.starter;

import io.vertx.core.Vertx;

public class MainVerticleRunner {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainVerticle.class.getName());
  }
}