package com.example.androidtrivia

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.androidtrivia.GameWonFragmentArgs
import com.example.androidtrivia.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).popBackStack()
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    private fun getSharedIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(requireActivity()).
        setText(getString(R.string.share_success_text,
        args.numCorrect, args.numQuestion)).setType("text/plain").intent
    }
    private fun shareSuccess() {
        startActivity(getSharedIntent())
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if(null == getSharedIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}