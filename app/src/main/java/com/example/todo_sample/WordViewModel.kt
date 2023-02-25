package com.example.todo_sample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

/*
ViewModel

リポジトリとUI間の仲介役を担っている

ActivityクラスやFragmentクラスからUIデータの保持と処理の責務を分離し、
ViewModelがその責務を担うことで、単一責任の原則への遵守性を高めることができる

Repositoryは、ViewModelに必要な唯一の依存関係にあるのでパラメータとして取得する
 */

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    /*
    TODO: LiveDataの理解を記述する
     */

    // 単語リストをキャッシュに保存するためのメンバ変数
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    /*
    リポジトリのinsertメソッドのラッパー
    insertの処理を隠蔽し(カプセル化)、
    外部からの無駄なデータの変更を防止する

    なので、外部は公開されたインターフェイスからしか
    データにアクセスできない
     */

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}