package jp.amaterasu_hyouka.core.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public class TextUtil {
    private TextUtil(){}

    public static Component clearItalic(Component component) {
        return component.decoration(TextDecoration.ITALIC, false);
    }
}
