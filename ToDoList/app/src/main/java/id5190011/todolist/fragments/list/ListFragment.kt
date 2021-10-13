package id5190011.todolist.fragments.list

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id5190011.todolist.R

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val button = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val listLayout = view.findViewById<View>(R.id.listLayout)
        listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        return view
    }
}