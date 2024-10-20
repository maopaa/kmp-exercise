package org.maopaa.kmp.notes.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.maopaa.kmp.notes.domain.entities.Note

@Composable
fun NoteComponent(
    note: Note,
    color: Color,
    onNoteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(8.dp))
            .background(color)
            .padding(16.dp)
            .clickable { onNoteClick() }
    ) {
        // Titulo de la Nota
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            // Aqui ira el boton de cerrar la nota
        }
        // Contenido de la Nota
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                fontSize = 16.sp
            )
        }
        // Fecha de creacion de la Nota
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column( modifier = Modifier.fillMaxWidth() ) {
                Text(
                    text = note.createdAt,
                    style = MaterialTheme.typography.body2,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}