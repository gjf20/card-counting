package com.example.myfirstapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myfirstapp.data_model.Deck
import com.example.myfirstapp.databinding.FragmentFirstBinding
import android.widget.ImageView


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val deck: Deck = Deck(52)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonNextCard.setOnClickListener { showNextCard() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showNextCard() {
        val card = deck.drawCard()

        //create the image view with the card's image
        // initialising new layout
        val imageView = ImageView(this.context)

        // setting the image in the layout
        val res = resources.getIdentifier(card.imageUri, "drawable", "com.example.myfirstapp")
        System.out.println("The image resource ID is $res")
        imageView.setImageResource(res)

        // insert into main fragment view
        binding.firstFragConstraintLayout.addView(imageView,100, 100)
        imageView.bringToFront()
        imageView.setBackgroundColor(Color.MAGENTA)
    }

}