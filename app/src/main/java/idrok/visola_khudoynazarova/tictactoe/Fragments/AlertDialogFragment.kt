package idrok.visola_khudoynazarova.tictactoe.Fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import idrok.visola_khudoynazarova.tictactoe.R

class AlertDialogFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog)
    }

    fun showSimpleDialog(view: View) {

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("")


    }
}