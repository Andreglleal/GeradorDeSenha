package com.trabalhoandroid.geradordesenha

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trabalhoandroid.geradordesenha.ui.theme.Blue900
import com.trabalhoandroid.geradordesenha.ui.theme.BlueA200
import com.trabalhoandroid.geradordesenha.ui.theme.Green900
import com.trabalhoandroid.geradordesenha.ui.theme.RedA200
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home() {
    val clipboardManager = LocalClipboardManager.current
    var Password  by remember { mutableStateOf("") }
    var strength by remember { mutableStateOf("") }
    val specialChar = arrayOf(
        "!","$","%","(",")",",",":","<",">","/","]","~","[","@","?","|"
    )
    val uperCase = arrayOf(
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
        "Q","R","S","T","U","V","X","Z","W","Y"
    )
    val lowerCase = arrayOf(
        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
        "q","r","s","t","u","v","x","z","w","y"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gerador de Senha") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RedA200
                )
            )
        }, bottomBar = {
            BottomAppBar(
                containerColor = RedA200
            ) {
            }
        },
    ) { PaddingValues ->
        Column(
            modifier = Modifier.padding(PaddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                OutlinedTextField(
                    value = Password,
                    onValueChange = {
                        Password = it
                    },
                    label = { Text(text = "Password")},
                    textStyle = TextStyle(
                        color = Green900,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    modifier = Modifier.padding(10.dp).width(250.dp)
                )
                IconButton(
                    onClick = {
                        clipboardManager.setText(annotatedString = AnnotatedString(Password))
                    },
                    modifier = Modifier.size(50.dp).background(
                        color = Blue900,
                        shape = RoundedCornerShape(15.dp)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ContentCopy,
                        contentDescription = "Content copy",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {
                        val number = Random.nextInt(100000,999999)

                        Password = "${uperCase.random()}${uperCase.random()}" +
                                "${lowerCase.random()}${lowerCase.random()}" +
                                "$number" +
                                "${specialChar.random()}${specialChar.random()}"
                        strength = "Senha Forte"
                    },
                    modifier = Modifier.size(50.dp).background(
                        color = BlueA200,
                        shape = RoundedCornerShape(15.dp)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Refresh,
                        contentDescription = "Refresh",
                        tint = Color.White
                    )
                }
            }
            Text(
                text = strength,
                color = Green900,
                fontSize = 20.sp,
            )
        }
    }
}




@Preview
@Composable
fun HomePreview() {
    Home()
}

