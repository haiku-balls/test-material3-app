package com.example.test_material3_app

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.example.test_material3_app.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

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

        // Haiku's Code
        val firstActProgress = binding.firstProgress

        firstActProgress.setOnClickListener {
            Snackbar.make(view, "You found the secret!", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
            firstActProgress.isIndeterminate = true
        }

        val videoPlayer = binding.videoView
        val vidUri = Uri.parse("android.resource://com.example.test_material3_app/raw/nfl")
        var videoPauseState = false
        var length = videoPlayer.currentPosition
        videoPlayer.setVideoURI(vidUri)
        videoPlayer.start()

        videoPlayer.setOnClickListener {
            Log.v("TestMaterial3", "[DEBUG] Pause state: $videoPauseState")
            if (videoPauseState == false) {
                videoPlayer.pause()
                videoPauseState = true
            } else {
                videoPlayer.seekTo(length)
                videoPlayer.start()
                videoPauseState = false
            }
        }
        //
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}