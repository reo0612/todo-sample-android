package com.example.todo_sample

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/*
Repository

様々なデータソースの仲介役としての役割

・リポジトリのコンストラクタ(オブジェクトを生成するための手続き)にはDAOが渡される
理由は、DAOには既にDBに対する読み取り・書き込みのインターフェイスが含まれているのでアクセスするだけで良いから
なのでDB自体をリポジトリに公開する必要はない
 */

class WordRepository(private val wordDao: WordDao) {
    // 単語のリスト
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    /*
    @Suppress:
    アノテーションされた要素で与えられたコンパイル時の警告を抑止する

    @WorkerThread:
    このアノテーションを追加したメソッド内でUI操作を書くとコンパイラに怒られる
     */

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}