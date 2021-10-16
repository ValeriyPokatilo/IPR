package id5190011.todolist.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
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
        holder.itemView.findViewById<View>(R.id.row_background).setOnClickListener {
            // Переход на экран редактирования Задачи
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        //val pr = dataList[position].priority
        // ***
        val cv = holder.itemView.findViewById<CardView>(R.id.priority_indicator)
        when(dataList[position].priority){
            Priority.HIGH -> cv.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.red
            ))
            Priority.MEDIUM -> cv.setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.yellow
            ))
            Priority.LOW -> cv.setCardBackgroundColor(ContextCompat.getColor(
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