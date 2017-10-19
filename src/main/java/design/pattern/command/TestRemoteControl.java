package design.pattern.command;

/**
 * 命令模式
 *
 * @author YuChuanQi
 * @since 2015年11月25日 上午11:37:37
 */
public class TestRemoteControl {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();

        Light light = new Light();
        Command lightOn = new LightOnCommand(light);

        remote.setCommand(lightOn);
        remote.ButtonWasPressed();
    }
}
