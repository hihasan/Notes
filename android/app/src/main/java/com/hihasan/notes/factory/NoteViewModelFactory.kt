package com.hihasan.notes.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.hihasan.notes.data.repository.impl.OfflineNoteRepositoryImpl
import com.hihasan.notes.data.repository.impl.OnlineNoteRepositoryImpl
import com.hihasan.notes.views.notes.NoteViewModel

class NoteViewModelFactory(private val noteOnlineNoteRepositoryImpl: OnlineNoteRepositoryImpl,
                           private val noteOfflineNoteRepositoryImpl: OfflineNoteRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return NoteViewModel(noteOnlineNoteRepositoryImpl, noteOfflineNoteRepositoryImpl) as T
    }
}