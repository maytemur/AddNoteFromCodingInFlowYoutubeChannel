package com.maytemur.notecodingflow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast

class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var numberPickerPriority: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPickerPriority = findViewById(R.id.number_picker_priority)

        numberPickerPriority.minValue = 1
        numberPickerPriority.maxValue = 10

        //sol üst köşedeki çarpı iptal için
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        when (intent.hasExtra(EXTRA_ID)) {
            true -> {
                setTitle("Edit Note")
                editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE))
                editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
                numberPickerPriority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
            }
            else -> {
                setTitle("Add Note")
            }
        }
    }

    private fun saveNote() {
        val title = editTextTitle.text.toString()
        val description = editTextDescription.text.toString()
        val priorityValue = numberPickerPriority.value
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent()
        intent.putExtra(EXTRA_TITLE, title)
        intent.putExtra(EXTRA_DESCRIPTION, description)
        intent.putExtra(EXTRA_PRIORITY, priorityValue)

        val id = getIntent().getIntExtra(EXTRA_ID, -1)
        if (id != -1) intent.putExtra(EXTRA_ID, id)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}