package com.tuit.diplomish.common;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public  enum Text {

    REGISTER("""
            🎤 register 🎤
            """),

    LOGIN ( """
            🛤 login 🛤
            """),
    PHONE("""
            📞share phone 📞
            """),
    START("/start"),

    ADMIN("""
            🏚 ADMIN 🏚
            """),
    USER("""
            🎓 USER(STUDENT) 🎓
            """),
    ADMIN_ADD_QUESTIONS("""
            Savol qo`shish ❔❓❓❓
            """),
    DEFAULT("default");

    private final String text;

    Text(java.lang.String text) {
        this.text = text;
    }

    public java.lang.String getText() {
        return text;
    }
    public static final Text[] VALUES = values();

    public static Optional<Text> getByText(String text) {
        return Arrays.stream(VALUES)
                .filter(txt -> Objects.equals(txt.getText().strip(),text))
                .findFirst();
    }
}
