package dev.crasher508.forms.utils.forms;

import dev.crasher508.forms.utils.Form;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class ModalForm extends Form {

    private final ModalFormCallback callback;

    public ModalForm(ModalFormCallback callback) {
        super(null);
        this.data.addProperty("title", "");
        this.data.addProperty("content", "");
        this.data.addProperty("button1", "");
        this.data.addProperty("button2", "");
        this.callback = callback;
    }

    public ModalForm(ModalFormCallback callback, CloseCallback closeCallback) {
        super(closeCallback);
        this.data.addProperty("title", "");
        this.data.addProperty("content", "");
        this.data.addProperty("button1", "");
        this.data.addProperty("button2", "");
        this.callback = callback;
    }

    @Override
    public String getFormType() {
        return "modal";
    }

    public void setContent(String content) {
        this.data.addProperty("content", content);
    }

    public void setButton1(String text) {
        this.data.addProperty("button1", text);
    }

    public void setButton2(String text) {
        this.data.addProperty("button2", text);
    }

    @Override
    public boolean handle(ProxiedPlayer proxiedPlayer, String response) {
        if (response == null)
            return true;
        this.callback.onRun(proxiedPlayer, response.trim().equals("true"));
        return true;
    }
}
