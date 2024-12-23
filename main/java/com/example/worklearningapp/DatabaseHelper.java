package com.example.worklearningapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Имя базы данных
    private static final String DATABASE_NAME = "words.db";
    // Версия базы данных
    private static final int DATABASE_VERSION = 1;

    // Название таблицы и названия столбцов
    private static final String TABLE_WORDS = "words";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_TRANSLATION = "translation";

    // Конструктор класса, который вызывает конструктор родительского класса
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод для создания базы данных и таблицы при первом запуске
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL-запрос для создания таблицы слов
        String createTable = "CREATE TABLE " + TABLE_WORDS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WORD + " TEXT NOT NULL, " +
                COLUMN_TRANSLATION + " TEXT NOT NULL)";
        // Выполнение SQL-запроса
        db.execSQL(createTable);
    }

    // Метод, вызываемый при обновлении версии базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаление старой таблицы, если она существует
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        // Создание новой таблицы
        onCreate(db);
    }

    // Метод для добавления нового слова в базу данных
    public void addWord(String word, String translation) {
        // Получение объекта SQLiteDatabase для записи данных
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Заполнение значений для вставки в таблицу
        values.put(COLUMN_WORD, word);
        values.put(COLUMN_TRANSLATION, translation);
        // Вставка значений в таблицу
        db.insert(TABLE_WORDS, null, values);
        // Закрытие базы данных после операции
        db.close();
    }

    // Метод для получения всех слов из базы данных
    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();
        // SQL-запрос для выборки всех слов из таблицы
        String selectQuery = "SELECT * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Проверка наличия результатов и извлечение слов из курсора
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                // Получение слова из курсора по индексу столбца
                String word = cursor.getString(cursor.getColumnIndex(COLUMN_WORD));
                words.add(word); // Добавление слова в список
            } while (cursor.moveToNext());
        }

        // Закрытие курсора и базы данных после завершения выборки
        cursor.close();
        db.close();
        return words; // Возврат списка всех слов
    }

    // Метод для очистки базы данных (удаление всех слов)
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Выполнение SQL-запроса для удаления всех записей из таблицы
        db.execSQL("DELETE FROM " + TABLE_WORDS);
        // Закрытие базы данных после операции
        db.close();
    }
}