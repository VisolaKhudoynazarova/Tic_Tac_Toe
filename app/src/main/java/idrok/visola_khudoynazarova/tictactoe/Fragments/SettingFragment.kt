package idrok.visola_khudoynazarova.tictactoe.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import idrok.visola_khudoynazarova.tictactoe.R
import idrok.visola_khudoynazarova.tictactoe.databinding.FragmentSettingBinding
import kotlinx.android.synthetic.main.content_main.*

class SettingFragment : Fragment() {

    private lateinit var binding:FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        requireActivity().toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        requireActivity().toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onDestroyView() {
        requireActivity().toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24)
        super.onDestroyView()
    }

}