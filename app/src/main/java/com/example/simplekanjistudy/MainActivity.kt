package com.example.simplekanjistudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplekanjistudy.data.KanjiCard
import com.example.simplekanjistudy.data.KanjiRepository
import com.example.simplekanjistudy.ui.theme.SimpleKanjiStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleKanjiStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KanjiStudyScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun KanjiStudyScreen(modifier: Modifier = Modifier) {
    var selectedLevel by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Kanji Study", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (selectedLevel == null) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("N5", "N4", "N3", "N2", "N1").forEach { level ->
                    Button(onClick = { selectedLevel = level }) {
                        Text("Level $level")
                    }
                }
            }
        } else {
            Button(onClick = { selectedLevel = null }) {
                Text("← Back to level selection")
            }
            Spacer(modifier = Modifier.height(16.dp))
            KanjiList(KanjiRepository.allKanji[selectedLevel] ?: emptyList())
        }
    }
}

@Composable
fun KanjiList(kanjiList: List<KanjiCard>) {
    Column {
        kanjiList.forEach { card ->
            KanjiCardView(card)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun KanjiCardView(card: KanjiCard) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isRevealed by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp,
            focusedElevation = 6.dp,
            disabledElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = card.kanji, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))

            if (selectedOption == null) {
                // Show options to select knowledge
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { selectedOption = "I know"; isRevealed = true }) {
                        Text("I know")
                    }
                    Button(onClick = { selectedOption = "I'm unsure"; isRevealed = true }) {
                        Text("I'm unsure")
                    }
                    Button(onClick = { selectedOption = "I don't know"; isRevealed = true }) {
                        Text("I don't know")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reveal the meaning after selection
            if (isRevealed) {
                Text(text = card.meaning, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleKanjiStudyTheme {
        KanjiStudyScreen()
    }
}
