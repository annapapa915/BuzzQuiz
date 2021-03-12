import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * The class KeyBinding contains the key bindings method used in CompQuestionPanel.
 * @author Anna Papadopoulou
 * @author Glykeria Fountoukidou
 */
public class KeyBinding {
    /**
     * The constructor of the class, it assigns a new key of our choice in the keyboard in a button of our choice.
     * The method works via input and action map and each key that is assigned behaves as a mouse click.
     * @param button JButton type object, symbolizes the button we want to assign a keybinding to.
     * @param key A string that symbolizes which keyboard key corresponds to the button.
     * @author Anna Papadopoulou
     * @author Glykeria Fountoukidou
     */
    public KeyBinding(JButton button, String key){
        button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);
        button.getActionMap().put(key, new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
                button.doClick();
            }
        });
    }
}
