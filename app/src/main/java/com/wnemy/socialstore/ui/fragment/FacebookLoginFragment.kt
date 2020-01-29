package com.wnemy.socialstore.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.wnemy.socialstore.R
import kotlinx.android.synthetic.main.fragment_facebook_login.*


class FacebookLoginFragment : Fragment() {

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_facebook_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(login_button) {
            setPermissions("email", "instagram_basic", "pages_show_list")
            fragment = this@FacebookLoginFragment
            registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { // App code
                    Toast.makeText(
                        requireContext(),
                        loginResult!!.accessToken.userId,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onCancel() { // App code
                    Toast.makeText(requireContext(), "Canceled", Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: FacebookException) { // App code
                    Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
                }
            })
        }


    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}