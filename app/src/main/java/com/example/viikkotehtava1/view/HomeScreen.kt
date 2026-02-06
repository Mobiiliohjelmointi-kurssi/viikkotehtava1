
package com.example.viikkotehtava1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.viikkotehtava1.model.Task
import com.example.viikkotehtava1.view.DetailDialog
import com.example.viikkotehtava1.view.ROUTE_CALENDAR
import com.example.viikkotehtava1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel,
    navController: NavController
) {
    val taskList by viewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Lisää tehtävä")
            }
        }
    ) { paddingValues ->
        Column(modifier = modifier
            .padding(paddingValues)
            .padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Tehtävälista", style = MaterialTheme.typography.headlineMedium)
                IconButton(onClick = { navController.navigate(ROUTE_CALENDAR) }) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Kalenteri")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
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

    if (showAddDialog) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Lisää uusi tehtävä") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextField(value = title, onValueChange = { title = it }, label = { Text("Otsikko") })
                    TextField(value = description, onValueChange = { description = it }, label = { Text("Kuvaus") })
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTask(Task(
                            id = (taskList.maxOfOrNull { it.id } ?: 0) + 1,
                            title = title,
                            description = description,
                            dueDate = "2026-02-05"
                        ))
                        showAddDialog = false
                    }
                }) { Text("Tallenna") }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) { Text("Peruuta") }
            }
        )
    }
}