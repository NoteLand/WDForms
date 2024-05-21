# WDForms
Minecraft Bedrock Forms Support for https://github.com/WaterdogPE/WaterdogPE

## Examples
### SimpleForm
```java
import dev.crasher508.forms.utils.forms.SimpleForm;

SimpleForm form = new SimpleForm(((proxiedPlayer, response) -> {
            proxiedPlayer.sendMessage(response + " this is an int");
}));
form.setTitle("Title");
form.setContent("Content");
form.addButton("Button");
Forms.getInstance().sendForm(player, form);
```

### CustomForm
```java
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.crasher508.forms.utils.forms.CustomForm;

CustomForm form = new CustomForm((proxiedPlayer, response) -> {
    JsonElement inputElement = responseArray.get(5);
    if (inputElement == null)
        return;
    String input = inputElement.getAsJsonPrimitive().getAsString();
    if (input.isEmpty())
        return;
    proxiedPlayer.sendMessage(input);
});
JsonArray stepSliderSteps = new JsonArray();
stepSliderSteps.add("0");
stepSliderSteps.add("2");
stepSliderSteps.add("4");

JsonArray dropdownOptions = new JsonArray();
dropdownOptions.add("First");
dropdownOptions.add("Second");
dropdownOptions.add("Third");

form.setTitle("Title");
form.addLabel("Label");
form.addToggle("Toggle", default: true);
form.addSlider(text: "Slider", min: 0, max: 4, step: 1, default: 1);
form.addStepSlider("StepSlider", stepSliderSteps, default: 2);
form.addDropdown("Dropdown", dropdownOptions, default: 0);
form.addInput("Input", "Placeholder", default: "Default value");
Forms.getInstance().sendForm(player, form);
```

### ModalForm
```java
import dev.crasher508.forms.utils.forms.ModalForm;

ModalForm form = new ModalForm(((proxiedPlayer, response) -> {
        proxiedPlayer.sendMessage((response ? "true" : "false") + " pressed");
}));
form.setTitle("Title");
form.setContent("Content");
form.setButton1("Yes");
form.setButton2("No");
Forms.getInstance().sendForm(player, form);
```
