package com.example.worklearningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Список слов в словаре
    private static List<Word> dictionary;
    // Элемент ListView для отображения списка слов
    private ListView listView;
    // Адаптер для связывания данных со списком
    private ArrayAdapter<Word> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация словаря как нового списка
        dictionary = new ArrayList<>();

        // Настройка ListView для отображения слов
        listView = findViewById(R.id.word_list);
        // Создание адаптера для связывания списка слов с ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dictionary);
        listView.setAdapter(adapter);

        // Обработчик нажатий на элементы списка
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Получение выбранного слова по позиции
                Word selectedWord = dictionary.get(position);
                // Показ сообщения с выбранным словом
                Toast.makeText(MainActivity.this, "Вы выбрали: " + selectedWord.getWord(), Toast.LENGTH_SHORT).show();
            }
        });

        // Настройка кнопки добавления слова
        Button addWordButton = findViewById(R.id.add_word_button);
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран добавления нового слова
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });

        // Настройка кнопки начала тестирования
        Button startTestingButton = findViewById(R.id.start_testing_button);
        startTestingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на экран тестирования
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        // Настройка кнопки очистки словаря
        Button clearDictionaryButton = findViewById(R.id.clear_dictionary_button);
        clearDictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDictionary(); // Вызов метода очистки словаря
            }
        });
    }

    // Метод для очистки словаря и обновления интерфейса
    private void clearDictionary() {
        dictionary.clear(); // Очистка списка слов
        adapter.notifyDataSetChanged(); // Уведомление адаптера об изменениях в данных
        Toast.makeText(this, "Словарь очищен", Toast.LENGTH_SHORT).show(); // Сообщение об успешной очистке
    }

    // Статический метод для получения словаря из других классов
    public static List<Word> getDictionary() {
        return dictionary; // Возврат текущего списка слов
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged(); // Обновление адаптера при возвращении в активность для отображения актуальных данных
    }
}