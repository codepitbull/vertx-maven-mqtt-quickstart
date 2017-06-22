package io.vertx.starter;

import io.vertx.core.AbstractVerticle;

public class EventbusVerticle extends AbstractVerticle{
  @Override
  public void start() throws Exception {
    vertx.eventBus().<String>consumer("event.web", m -> {
      System.out.println(hashCode()+" "+m.body());
      m.reply("Done!");
    });
  }
}
