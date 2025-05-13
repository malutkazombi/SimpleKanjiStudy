package com.example.simplekanjistudy.data

data class KanjiCard(val kanji: String, val meaning: String, val level: String)

object KanjiRepository {
    val allKanji: Map<String, List<KanjiCard>> = mapOf(
        "N5" to listOf(
            KanjiCard("日", "Day/Sun", "N5"),
            KanjiCard("月", "Moon/Month", "N5"),
            KanjiCard("人", "Person", "N5")
        ),
        "N4" to listOf(
            KanjiCard("病", "Sick", "N4"),
            KanjiCard("院", "Institution", "N4")
        ),
        "N3" to listOf(
            KanjiCard("感", "Feeling", "N3"),
            KanjiCard("情", "Emotion", "N3")
        ),
        "N2" to emptyList(), // Пока пусто
        "N1" to emptyList()  // Пока пусто
    )
}
