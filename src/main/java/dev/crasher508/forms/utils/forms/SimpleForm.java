package dev.crasher508.forms.utils.forms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.crasher508.forms.utils.Form;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class SimpleForm extends Form {

    private final SimpleFormCallback callback;

    public SimpleForm(SimpleFormCallback callback) {
        super(null);
        this.data.addProperty("title", "");
        this.data.addProperty("content", "");
        this.data.add("buttons", new JsonArray());
        this.callback = callback;
    }

    public SimpleForm(SimpleFormCallback callback, CloseCallback closeCallback) {
        super(closeCallback);
        this.data.addProperty("title", "");
        this.data.addProperty("content", "");
        this.data.add("buttons", new JsonArray());
        this.callback = callback;
    }

    public void setContent(String content) {
        this.data.addProperty("content", content);
    }

    public void addButton(String text) {
        JsonObject element = new JsonObject();
        element.addProperty("text", text);
        JsonArray buttons = new JsonArray();
        if (this.data.get("buttons") != null) {
            buttons = (JsonArray) this.data.get("buttons");
        }
        buttons.add(element);
        this.data.add("buttons", buttons);
    }

    @Override
    public String getFormType() {
        return "form";
    }

    @Override
    public boolean handle(ProxiedPlayer proxiedPlayer, String response) {
        if (response == null)
            return true;
        try {
            int button = Integer.parseInt(response.trim());
            this.callback.onRun(proxiedPlayer, button);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
