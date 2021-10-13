package com.mohammad.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohammad.todoapp.databinding.TodoRowBinding


class RVAdapter(private var todos: ArrayList<Todo>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: TodoRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TodoRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]

        holder.binding.apply {
            setStrikeThrough(todo)
            tvTodo.text = todo.title
            cbTodo.isChecked = todo.completed
            cbTodo.setOnClickListener {
                todo.completed = !todo.completed
                setStrikeThrough(todo)

            }
        }
    }

    private fun TodoRowBinding.setStrikeThrough(todo: Todo) {
        if (todo.completed)
            tvTodo.paintFlags = 16
        else
            tvTodo.paintFlags = 1283
    }

    override fun getItemCount(): Int = todos.size

    fun deleteCompleted() {
        todos.removeAll { todo -> todo.completed }
        notifyDataSetChanged()
    }

    fun add(text: String) {
        todos.add(Todo(text))
        notifyItemChanged(todos.size - 1)
    }
}


//For doing this you can use this
//
//title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//and for remove you can use this
//
//title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));


