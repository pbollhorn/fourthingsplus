package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<String, String> userMap = new HashMap<>();

    public static void main(String[] args) {

        // Initialize usermap
        initUserMap();

        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(SessionConfig.sessionConfig()));
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing
        app.get("/", ctx -> ctx.render("index.html"));
        app.post("/login", ctx -> UserServices.login(ctx, userMap));
    }

    private static void initUserMap() {
        userMap.put("jon", "1234");
        userMap.put("Lene", "1234");
        userMap.put("Ole", "1234");
    }


}