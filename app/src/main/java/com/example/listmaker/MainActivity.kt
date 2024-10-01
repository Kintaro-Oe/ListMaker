package com.example.listmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listmaker.ui.theme.ListMakerTheme
import com.example.listmaker.ui.theme.SoftBlue
import com.example.listmaker.ui.theme.SoftRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListMakerTheme {
                var name by remember {
                    mutableStateOf("")
                }
                var names by remember {
                    mutableStateOf(listOf<String>())
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            shape = RoundedCornerShape(25.dp),
                            modifier = Modifier
                                .weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                if(name.isNotBlank()) {
                                    names = listOf<String>() + name + names
                                    name = ""
                                }
                            },
                            colors = ButtonDefaults.buttonColors(SoftBlue)
                        ) {
                            Text(
                                text = "Add"
                            )
                        }
                        Button(
                            onClick = {
                                if(names.isNotEmpty()) {
                                    names = listOf<String>()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(SoftRed),
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "C"
                            )
                        }
                    }

                    NamesList(names = names)

                }
            }
        }
    }
}

@Composable
fun NamesList(
    names: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(names) { currentName ->
            Text(
                text = currentName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}
