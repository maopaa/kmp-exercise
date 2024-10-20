package org.maopaa.quicknotes.notes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime
import org.maopaa.quicknotes.notes.domain.NoteModule
import org.maopaa.quicknotes.notes.domain.entities.Note

object AddNoteScreen: Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { AddNoteViewModel(NoteModule.noteRepository) }
        val state by viewModel.state.collectAsState()

        // Cuando haya anotado una nota, debe de volver a la pantalla anterior
        // y refrescar la lista de notas
        LaunchedEffect(state.isSuccess) {
            if (state.isSuccess) navigator.pop() // Volver a la pantalla anterior
        }

        Scaffold(
            topBar = {
                androidx.compose.material.TopAppBar(
                    title = {
                        androidx.compose.material.Text(text = "Add Note")
                    },
                    navigationIcon = {
                        androidx.compose.material.IconButton(onClick = { navigator.pop() }) {
                            androidx.compose.material.Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxSize().safeDrawingPadding()
        ) {
            AddNoteScreenContent(state, onEvent = viewModel::onEvent)
        }
    }
}

@OptIn(ExperimentalStdlibApi::class, FormatStringsInDatetimeFormats::class)
@Composable
private fun AddNoteScreenContent(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(20.dp)
    ) {
        TextField(
            value = title, onValueChange = { title = it },
            label = { androidx.compose.material.Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = content, onValueChange = { content = it },
            label = { androidx.compose.material.Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
        )
        if (state.isLoading) {
            androidx.compose.material.CircularProgressIndicator()
        } else {
            Button(
                onClick = { onEvent( NoteEvent.add(
                    note = Note(
                        id = "",
                        title, content,
                        color = Color.Yellow.toString(),
                        createdAt = Clock.System.now()
                            .toLocalDateTime(TimeZone.currentSystemDefault()).toString()
                    )

                ))},
                enabled = title.isNotBlank(),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                androidx.compose.material.Text("Add Note")
            }
        }
    }
}