package org.maopaa.quicknotes.home.screen

sealed interface HomeEvent {

    data object refreshList : HomeEvent
}