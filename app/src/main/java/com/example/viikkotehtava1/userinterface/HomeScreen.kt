package com.example.viikkotehtava1.userinterface

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.viikkotehtava1.domain.Task

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    val taskList = viewModel.tasks

    var newTaskTitle by remember { mutableStateOf("") }
    var newTaskDueDate by remember { mutableStateOf("") }
    var newTaskIsDone by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = newTaskTitle,
                    onValueChange = { newTaskTitle = it },
                    label = { Text("Tehtävän nimi") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = newTaskDueDate,
                    onValueChange = { newTaskDueDate = it },
                    label = { Text("Eräpäivä (esim. 2026-01-20)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = newTaskIsDone, onCheckedChange = { newTaskIsDone = it })
                    Text("Merkkaa tehdyksi heti")
                }

                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        if (newTaskTitle.isNotBlank()) {
                            val newTask = Task(
                                id = (taskList.maxOfOrNull { it.id } ?: 0) + 1,
                                title = newTaskTitle,
                                dueDate = if (newTaskDueDate.isBlank()) "Ei eräpäivää" else newTaskDueDate,
                                done = newTaskIsDone,
                                description = "",
                                priority = 1
                            )
                            viewModel.addTask(newTask)
                            newTaskTitle = ""
                            newTaskDueDate = ""
                            newTaskIsDone = false
                        }
                    }
                ) {
                    Text("Lisää tehtävä")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.sortByDueDate() }) { Text("Järjestä") }
            Button(onClick = { viewModel.filterByDone(true) }) { Text("Vain tehdyt") }
            Button(onClick = { viewModel.resetTasks() }) { Text("Palauta") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(taskList) { task ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { viewModel.toggleDone(task.id) }
                    )

                    Column(modifier = Modifier.padding(start = 8.dp).weight(1f)) {
                        Text(text = task.title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = "Eräpäivä: ${task.dueDate}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    IconButton(onClick = { viewModel.removeTask(task.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Poista tehtävä")
                    }
                }
                HorizontalDivider()
            }
        }
    }
}