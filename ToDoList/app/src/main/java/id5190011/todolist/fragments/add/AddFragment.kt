package id5190011.todolist.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id5190011.todolist.R
import id5190011.todolist.data.models.Priority
import id5190011.todolist.data.models.ToDoData
import id5190011.todolist.data.viewmodel.ToDoViewModel
import id5190011.todolist.fragments.ShareViewModel

class AddFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mShareViewModel: ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        // Set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val titleView = view?.findViewById<EditText>(R.id.title_et)
        val priorityView = view?.findViewById<Spinner>(R.id.priorities_spinner)
        val descriptionView = view?.findViewById<EditText>(R.id.description_et)

        val mTitle = titleView?.text.toString()
        val mProirity = priorityView?.selectedItem.toString()
        val mDescription = descriptionView?.text.toString()

        val validation = mShareViewModel.verifyDataFromUser(mTitle, mDescription)
        if(validation){
            // Insert data to database
            val newData = ToDoData(
                id = 0,
                mTitle,
                mShareViewModel.parsePriority(mProirity),
                mDescription
            )
            mToDoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()

            // Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}