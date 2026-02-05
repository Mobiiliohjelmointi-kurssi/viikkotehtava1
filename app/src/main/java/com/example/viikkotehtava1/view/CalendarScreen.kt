package com.example.viikkotehtava1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.viikkotehtava1.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(
    navController: NavController,
    viewModel: TaskViewModel
) {
    val taskList by viewModel.tasks.collectAsState()

    val groupedTasks = taskList.groupBy { it.dueDate }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Kalenteri", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = { navController.popBackStack() }) {
                Text("Takaisin listaan")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            groupedTasks.forEach { (date, tasks) ->
                item {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(tasks) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        ListItem(
                            headlineContent = { Text(task.title) },
                            supportingContent = { Text(task.description) },
                            trailingContent = {
                                Checkbox(
                                    checked = task.done,
                                    onCheckedChange = { viewModel.toggleDone(task.id) }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}