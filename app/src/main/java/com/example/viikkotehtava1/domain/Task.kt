package com.example.viikkotehtava1.domain

data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val priority: Int = 1,
    val dueDate: String,
    val done: Boolean = false
)
val mockTasks = listOf(
    Task(1, "Pese pyykit", "Kirjopesu 40 astetta", 2, "2026-01-13", true),
    Task(2, "Tee kurssitehtävä", "Mobiiliohjelmointi-kurssin viikkotehtävä 1", 1, "2026-01-16", true),
    Task(3, "Käy kaupassa", "Jauhelihaa, leipää ja juustoa", 3, "2026-01-13", true),
    Task(4, "Herää klo 9.30 kouluun", "klo 9.30", 1, "2026-01-15", false)
)
