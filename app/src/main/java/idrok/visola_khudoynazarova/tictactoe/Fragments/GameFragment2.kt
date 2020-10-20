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
import idrok.visola_khudoynazarova.tictactoe.databinding.FragmentGame2Binding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_game2.*

class GameFragment2 : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentGame2Binding
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
        binding = FragmentGame2Binding.inflate(inflater, container, false)

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
            this@GameFragment2.viewModel.getPlayer1().observe(viewLifecycleOwner, { player1 ->
                tvPlayerX.text = "PlayerX: $player1"
            })
            this@GameFragment2.viewModel.getPlayer2().observe(viewLifecycleOwner, { player2 ->
                tvPlayerO.text = "PlayerO: $player2"
            })
        }

        addButtons()
        getList()
        setOnClick()

        binding.apply {

            btnNewGame2.setOnClickListener {
                clearPanel(false)
            }

            btnExit2.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        return binding.root

    }

    private fun getList() {
        Log.d("GameFragment2", "getList: ${viewModel.getListText()}")
        if (!viewModel.getListText().isEmpty()) {
            listString = viewModel.getListText()
            setSavedText()
        } else (0..3).forEach { position ->
            listString.add(arrayListOf("", "", "",""))
        }
    }

    private fun setSavedText() {
        (0..3).forEach { i ->
            (0..3).forEach { j ->
                listButtons[i][j].text = listString[i][j]
            }
        }
    }

    private fun setOnClick() {
        (0..3).forEach { positionX ->
            (0..3).forEach { positionY ->
                listButtons[positionX][positionY].setOnClickListener(this)
            }
        }
    }

    private fun addButtons() {

        binding.apply {

            listButtons.add(arrayListOf(tv200, tv201, tv202, tv203))
            listButtons.add(arrayListOf(tv210, tv211, tv212, tv213))
            listButtons.add(arrayListOf(tv220, tv221, tv222, tv223))
            listButtons.add(arrayListOf(tv230, tv231, tv232, tv233))

        }
    }

    override fun onClick(view: View?) {

        binding.apply {

            when (view) {
                tv_200 -> {
                    setText(tv_200, 0, 0)
                }
                tv_201 -> {
                    setText(tv_201, 0, 1)
                }
                tv_202 -> {
                    setText(tv_202, 0, 2)
                }
                tv_203 -> {
                    setText(tv_203, 0, 3)
                }
                tv_210 -> {
                    setText(tv_210, 1, 0)
                }
                tv_211 -> {
                    setText(tv_211, 1, 1)
                }
                tv_212 -> {
                    setText(tv_212, 1, 2)
                }
                tv_213 -> {
                    setText(tv_213, 1, 3)
                }
                tv_220 -> {
                    setText(tv_220, 2, 0)
                }
                tv_221 -> {
                    setText(tv_221, 2, 1)
                }
                tv_222 -> {
                    setText(tv_222, 2, 2)
                }
                tv_223 -> {
                    setText(tv_223, 2, 3)
                }
                tv_230 -> {
                    setText(tv_230, 3, 0)
                }
                tv_231 -> {
                    setText(tv_231, 3, 1)
                }
                tv_232 -> {
                    setText(tv_232, 3, 2)
                }
                tv_233 -> {
                    setText(tv_233, 3, 3)
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
//////////////////////////////////////////////////////////////////////////////

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
        /////////////////////////////////////////////////////////////
        (0..2).forEach { positionX ->
            (0..2).forEach { positionY ->
                val text = listButtons[positionX][positionY]

                if (positionX + positionY == 2) {

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


        /////////////////////////////////////////////////////

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

        ///////////////////////////////////  2 - massiv  ////////////////////////////////////////


        (0..2).forEach { positionX ->
            (1..3).forEach { positionY ->
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

//////////////////////////////////////////////////////////////////////////////////

        (0..2).forEach { positionX ->
            (1..3).forEach { positionY ->
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


////////////////////////////////////////////////////////

        (0..2).forEach { positionX ->
            (1..3).forEach { positionY ->
                val text = listButtons[positionX][positionY]

                if (positionX + 1 == positionY) {

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

//////////////////////////////////////////////////

        (0..2).forEach { positionX ->
            (1..3).forEach { positionY ->
                val text = listButtons[positionX][positionY]

                if (positionX + positionY == 3) {

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

        ////////////////////////  3 - massiv  ///////////////////////////////////


        (1..3).forEach { positionX ->
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

//////////////////////////////////////////////////////////////////////////////////

        (1..3).forEach { positionX ->
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


////////////////////////////////////////////////////////

        (1..3).forEach { positionX ->
            (0..2).forEach { positionY ->
                val text = listButtons[positionX][positionY]

                if (positionX - 1 == positionY) {

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
//////////////////////////////////////////////////

        (1..3).forEach { positionX ->
            (0..2).forEach { positionY ->
                val text = listButtons[positionX][positionY]

                if (positionX + positionY == 3) {

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

        ////////////////////////  4 - massiv  ///////////////////////////////////


        (1..3).forEach { positionX ->
            (1..3).forEach { positionY ->
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

//////////////////////////////////////////////////////////////////////////////////

        (1..3).forEach { positionX ->
            (1..3).forEach { positionY ->
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


////////////////////////////////////////////////////////

        (1..3).forEach { position ->
            val text = listButtons[position][position]

            if (position == position) {

                if (text.text == "X") {
                    schet1++
                } else if (text.text == "O") {
                    schet2++
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
//////////////////////////////////////////////////

        (1..3).forEach { positionX ->
            (1..3).forEach { positionY ->
                val text = listButtons[positionX][positionY]

                if (positionX + positionY == 4) {

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

        (0..3).forEach { position1 ->
            (0..3).forEach { position2 ->
                if (listButtons[position1][position2].text.isNotEmpty()) {
                    counter++
                }
            }
        }
        if (counter == 16) {
            clearPanel(false)
        } else {
            counter = 0
        }


    }

    @SuppressLint("SetTextI18n")
    private fun clearPanel(isSomeoneWon: Boolean) {
        isPlayer = true
        if (!isSomeoneWon) {
            (0..3).forEach { positionX ->
                (0..3).forEach { positionY ->
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

            tvPlayerX.text = "PlayerX = $playerX"
            tvPlayerO.text = "PlayerO = $playerO"
            if (playerX > playerO) {
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
            } else {
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
}