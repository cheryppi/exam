package com.example.worklearningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddWordActivity extends AppCompatActivity {
    // Поля для ввода слова и его перевода
    private EditText wordEditText;
    private EditText translationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Установка макета активности
        setContentView(R.layout.activity_add_word);

        // Инициализация полей ввода с помощью их идентификаторов
        wordEditText = findViewById(R.id.word_input);
        translationEditText = findViewById(R.id.translation_input);
    }

    // Метод, вызываемый при нажатии на кнопку добавления слова
    public void addWord(View view) {
        // Получение текста из полей ввода и удаление лишних пробелов
        String word = wordEditText.getText().toString().trim();
        String translation = translationEditText.getText().toString().trim();

        // Проверка, заполнены ли оба поля
        if (!word.isEmpty() && !translation.isEmpty()) {
            // Получение текущего словаря из MainActivity
            List<Word> dictionary = (List<Word>) MainActivity.getDictionary();

            // Создание нового слова и добавление его в словарь
            dictionary.add(new Word(word, translation));
            // Сообщение пользователю об успешном добавлении слова
            Toast.makeText(this, "Слово добавлено", Toast.LENGTH_SHORT).show();
            finish(); // Завершение активности и возврат к предыдущему экрану
        } else {
            // Сообщение пользователю о необходимости заполнить оба поля
            Toast.makeText(this, "Пожалуйста, заполните оба поля", Toast.LENGTH_SHORT).show();
        }
    }
}