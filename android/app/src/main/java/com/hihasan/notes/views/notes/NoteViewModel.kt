package com.hihasan.notes.views.notes

import com.hihasan.notes.data.repository.NoteRepository
import com.hihasan.notes.data.repository.impl.OfflineNoteRepositoryImpl
import com.hihasan.notes.data.repository.impl.OnlineNoteRepositoryImpl
import com.hihasan.notes.utils.App
import com.hihasan.notes.utils.base.BaseViewModel

class NoteViewModel(private val noteOnlineNoteRepositoryImpl: OnlineNoteRepositoryImpl,
                    private val noteOfflineNoteRepositoryImpl: OfflineNoteRepositoryImpl) : BaseViewModel(context = App.getAppContext()) {
}