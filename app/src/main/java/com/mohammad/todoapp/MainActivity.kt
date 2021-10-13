package com.mohammad.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var rvAdapter: RVAdapter
    lateinit var rvTodos: RecyclerView
    lateinit var btAddTodo: FloatingActionButton
    lateinit var todos: ArrayList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodos = findViewById(R.id.rvTodos)
        btAddTodo = findViewById(R.id.btAddTodo)
        todos = arrayListOf(
            Todo("Todo 1"),
            Todo("Todo 2"),
            Todo("Todo 3", true),
            Todo("Todo 4")
        )
        rvAdapter = RVAdapter(todos)
        rvTodos.adapter = rvAdapter
        rvTodos.layoutManager = LinearLayoutManager(this)

        btAddTodo.setOnClickListener {
            addTodo()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteTodos -> {
                rvAdapter.deleteCompleted()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun addTodo() {
        val editText = EditText(this)
        editText.hint = "Enter Todo"
        AlertDialog.Builder(this)
            .setTitle("Add Todo")
            .setMessage("Enter Todo")
            .setView(editText)
            .setPositiveButton("Add") { dialog, _ ->
                run {
                    rvAdapter.add(editText.text.toString())
                    dialog.dismiss()
                }
            }.show()
    }
}