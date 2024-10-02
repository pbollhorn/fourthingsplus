package app;

import io.javalin.http.Context;

import java.util.Map;

public class UserServices {

    public static void login(Context ctx, Map<String, String> userMap) {

        String userName = ctx.formParam("username");
        String password = ctx.formParam("password");

        if (userMap.getOrDefault(userName, "A STRING\nWITH A LINE BREAK").equals(password)) {
            ctx.render("tasks.html");
        } else {
            ctx.attribute("message", "Desværre - no show");
            ctx.render("index.html");
        }


    }


}
