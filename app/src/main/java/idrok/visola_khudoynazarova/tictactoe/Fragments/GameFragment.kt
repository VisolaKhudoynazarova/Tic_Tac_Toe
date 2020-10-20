package idrok.visola_khudoynazarova.tictactoe.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import idrok.visola_khudoynazarova.tictactoe.GameViewModel
import idrok.visola_khudoynazarova.tictactoe.R
import idrok.visola_khudoynazarova.tictactoe.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class GameFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentGameBinding
    private val listButtons = arrayListOf<ArrayList<TextView>>()
    private lateinit var viewModel: GameViewModel
    private var playerX = 0
    private var playerO = 0
    private var isPlayer = true
    private var listString = arrayListOf<ArrayList<String>>()


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        requireActivity().toolbar.setNavigationOnClickListener {
            requireActivity().drawer_layout.openDrawer(GravityCompat.START)
        }

        viewModel = ViewModelProviders.of(requireActivity()).get(GameViewModel::class.java)
        if (viewModel.getPlayer1().value == null)
            viewModel.setPlayer1(0)
        if (viewModel.getPlayer2().value == null)
            viewModel.setPlayer2(0)
        if (viewModel.getPlayer1().value != null)
            playerX = viewModel.getPlayer1().value!!
        if (viewModel.getPlayer2().value != null)
            playerO = viewModel.getPlayer2().value!!

        binding.apply {
            this@GameFragment.viewModel.getPlayer1().observe(viewLifecycleOwner, { player1 ->
                tvPlayer1.text = "Player1: $player1"
            })
            this@GameFragment.viewModel.getPlayer2().observe(viewLifecycleOwner, { player2 ->
                tvPlayer2.text = "Player2: $player2"
            })
        }
        addButtons()
        getList()
        setOnClick()

        binding.apply {

            btnNewGame.setOnClickListener {
                clearPanel(false)
            }

            btnExit.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        return binding.root


    }

    private fun getList() {


        Log.d("GameFragment", "getList: ${viewModel.getListText()}")
        if (!viewModel.getListText().isEmpty()) {
            listString = viewModel.getListText()
            setSavedText()
        } else (0..2).forEach { position ->
            listString.add(arrayListOf("", "", ""))
        }
    }

    private fun setSavedText() {
        (0..2).forEach { i ->
            (0..2).forEach { j ->
                listButtons[i][j].text = listString[i][j]
            }
        }
    }

    private fun setOnClick() {
        (0..2).forEach { positionX ->
            (0..2).forEach { positionY ->
                listButtons[positionX][positionY].setOnClickListener(this)
            }
        }
    }

    private fun addButtons() {
        binding.apply {

            listButtons.add(arrayListOf(tv00, tv01, tv02))
            listButtons.add(arrayListOf(tv10, tv11, tv12))
            listButtons.add(arrayListOf(tv20, tv21, tv22))

        }

    }

    override fun onClick(view: View?) {

        binding.apply {

            when (view) {
                tv00 -> {
                    setText(tv00, 0, 0)
                }

                tv01 -> {
                    setText(tv01, 0, 1)
                }

                tv02 -> {
                    setText(tv02, 0, 2)
                }

                tv10 -> {
                    setText(tv10, 1, 0)
                }

                tv11 -> {
                    setText(tv11, 1, 1)
                }

                tv12 -> {
                    setText(tv12, 1, 2)
                }

                tv20 -> {
                    setText(tv20, 2, 0)
                }

                tv21 -> {
                    setText(tv21, 2, 1)
                }

                tv22 -> {
                    setText(tv22, 2, 2)
                }

            }

        }

    }

    private fun setText(text: TextView, i: Int, j: Int) {
        if (text.text.isEmpty()) {

            if (isPlayer) {
                text.text = "X"
                listString[i][j] = "X"
                viewModel.setListText(listString)
                isPlayer = false
                checkWin()
            } else {
                text.text = "O"
                listString[i][j] = "O"
                viewModel.setListText(listString)
                isPlayer = true
                checkWin()
            }
        }
    }

    private fun checkWin() {

        var schet1 = 0
        var schet2 = 0
        var counter = 0

        (0..2).forEach { positionX ->
            (0..2).forEach { positionY ->
                val text = listButtons[positionX][positionY]
                if (text.text == "X") {
                    schet1++

                } else if (text.text == "O") {
                    schet2++

                }
            }

            when {

                schet1 == 3 -> {
                    playerX++
                    clearPanel(true)
                    return
                }
                schet2 == 3 -> {
                    playerO++
                    clearPanel(true)
                    return
                }
                else -> {
                    schet1 = 0
                    schet2 = 0
                }
            }
        }

        (0..2).forEach { positionX ->
            (0..2).forEach { positionY ->
                val text = listButtons[positionY][positionX]
                if (text.text == "X") {
                    schet1++

                } else if (text.text == "O") {
                    schet2++

                }
            }

            when {

                schet1 == 3 -> {
                    playerX++
                    clearPanel(true)
                    return
                }
                schet2 == 3 -> {
                    playerO++
                    clearPanel(true)
                    return
                }
                else -> {
                    schet1 = 0
                    schet2 = 0
                }
            }
        }
//////////////////////////////////////////////////////////////////////////////
        (0..2).forEach { position ->
            val text = listButtons[position][position]
            if (text.text == "X") {
                schet1++

            } else if (text.text == "O") {
                schet2++

            }
        }

        when {

            schet1 == 3 -> {
                playerX++
                clearPanel(true)
                return
            }
            schet2 == 3 -> {
                playerO++
                clearPanel(true)
                return
            }
            else -> {
                schet1 = 0
                schet2 = 0
            }
        }

        ////////////////////////////////////////////////////////////

        (0..2).forEach { positionX ->
            (0..2).forEach { positionY ->
                if (positionX + positionY == 2) {
                    val text = listButtons[positionX][positionY]
                    if (text.text == "X") {
                        schet1++

                    } else if (text.text == "O") {
                        schet2++

                    }
                }
            }
        }

        when {

            schet1 == 3 -> {
                playerX++
                clearPanel(true)
                return
            }
            schet2 == 3 -> {
                playerO++
                clearPanel(true)
                return
            }

            else -> {
                schet1 = 0
                schet2 = 0
            }
        }

        (0..2).forEach { positionX ->
            (0..2).forEach { positionO ->
                if (listButtons[positionX][positionO].text.isNotEmpty()) {
                    counter++
                }
            }
        }
        if (counter == 9) {
            clearPanel(false)
        } else {
            counter = 0
        }


    }

    @SuppressLint("SetTextI18n")
    private fun clearPanel(isSomeoneWon: Boolean) {
        isPlayer = true
        if (!isSomeoneWon) {
            (0..2).forEach { positionX ->
                (0..2).forEach { positionY ->
                    listButtons[positionX][positionY].text = ""
                    listString[positionX][positionY]
                }

            }


        } else {
            (0..2).forEach { positionX ->
                (0..2).forEach { positionY ->
                    listButtons[positionX][positionY].text = ""
                }

            }
        }

        binding.apply {

            tvPlayer1.text = "PlayerX = $playerX"
            tvPlayer2.text = "PlayerO = $playerO"

        }

        if(playerX > playerO){
            val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                Toast.makeText(
                    context,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }

            val builder = AlertDialog.Builder(context)
            with(builder) {
                builder.setTitle("PlayerX = $playerX")
                builder.setMessage("************")
                builder.setView(R.layout.dialog)
                builder.setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener(positiveButtonClick)
                )

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
//                Toast.makeText(applicationContext,
//                    android.R.string.yes, Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }else{
            val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                Toast.makeText(
                    context,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }

            val builder = AlertDialog.Builder(context)
            with(builder) {
                builder.setTitle("PlayerO = $playerO")
                builder.setMessage("************")
                builder.setView(R.layout.dialog)
                builder.setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener(positiveButtonClick)
                )

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
//                Toast.makeText(applicationContext,
//                    android.R.string.yes, Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }

    }

}