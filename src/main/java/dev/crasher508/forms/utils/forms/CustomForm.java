package dev.crasher508.forms.utils.forms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.crasher508.forms.utils.Form;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class CustomForm extends Form {

    private final CustomFormCallback callback;

    public CustomForm(CustomFormCallback callback) {
        super(null);
        this.data.addProperty("title", "");
        this.data.add("content", new JsonArray());
        this.callback = callback;
    }

    public CustomForm(CustomFormCallback callback, CloseCallback closeCallback) {
        super(closeCallback);
        this.data.addProperty("title", "");
        this.data.add("content", new JsonArray());
        this.callback = callback;
    }

    public void addLabel(String text) {
        JsonObject element = new JsonObject();
        element.addProperty("type", "label");
        element.addProperty("text", text);
        this.addContent(element);
    }

    public void addToggle(String text, boolean standard) {
        JsonObject element = new JsonObject();
        element.addProperty("type", "toggle");
        element.addProperty("text", text);
        element.addProperty("default", standard);
        this.addContent(element);
    }

    public void addSlider(String text, int min, int max, int step, int standard) {
        JsonObject element = new JsonObject();
        element.addProperty("type", "slider");
        element.addProperty("text", text);
        element.addProperty("min", min);
        element.addProperty("max", max);
        element.addProperty("step", step);
        element.addProperty("default", standard);
        this.addContent(element);
    }

    public void addStepSlider(String text, JsonArray steps, int standard) {
        JsonObject element = new JsonObject();
        element.addProperty("type", "step_slider");
        element.addProperty("text", text);
        element.add("steps", steps);
        element.addProperty("default", standard);
        this.addContent(element);
    }

    public void addDropdown(String text, JsonArray options, int standard) {
        JsonObject element = new JsonObject();
        element.addProperty("type", "dropdown");
        element.addProperty("text", text);
        element.add("options", options);
        element.addProperty("default", standard);
        this.addContent(element);
    }

    public void addInput(String text, String placeholder, String standard) {
        JsonObject element = new JsonObject();
        element.addProperty("type", "input");
        element.addProperty("text", text);
        element.addProperty("placeholder", placeholder);
        element.addProperty("default", standard);
        this.addContent(element);
    }

    @Override
    public String getFormType() {
        return "custom_form";
    }

    @Override
    public boolean handle(ProxiedPlayer proxiedPlayer, String response) {
        if (response == null)
            return true;
        try {
            JsonArray formData = JsonParser.parseString(response).getAsJsonArray();
            this.callback.onRun(proxiedPlayer, formData);
        } catch (IllegalStateException exception) {
            return true;
        }
        return true;
    }

    private void addContent(JsonObject element) {
        JsonArray contents = new JsonArray();
        if (this.data.get("content") != null) {
            contents = (JsonArray) this.data.get("content");
        }
        contents.add(element);
        this.data.add("content", contents);
    }
}
