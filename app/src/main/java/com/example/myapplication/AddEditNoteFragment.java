package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.UUID;

public class AddEditNoteFragment extends Fragment {

    private static final String ARG_NOTE = "note";
    private Note note;

    public static AddEditNoteFragment newInstance(Note note) {
        AddEditNoteFragment fragment = new AddEditNoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_note, container, false);

        if (getArguments() != null) {
            note = (Note) getArguments().getSerializable(ARG_NOTE);
        }

        EditText noteEditText = view.findViewById(R.id.note_edittext);
        Button saveNoteButton = view.findViewById(R.id.save_note_button);

        if (note != null) {
            noteEditText.setText(note.getContent());
        }

        saveNoteButton.setOnClickListener(v -> {
            Log.d("AddEditNoteFragment", "Save button clicked");
            saveNoteAndReturn();
        });

        return view;
    }

    private void saveNoteAndReturn() {
        String noteContent = ((EditText) getView().findViewById(R.id.note_edittext)).getText().toString();
        Log.d("AddEditNoteFragment", "Saving note: " + noteContent);

        SQLiteHelper dbHelper = new SQLiteHelper(requireContext());

        try {
            if (note == null) {
                note = new Note();
                note.setId(UUID.randomUUID().toString());
                note.setContent(noteContent);
                dbHelper.addNote(note);
                Log.d("AddEditNoteFragment", "Note added");
            } else {
                note.setContent(noteContent);
                dbHelper.updateNote(note);
                Log.d("AddEditNoteFragment", "Note updated");
            }

            // Show a Toast message
            Toast.makeText(requireContext(), "Note saved successfully!", Toast.LENGTH_SHORT).show();

            // Notify the NotesFragment to refresh
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).onNoteUpdated();
                Log.d("AddEditNoteFragment", "MainActivity notified of note update");
            }

            // Navigate back to NotesFragment
            requireFragmentManager().popBackStack();
            Log.d("AddEditNoteFragment", "Returning to NotesFragment");

        } catch (Exception e) {
            Log.e("AddEditNoteFragment", "Error saving note", e);
            Toast.makeText(requireContext(), "Now Go Back", Toast.LENGTH_SHORT).show();
        }
    }
}