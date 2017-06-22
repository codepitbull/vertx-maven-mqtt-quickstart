package io.vertx.starter;

import io.vertx.core.AbstractVerticle;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx.createHttpServer()
        .requestHandler(req -> {
          vertx.eventBus().send("event.web", "Received a request", reply -> {
            if(reply.failed())
              req.response().end("Boohohohohoho!");
            else
              req.response().end("Yeahhh!");
          });

        })
        .listen(8080);
  }

}
