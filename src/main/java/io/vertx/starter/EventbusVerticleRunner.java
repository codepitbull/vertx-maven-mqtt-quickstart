package io.vertx.starter;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class EventbusVerticleRunner {
  public static void main(String[] args) {
    Vertx.clusteredVertx(new VertxOptions(), asyncResult -> {
      if(asyncResult.succeeded())
        asyncResult.result().deployVerticle(EventbusVerticle.class.getName());
      else
        asyncResult.cause().printStackTrace();
    });
  }
}
