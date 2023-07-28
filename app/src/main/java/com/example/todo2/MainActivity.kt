package com.example.todo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // User inputs
        var editText = findViewById<EditText>(R.id.editText)

        // Initialize the list
        var itemList = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this, R.layout.list_items, R.id.textView3, itemList)
        var listView: ListView = findViewById<ListView>(R.id.listView)

        // Get the buttons
        val addBtn = findViewById<Button>(R.id.add)
        val deleteBtn = findViewById<Button>(R.id.delete)
        val clearBtn = findViewById<Button>(R.id.clear)

        // Listen for when the user adds an item
        addBtn.setOnClickListener {

            // Get the users input from the editText
            itemList.add(editText.text.toString())

            println("Adding item"+ itemList)

            // Notify the adapter that there has been change
            listView.adapter = adapter
            adapter.notifyDataSetChanged()

            // Clear the edit text field
            editText.text.clear()
        }

        // Make a toast popup when an item is selected on the list
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this,
                "You Selected the item -> " + itemList.get(i),
                android.widget.Toast.LENGTH_SHORT).show()
        }


        // Listen for when the user deletes an item from the list
        deleteBtn.setOnClickListener {
            println("Deleting item")

            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) { if (position.get(item))
            {
                adapter.remove(itemList.get(item))
            }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        // Listen for when the user wants to clear the list
        clearBtn.setOnClickListener {
            itemList.clear()
            adapter.notifyDataSetChanged()
        }
    }
}