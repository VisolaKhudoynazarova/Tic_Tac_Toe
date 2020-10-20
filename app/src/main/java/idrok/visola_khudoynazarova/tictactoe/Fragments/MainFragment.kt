package idrok.visola_khudoynazarova.tictactoe.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import idrok.visola_khudoynazarova.tictactoe.GameViewModel
import idrok.visola_khudoynazarova.tictactoe.R
import idrok.visola_khudoynazarova.tictactoe.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

@Suppress("UNREACHABLE_CODE")
class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(requireActivity()).get(GameViewModel::class.java)
        viewModel.setListText(arrayListOf())
        return binding.root

    }

    override fun onResume() {

        super.onResume()

        requireActivity().apply {
            app_bar.visibility = View.VISIBLE
            toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)
            toolbar.setNavigationOnClickListener {
                requireActivity().drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        binding.btnPlayGame.setOnClickListener {
           findNavController().navigate(R.id.gameFragment)
        }

        binding.btnPlayGame2.setOnClickListener {
            findNavController().navigate(R.id.gameFragment2)
        }
    }


}