package com.krishanshamod.simple_android_application_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.krishanshamod.simple_android_application_5.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    var db = FirebaseFirestore.getInstance()

    private var userEmail: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getButton.setOnClickListener {
            binding.apply {

                // Get the input from the user
                userEmail = email.text.toString()

                // get the user's data from the database
                db.collection("users").document(userEmail).get().addOnSuccessListener { document ->
                    if (document != null) {
                        // set the data to the textviews
                        firstNameEdit.text = document.data?.get("firstName").toString()
                        lastNameEdit.text = document.data?.get("lastName").toString()
                        ageEdit.text = document.data?.get("age").toString()
                    }
                }
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}