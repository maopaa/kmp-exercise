package org.maopaa.quicknotes.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.maopaa.quicknotes.notes.domain.NoteModule
import org.maopaa.quicknotes.notes.screen.AddNoteScreen
import org.maopaa.quicknotes.notes.screen.components.NoteComponent

object HomeScreen: Screen {

    @OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class,
        ExperimentalStdlibApi::class
    )
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val viewModel = rememberScreenModel { HomeViewModel(NoteModule.noteRepository) }
        val state = viewModel.state.collectAsState()

        // Detecta cuando se añade una nota y refresca la lista
        LaunchedEffect(Unit) {
            viewModel.onEvent(HomeEvent.refreshList)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "All notes")
                    },
                    actions = {
                        IconButton(onClick = { navigator.push(AddNoteScreen) }) {
                            Icon(Icons.Rounded.Add, contentDescription = null)
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxWidth().safeDrawingPadding()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(state.value.list) { note ->
                    NoteComponent(
                        note = note,
                        color = Color.Yellow,
                        onNoteClick = {
                            // Navigator(NoteDetailScreen(note, color = Color(note.color)))
                        },
                        modifier = Modifier.fillMaxWidth().padding(12.dp)
                            .animateItemPlacement()
                    )
                }
            }
        }
    }
}