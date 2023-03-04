package com.example.todo_sample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/*

Entity

RoomDBでこのクラスを使用するためには、アノテーションを使用して
DBと関連付けを行わないといけない

各アノテーションの説明は以下の通り


@Entity(tableName = ""):

SQLiteのテーブルを表す
Entityであることを明確にするため、@Entityアノテーションをクラスに付ける
テーブル名を指定する事ができ、クラス名と異なる名前でもOK

@PrimaryKey:

主キーを表す
今回は、シンプルに各単語自体を主キーに指定している

一意のキーを自動生成する場合は以下のように記述する

 ```
 @Entity(tableName = "tableName")
 class word(
      @PrimaryKey(autoGenerate = true) val id: Int,
      @ColumuInfo(name = "word") val word: String
 )
 ```

@ColumuInfo(name = "word"):

テーブル内のアラムを表す
メンバ変数と異なる名前にする必要がある

 */

@Entity(tableName = "Word_Table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)

/*
DAO(データアクセスオブジェクト)

SQLクエリを指定し、メソッド呼び出しに関連付けする
一般的な`SELECT`などのクエリを、アノテーションに関連付けすることで
コンパイラがSQLのチェック及び生成してくれる

RoomではこのDAOを使用して、APIを作成する

各アノテーションなどの説明は以下の通り

@Dao:

このアノテーションは、RoomのDAOクラスであることを示す

@Insert(onConflict = OnConflictStrategy.IGNORE):

@Insertアノテーションは他のアノテーションのようにSQLを指定する必要がない

`onConflict = OnConflictStrategy.IGNORE`では、
既にテーブル内に同じカラムがある場合は`INSERT`を無視する

@Query:

パラメータに文字列を指定する必要があり、そこにSQLを指定する

 */

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}