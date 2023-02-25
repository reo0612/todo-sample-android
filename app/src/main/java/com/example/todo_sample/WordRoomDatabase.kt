package com.example.todo_sample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
RoomDBとは

・Androidアプリケーションにおけるデータベースアクセス層を提供する
・コンパイル時にSQLクエリの文法間違いなどを検出する(実行時エラーを防ぐ)
・LiveDataを使用して、データベースの変更を監視し、UIを自動的に反映することも可能
・デフォルトでメインスレッドからクエリを発行せず、バックグラウンドスレッドで自動的に実行する

RoomDBの実装

RoomDBでは、`RoomDatabase()`を拡張した抽象クラスを作成する必要がある
通常はアプリ全体で必要なRoomDBのインスタンスは1つのみなのでシングルトンにする
 */

// Wordクラスのテーブル(エンティティ)を持つRoomデータベースクラス
// パラメータから、このデータベースに使用するEntityの定義、バージョンの指定を行う
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    // DBインスタンスが同時に開かれるのを防ぐため、
    // クラス内のシングルトンとして定義する
    companion object {
        // @Volatileアノテーション
        // スレッドのキャッシュに保存せず、メモリに直接書き込む
        // このアノテーションを付ける事によって、各スレッド間でフィールドの値の相違を防ぐ事ができる
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        // このシングルトンを返す
        fun getDatabase(context: Context): WordRoomDatabase {
            // インスタンスを生成しているならそれを返す
            return INSTANCE ?: synchronized(this) {
                // nullであれば、データベースのインスタンスを生成
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}