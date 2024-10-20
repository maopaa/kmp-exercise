package org.maopaa.kmp.home.screen

sealed interface HomeEvent {

    data object refreshList : HomeEvent
}