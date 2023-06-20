package app.peter.stateexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.peter.stateexample.ui.theme.StateExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    // 그냥 리멤버는 환경의 변화를 감지하지 못한다. 액티비티가 전환되어 다시그리는 등의 행위.
    // rememberSaveable 이걸 사용하면 저장할 수 있다.
    var textState by remember { mutableStateOf("") }
    // var textState by rememberSaveable { mutableStateOf("") }
    val onTextChange = { text: String ->
        textState = text
    }

    MyTextField(text = textState, onTextChange = onTextChange)
}

@Composable
fun MyTextField(text: String, onTextChange: (String) -> Unit) {
    // 위임자를 이용한 간결한 처리
//    var textState by remember { mutableStateOf("") }

    // key, call 방식으로도 구현가능
//    var (textValue, setText) = remember {
//        mutableStateOf("")
//    }

//    val onTextChange = { text: String ->
//        textState = text
//    }

    BasicTextField(value = text, onValueChange = onTextChange)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateExampleTheme {
        Column {
            DemoScreen()
            FunctionA()
        }

    }
}

@Composable
fun FunctionA() {
    var switchState by remember { mutableStateOf(true) }

    val onSwitchChange = { value: Boolean ->
        switchState = value
    }

    FunctionB(
        switchState = switchState,
        onSwitchChange = onSwitchChange
    )
}

@Composable
fun FunctionB(switchState: Boolean, onSwitchChange: (Boolean) -> Unit) {
    Switch(checked = switchState, onCheckedChange = onSwitchChange)
}