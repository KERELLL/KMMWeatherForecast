package com.example.kmmweatherforecast.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmmweatherforecast.Greeting
import kotlinx.coroutines.*
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private val greeting = Greeting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
  //      setContentView(R.layout.activity_main)

        setContent{
            val scaffoldState = rememberScaffoldState()
            val textFieldState = remember {
                mutableListOf("")
            }
            val scope = rememberCoroutineScope()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)
                ) {
                    val text = remember { mutableStateOf("") }
                    var text2 : String = ""
                    TextField(text.value, { text.value = it }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = {
                        scope.launch {
                            mainScope.launch {
                                kotlin.runCatching {
                                    greeting.greeting(text.value)
                                }.onSuccess {
                                    text.value = it
                                }.onFailure {
                                    text.value= "Error: ${it.localizedMessage}"
                                }
                            }
                            scaffoldState.snackbarHostState.showSnackbar("Hello ${text2}")
                        }

                    }){
                        Text("Search")
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaulPreview(text: String){
        Text(text)
    }

}
