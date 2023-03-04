package com.example.todo_sample

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todo_sample.ui.theme.TodosampleTheme

@Composable
fun WordAddDialog(
    onClickOK: () -> Unit,
    onClickCancel: () -> Unit,
) {
    var word by remember { mutableStateOf("") }

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val dialogHeight = screenHeight * 0.4f

    Dialog(
        onDismissRequest = { /*TODO*/ },
    ) {
        // ダイアログの外観
        Surface(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier
                    .height(dialogHeight.dp)
                    .background(MaterialTheme.colors.onPrimary)
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = "文字を追加",
                            color = MaterialTheme.colors.onPrimary
                        )
                    },
                )
                WordAddDialogContent(
                    title = "好きな文字を入力しよう！",
                    word = word,
                    { word = it },
                    dialogHeight = dialogHeight,
                    onClickOK = onClickOK,
                    onClickCancel = onClickCancel
                )
            }
        }
    }
}

@Composable
fun WordAddDialogContent(
    title: String,
    word: String,
    onWordChange: (String) -> Unit,
    dialogHeight: Float,
    onClickOK: () -> Unit,
    onClickCancel: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 各コンポーネントの高さ
        val minTextFiledHeight = 60
        val minButtonHeight = 48
        val textFiledHeight =
            if ((dialogHeight / 10) > minTextFiledHeight) (dialogHeight / 10) else minTextFiledHeight
        val buttonHeight =
            if ((dialogHeight / 10) > minButtonHeight) (dialogHeight / 10) else minButtonHeight

        Log.d("textFiledHeight", textFiledHeight.toString())
        Log.d("buttonHeight", buttonHeight.toString())

        Spacer(modifier = Modifier.height(5.dp))

        WordDialogTitle(
            title = title,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "hogehoge".repeat(20),
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 12.dp),
        )

        WordDialogOutlinedTextFiled(
            modifier = Modifier
                .height(textFiledHeight.toFloat().dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 12.dp),
            value = word,
            onValueChange = onWordChange,
            placeholder = "文字を入力",
            keyboardOptions = KeyboardOptions(),
            keyboardActions = KeyboardActions { }
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            val buttonModifier = Modifier
                .weight(0.5f)
                .height(buttonHeight.toFloat().dp)

            WordDialogOutlinedButton(
                text = "OK",
                modifier = buttonModifier,
                contentColor = MaterialTheme.colors.primary
            ) { onClickOK() }

            WordDialogOutlinedButton(
                text = "キャンセル",
                modifier = buttonModifier,
                contentColor = Color.Gray
            ) { onClickCancel() }
        }
    }
}

@Composable
fun WordDialogTitle(title: String, modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
        )
    }
}

@Composable
fun WordDialogOutlinedTextFiled(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    )
}

@Composable
fun WordDialogOutlinedButton(
    text: String,
    modifier: Modifier,
    contentColor: Color,
    onClick: () -> Unit,
) {
    // デフォルトで白抜きのボタンかつ
    // elevationがnullのため、OutlinedButtonを採用
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor
        ),
        shape = RectangleShape, // 真四角にする
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Preview
@Composable
fun PreviewWordAddDialog() {
    TodosampleTheme {
        WordAddDialog(onClickOK = {}, onClickCancel = {})
    }
}
