package id5190011.todolist.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id5190011.todolist.R
import id5190011.todolist.data.models.Priority
import id5190011.todolist.data.models.ToDoData
import id5190011.todolist.data.viewmodel.ToDoViewModel
import id5190011.todolist.fragments.ShareViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: ShareViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

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
        currentPriority.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        currentPriority.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val currentTitle = view?.findViewById<TextView>(R.id.current_title_et)
        val title = currentTitle?.text.toString()

        val currentDescription = view?.findViewById<TextView>(R.id.current_description_et)
        val description = currentDescription?.text.toString()

        val currentPriority = view?.findViewById<Spinner>(R.id.current_priorities_spinner)
        val getPriority = currentPriority?.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if(validation){
            // Update current item
            val updateItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description
            )
            mToDoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successfully update!", Toast.LENGTH_SHORT).show()
            // Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please, fill out all fields!", Toast.LENGTH_SHORT).show()

        }
    }

    // Show alert dialog
    private fun confirmItemRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mToDoViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: '${args.currentItem.title}'",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_, _ ->}
        builder.setTitle("Delete ${args.currentItem.title}")
        builder.setMessage("Are you sure want to remove '${args.currentItem.title}'?")
        builder.create().show()
    }
}