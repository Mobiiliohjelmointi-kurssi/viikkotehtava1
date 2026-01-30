// view/DetailScreen.kt
package com.example.viikkotehtava1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.example.viikkotehtava1.model.Task

@Composable
fun DetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    // Alustetaan tilat tehtävän nykyisillä tiedoilla
    var editedTitle by remember { mutableStateOf(task.title) }
    var editedDescription by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Muokkaa tehtävää") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = editedTitle,
                    onValueChange = { editedTitle = it },
                    label = { Text("Otsikko") }
                )
                TextField(
                    value = editedDescription,
                    onValueChange = { editedDescription = it },
                    label = { Text("Kuvaus") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(editedTitle, editedDescription) }) {
                Text("Tallenna")
            }
        },
        dismissButton = {
            // Vaihdettu poistonappi peruuta-napiksi
            TextButton(onClick = onDismiss) {
                Text("Peruuta")
            }
        }
    )
}