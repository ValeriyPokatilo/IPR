package id5190011.todolist.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id5190011.todolist.R
import id5190011.todolist.data.models.Priority
import id5190011.todolist.data.models.ToDoData

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title_txt).text = dataList[position].title
        holder.itemView.findViewById<TextView>(R.id.description_txt).text = dataList[position].description

        when(dataList[position].priority){
            Priority.HIGH -> holder.itemView.findViewById<CardView>(R.id.priority_indicator).setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.red
            ))
            Priority.MEDIUM -> holder.itemView.findViewById<CardView>(R.id.priority_indicator).setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.yellow
            ))
            Priority.LOW -> holder.itemView.findViewById<CardView>(R.id.priority_indicator).setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.green
            ))
        }
    }

    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }
}