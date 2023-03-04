package com.example.todo_sample

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.FloatingActionButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// メイン画面

@Composable
fun WordScreen() {
    // アプリバー
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "WordSample",
                        color = Color.White
                    )
                }
            )
        },
        floatingActionButton = {
            WordAddButton {
                // TODO: 文字を追加するダイアログを表示する
            }
        },
    ) { padding ->
        Column(
            Modifier.padding(padding)
        )
        {
            // TODO: 今は仮の要素を入れているがDBから取得し、反映するようにする
        }
    }
}

@Composable
fun WordAddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier.size(60.dp),
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "文字を追加",
        )
    }
}

@Composable
fun WordList(

) {

}

@Composable
fun WordListItem() {

}

@Preview
@Composable
fun PreviewWordScreen() {
    WordScreen()
}