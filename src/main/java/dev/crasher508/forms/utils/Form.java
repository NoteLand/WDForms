package dev.crasher508.forms.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public abstract class Form {

    protected JsonObject data;

    protected CloseCallback closeCallback;

    public Form(CloseCallback closeCallback) {
        this.closeCallback = closeCallback;
        this.data = new JsonObject();
        this.data.addProperty("type", this.getFormType());
    }

    public abstract String getFormType();

    public interface SimpleFormCallback {
        void onRun(ProxiedPlayer proxiedPlayer, int response);
    }

    public interface ModalFormCallback {
        void onRun(ProxiedPlayer proxiedPlayer, boolean response);
    }

    public interface CustomFormCallback {
        void onRun(ProxiedPlayer proxiedPlayer, JsonArray response);
    }

    public interface CloseCallback {
        void onRun(ProxiedPlayer proxiedPlayer);
    }

    public void setTitle(String title) {
        this.data.addProperty("title", title);
    }

    public abstract void handle(ProxiedPlayer proxiedPlayer, String response);

    public JsonObject getData() {
        return data;
    }

    public CloseCallback getCloseCallback() {
        return closeCallback;
    }
}
