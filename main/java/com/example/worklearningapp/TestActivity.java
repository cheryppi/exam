package com.example.worklearningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class TestActivity extends AppCompatActivity {
    // Объявление переменных для представлений
    private TextView wordTextView; // Текстовое поле для отображения слова
    private EditText translationEditText; // Поле ввода для перевода слова
    private Word currentWord; // Текущее слово для проверки перевода

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test); // Установка макета для активности

        // Инициализация представлений
        wordTextView = findViewById(R.id.word_text); // Получение ссылки на текстовое поле
        translationEditText = findViewById(R.id.translation_input); // Получение ссылки на поле ввода

        // Получаем следующее слово для тестирования
        nextWord();
    }

    public void nextWord() {
        // Получаем словарь из главной активности
        List<Word> dictionary = MainActivity.getDictionary();

        // Проверяем, пуст ли словарь
        if (dictionary == null || dictionary.isEmpty()) {
            Toast.makeText(this, "Словарь пуст", Toast.LENGTH_SHORT).show(); // Уведомление о пустом словаре
            return; // Выход из метода, если словарь пуст
        }

        // Получаем случайное слово из словаря
        Random rand = new Random();
        currentWord = dictionary.get(rand.nextInt(dictionary.size())); // Выбор случайного слова

        // Обновляем текстовое поле с текущим словом
        wordTextView.setText(currentWord.getWord()); // Отображение слова
        translationEditText.setText(""); // Очищаем поле ввода для нового перевода
    }

    public void checkTranslation(View view) {
        // Получаем введенный пользователем перевод
        String userTranslation = translationEditText.getText().toString().trim();

        // Проверяем перевод и выводим соответствующее сообщение
        if (userTranslation.equals(currentWord.getTranslation())) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show(); // Уведомление о правильном ответе
            nextWord(); // Переходим к следующему слову
        } else {
            Toast.makeText(this, "Неправильно. Правильный перевод: " + currentWord.getTranslation(), Toast.LENGTH_SHORT).show(); // Уведомление о неправильном ответе с правильным переводом
        }
    }

    public void goToMainScreen(View view) {
        // Переход к главной активности
        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent); // Запуск главной активности
        finish(); // Закрываем TestActivity, чтобы вернуться к MainActivity
    }
}
