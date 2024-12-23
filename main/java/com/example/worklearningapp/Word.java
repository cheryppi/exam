package com.example.worklearningapp;

// Класс Word представляет слово и его перевод
public class Word {
    private String word; // Слово на исходном языке
    private String translation; // Перевод слова на целевой язык

    // Конструктор класса Word, инициализирующий слово и его перевод
    public Word(String word, String translation) {
        this.word = word; // Установка значения слова
        this.translation = translation; // Установка значения перевода
    }

    // Метод для получения слова
    public String getWord() {
        return word; // Возвращает слово
    }

    // Метод для получения перевода
    public String getTranslation() {
        return translation; // Возвращает перевод
    }

    // Переопределенный метод toString для удобного отображения объекта в виде строки
    @Override
    public String toString() {
        return word + " - " + translation; // Формат отображения: слово - перевод
    }
}