package com.example.viikkotehtava1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.viikkotehtava1.domain.Task
import com.example.viikkotehtava1.domain.mockTasks
import com.example.viikkotehtava1.ui.theme.Viikkotehtava1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkotehtava1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var taskList by remember { mutableStateOf(mockTasks) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Tehtävälista",
            style = MaterialTheme.typography.headlineMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { taskList = sortByDueDate(taskList) }) {
                Text("Järjestä")
            }
            Button(onClick = { taskList = filterByDone(taskList, true) }) {
                Text("Vain tehdyt")
            }
            Button(onClick = { taskList = mockTasks }) {
                Text("Palauta")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(taskList) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { taskList = toggleDone(taskList, task.id) }) {
                        Text(text = if (task.done) "✅" else "⭕")
                    }

                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(text = task.title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = "Eräpäivä: ${task.dueDate}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
                HorizontalDivider()
            }
        }
    }
}


fun addTask(list: List<Task>, task: Task): List<Task> {
    return list + task
}

fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map {
        if (it.id == id) it.copy(done = !it.done) else it
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}