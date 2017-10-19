package design.pattern.command;

public class SimpleRemoteControl {
    Command slot;

    public void setCommand(Command slot) {
        this.slot = slot;
    }

    public void ButtonWasPressed() {
        slot.execute();
    }
}
