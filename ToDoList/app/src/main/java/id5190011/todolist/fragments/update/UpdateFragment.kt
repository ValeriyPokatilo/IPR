package id5190011.todolist.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import id5190011.todolist.R
import id5190011.todolist.data.models.Priority
import id5190011.todolist.fragments.ShareViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Set menu
        setHasOptionsMenu(true)

        val currentTitle = view.findViewById<TextView>(R.id.current_title_et)
        currentTitle.setText(args.currentItem.title)

        val currentDescription = view.findViewById<TextView>(R.id.current_description_et)
        currentDescription.setText(args.currentItem.description)

        val currentPriority = view.findViewById<Spinner>(R.id.current_priorities_spinner)
        currentPriority.setSelection(parsePriority(args.currentItem.priority))
        currentPriority.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority: Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}