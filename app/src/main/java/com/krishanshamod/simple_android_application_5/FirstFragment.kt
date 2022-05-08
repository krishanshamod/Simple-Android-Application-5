package com.krishanshamod.simple_android_application_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.krishanshamod.simple_android_application_5.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var userEmail: String = ""
    private var userFirstName: String = ""
    private var userLastName: String = ""
    private var userAge: String = ""

    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            binding.apply {

                // Get the input from the user
                userEmail = email.text.toString()
                userFirstName = firstName.text.toString()
                userLastName = lastName.text.toString()
                userAge = age.text.toString()

                if (userEmail.isEmpty() || userFirstName.isEmpty() || userLastName.isEmpty() || userAge.isEmpty()) {
                    // If the user did not enter anything, show an error message
                    errorView.setText("Please fill all details")
                } else {

                    // save the user details in the database
                    db.collection("users").document(userEmail).set(mapOf(
                        "email" to userEmail,
                        "firstName" to userFirstName,
                        "lastName" to userLastName,
                        "age" to userAge
                    ))
                }
            }
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}