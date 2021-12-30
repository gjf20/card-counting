package com.example.myfirstapp

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myfirstapp.data_model.Deck
import com.example.myfirstapp.databinding.FragmentFirstBinding
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var dialogBuilder : AlertDialog.Builder
    private lateinit var dialog : AlertDialog
    private lateinit var closeButton : Button

    private var _binding: FragmentFirstBinding? = null
    private val deck: Deck = Deck(1)

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
        binding.buttonShowCount.setOnClickListener { showCount() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showNextCard() {

        val card = deck.drawCard() ?: return

        //create the image view with the card's image
        // initialising new layout
        val imageView = ImageView(this.context)

        // setting the image in the layout
        val res = resources.getIdentifier(card.imageUri, "drawable", "com.example.myfirstapp")
        imageView.setImageResource(res)

        // insert into main fragment view
        val layoutParams = getLocationInFragment()
        binding.firstFragConstraintLayout.addView(imageView, layoutParams)
        imageView.bringToFront()
        imageView.setBackgroundColor(Color.MAGENTA)
    }

    private fun showCount() {
        //show the popup - inflate?
        dialogBuilder = AlertDialog.Builder(this.context)
        val countPopupView = layoutInflater.inflate(R.layout.popup, null)
        closeButton = countPopupView.findViewById(R.id.close_button)
        closeButton.setOnClickListener { dialog.dismiss() }

        //set the running count text
        var textView : TextView = countPopupView.findViewById(R.id.running_count)
        textView.text = deck.getCountString(deck.getRunningCount())

        //show the view as a dialog
        dialogBuilder.setView(countPopupView)
        dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun getPixels(dps: Int): Int { //TODO move this to common
        val scale = requireContext().resources.displayMetrics.density
        return (dps * scale + 0.5f).toInt()
    }

    private fun getLocationInFragment() : ViewGroup.LayoutParams {
        val viewWidth = 148
        val viewHeight = 215

        val layoutParams = ConstraintLayout.LayoutParams(getPixels(viewWidth), getPixels(viewHeight))
        layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        layoutParams.bottomToTop = binding.buttonNextCard.id
        return layoutParams
    }
}