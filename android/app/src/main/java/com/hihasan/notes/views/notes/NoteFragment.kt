package com.hihasan.notes.views.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hihasan.notes.databinding.FragmentNoteListBinding
import com.hihasan.notes.utils.base.BaseFragment

class NoteFragment : BaseFragment() {
    private lateinit var binding : FragmentNoteListBinding
    private lateinit var viewModel : NoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}