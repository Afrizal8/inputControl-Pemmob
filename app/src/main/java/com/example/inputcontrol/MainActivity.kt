package com.example.inputcontrol

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inputcontrol.ui.theme.InputControlTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InputControlTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InputControls(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun InputControls(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    selectedDate = "$dayOfMonth/${month + 1}/$year"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text("Pilih Tanggal")
        }

        Text("Tanggal yang dipilih: $selectedDate")

        Button(onClick = {
            TimePickerDialog(
                context,
                { _, hourOfDay, minute ->
                    selectedTime = "$hourOfDay:$minute"
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }) {
            Text("Pilih Waktu")
        }

        Text("Waktu yang dipilih: $selectedTime")

        Button(onClick = {
            Toast.makeText(context, "Ini adalah alert sederhana!", Toast.LENGTH_SHORT).show()
        }) {
            Text("Tampilkan Alert")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputControlsPreview() {
    InputControlTheme {
        InputControls()
    }
}
