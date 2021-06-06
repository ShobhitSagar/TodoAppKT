package com.devss.todoappkt

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
// For this, add this in gradle module > id 'kotlin-android-extensions' <
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
        private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_todo,
                        parent,
                        false
                )
        )

    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title                        // Setting the textview text from data
            cbDone.isChecked = curTodo.isChecked                    // Setting the checkbox from data

            toggleStrikeThrough(tvTodoTitle, curTodo.isChecked)

            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)         // toggle the UI(Strike through)
                curTodo.isChecked = !curTodo.isChecked              // Update the data(true/false)
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}

















