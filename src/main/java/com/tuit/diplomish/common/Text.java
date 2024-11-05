package com.tuit.diplomish.common;

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
}
