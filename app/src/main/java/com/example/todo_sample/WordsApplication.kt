package com.example.todo_sample

import android.app.Application

class WordsApplication: Application() {
    // 必要になったらインスタンスを作成したいのでby lazyにしている
    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}