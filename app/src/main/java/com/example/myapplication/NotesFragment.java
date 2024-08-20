package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    public interface OnNoteUpdateListener {
        void onNoteUpdated();
    }

    private OnNoteUpdateListener listener;
    private final List<Note> notesList = new ArrayList<>();
    private NotesAdapter notesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.notes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notesAdapter = new NotesAdapter(notesList, this::openEditNoteFragment);
        recyclerView.setAdapter(notesAdapter);

        view.findViewById(R.id.add_note_button).setOnClickListener(v -> openEditNoteFragment(null));

        loadNotes();

        return view;
    }

    private void loadNotes() {
        SQLiteHelper dbHelper = new SQLiteHelper(requireContext());
        List<Note> notes = dbHelper.getAllNotes();
        notesList.clear();
        notesList.addAll(notes);
        notesAdapter.notifyDataSetChanged();
    }

    private void openEditNoteFragment(Note note) {
        AddEditNoteFragment fragment = AddEditNoteFragment.newInstance(note);
        requireFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void updateNotes() {
        loadNotes();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteUpdateListener) {
            listener = (OnNoteUpdateListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnNoteUpdateListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
