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

// メイン画面

@Composable
fun MainScreen() {
    // アプリバー
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ToDoSample",
                        color = Color.White
                    )
                }
            )
        },
        floatingActionButton = {
            FABBtn {  }
        }
    ) { padding ->
        Row(
            Modifier.padding(padding)
        ) {
            // TODO: 今は仮の要素を入れているがDBから取得し、反映するようにする
        }
    }
}

@Composable
fun FABBtn(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, contentDescription = "追加")
    }
}