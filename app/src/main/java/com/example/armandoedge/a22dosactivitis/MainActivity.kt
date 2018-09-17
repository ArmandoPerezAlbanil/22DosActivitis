package com.example.armandoedge.a22dosactivitis

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    // EditText view for the message
    private var mMessageEditText: EditText? = null
    // TextView for the reply header
    private var mReplyHeadTextView: TextView? = null
    // TextView for the reply body
    private var mReplyTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializan  variables.
        mMessageEditText = findViewById<View>(R.id.editText_main) as EditText
        mReplyHeadTextView = findViewById<View>(R.id.text_header_reply) as TextView
        mReplyTextView = findViewById<View>(R.id.text_message_reply) as TextView
    }

    fun launchSecondActivity(view: View) {
        Log.d(LOG_TAG, "Boton precionado!")

        val intent= Intent(this,Main2Activity::class.java)
        val message = mMessageEditText!!.getText().toString()

        intent.putExtra(EXTRA_MESSAGE, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Test for the right intent reply
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == Activity.RESULT_OK) {
                val respuesta =data!!.getStringExtra(Main2Activity.EXTRA_REPLY)

                // Make the reply head visible.
                mReplyHeadTextView!!.setVisibility(View.VISIBLE)

                // Set the reply and make it visible.
                mReplyTextView!!.setText(respuesta)
                mReplyTextView!!.setVisibility(View.VISIBLE)
            }
        }
    }
    companion object {
        // clase llamada log tag
        private val LOG_TAG = MainActivity::class.java.simpleName
        // Unique tag required for the intent extra
        val EXTRA_MESSAGE = "com.example.android.dos_activities.extra.MESSAGE"
        // Unique tag for the intent reply
        val TEXT_REQUEST = 1
    }
}



