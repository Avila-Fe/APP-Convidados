package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.model.GuestModel
import com.example.convidados.R
import com.example.convidados.constants.DataBaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.viewModel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var biding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(biding.root)

        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        biding.radioPresent.isChecked = true
        biding.buttonSave.setOnClickListener(this)

        observe()

        loadData()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = biding.editName.text.toString()
            val presence = biding.radioPresent.isChecked

            val guest = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }
            viewModel.save(guest)

        }
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            biding.editName.setText(it.name)
            if (it.presence) {
                biding.radioPresent.isChecked = true
            } else {
                biding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {
            if (it != "") {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

}