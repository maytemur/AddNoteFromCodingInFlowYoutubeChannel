package com.maytemur.notecodingflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Coding in Flow- Sqlite Android Tutorials playlist 7nci videoda kaldım- ActivityResult deprecated olduğundan
// yeni yöntemle devam edilebilir- menü not silinmesinde ve sağa sola swipe ile not silme var

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener {
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.button_add_note).setOnClickListener(this)

        val adapter = NoteAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


        //Get a new or existing ViewModel from the ViewModelProvider
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //Add an observer on the LiveData returned by getAllNotes
        //The onChanged() method fires when the observed data changes and the activity is in the foreground
        noteViewModel.allNotes.observe(this, Observer { notes ->
            // Update the cached copy of the words in the adapter.
            //Toast.makeText(this, "Değişti", Toast.LENGTH_SHORT).show();
            //notes?.let { adapter.submitList(notes) } //update RecyclerView
            adapter.setNotes(notes)
        })
    }
/*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == INTENT_REQUEST_ADD_NOTE && resultCode == RESULT_OK && data != null -> {
                val titleText = data.getStringExtra(EXTRA_TITLE) ?: ""
                val descriptionText = data.getStringExtra(EXTRA_DESCRIPTION) ?: ""
                val priorityValue = data.getIntExtra(EXTRA_PRIORITY, 1)

                val note = Note(titleText, descriptionText, priorityValue)
                noteViewModel.insert(note)

                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
            }
            requestCode == INTENT_REQUEST_EDIT_NOTE && resultCode == RESULT_OK && data != null -> {
                val id = data.getIntExtra(EXTRA_ID, -1)
                if(id == -1) {
                    Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show()
                    return
                }

                val title = data.getStringExtra(EXTRA_TITLE) ?: ""
                val description = data.getStringExtra(EXTRA_DESCRIPTION) ?: ""
                val priority = data.getIntExtra(EXTRA_PRIORITY, 1)
                noteViewModel.update(Note(title, description, priority, id))

                Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
            }
            else -> Toast.makeText(this, "Note Not Saved", Toast.LENGTH_SHORT).show()
        }
    }*/

    override fun onClick(v: View?) {
       /* val intent = Intent(this, AddEditNoteActivity::class.java)
        startActivityForResult(intent, INTENT_REQUEST_ADD_NOTE)*/
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        /*
        intent.putExtra(EXTRA_ID, note.id)
        intent.putExtra(EXTRA_TITLE, note.title)
        intent.putExtra(EXTRA_DESCRIPTION, note.description)
        intent.putExtra(EXTRA_PRIORITY, note.priority)
        //startActivityForResult(intent, INTENT_REQUEST_EDIT_NOTE)
        val intent = Intent(this, AddEditNoteActivity::class.java)
        */
    }
 /*   var resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            doSomeOperations()
        }
    }

    fun openSomeActivityForResult() {
        val intent = Intent(this, SomeActivity::class.java)
        resultLauncher.launch(intent)
    }*/
}