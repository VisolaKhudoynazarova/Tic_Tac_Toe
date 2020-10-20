package idrok.visola_khudoynazarova.tictactoe.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import idrok.visola_khudoynazarova.tictactoe.R
import idrok.visola_khudoynazarova.tictactoe.databinding.FragmentSplashBinding
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

@Suppress("UNREACHABLE_CODE")
class SplashFragment : Fragment() {

    private lateinit var timer: Timer

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSplashBinding.inflate(inflater, container, false)
        requireActivity().app_bar.visibility = View.GONE
        return binding.root


    }

    fun waitAndOpenOtherFragment() {

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment2)

            }

        }, 2500)
    }

    override fun onResume() {
        super.onResume()
        waitAndOpenOtherFragment()
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}