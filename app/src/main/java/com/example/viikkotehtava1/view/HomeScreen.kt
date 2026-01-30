// view/HomeScreen.kt
package com.example.viikkotehtava1.userinterface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.view.DetailDialog
import com.example.viikkotehtava1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    val taskList by viewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var newTaskTitle by remember { mutableStateOf("") }
    // Lisätään kenttä kuvaukselle, jotta se ei puutu uudesta tehtävästä
    var newTaskDescription by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextField(
                    value = newTaskTitle,
                    onValueChange = { newTaskTitle = it },
                    label = { Text("Tehtävän nimi") },
                    modifier = Modifier.fillMaxWidth()
                )
                // Lisätty kenttä kuvaukselle
                TextField(
                    value = newTaskDescription,
                    onValueChange = { newTaskDescription = it },
                    label = { Text("Kuvaus") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        if (newTaskTitle.isNotBlank()) {
                            val newTask = Task(
                                id = (taskList.maxOfOrNull { it.id } ?: 0) + 1,
                                title = newTaskTitle,
                                description = newTaskDescription,
                                dueDate = "2026-01-20"
                            )
                            viewModel.addTask(newTask)
                            newTaskTitle = ""
                            newTaskDescription = ""
                        }
                    } // Korjattu sulkeiden määrä täällä
                ) { Text("Lisää tehtävä") }
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(taskList) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedTask = task }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )

                    Column(modifier = Modifier.padding(start = 8.dp).weight(1f)) {
                        Text(text = task.title, style = MaterialTheme.typography.titleLarge)
                        if (task.description.isNotBlank()) {
                            Text(
                                text = task.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray
                            )
                        }
                    }

                    IconButton(onClick = { viewModel.removeTask(task.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Poista")
                    }
                }
                HorizontalDivider()
            }
        }
    }

    selectedTask?.let { task ->
        DetailDialog(
            task = task,
            onDismiss = { selectedTask = null },
            onSave = { newTitle, newDescription ->
                viewModel.updateTask(task.id, newTitle, newDescription)
                selectedTask = null
            }
        )
    }
}