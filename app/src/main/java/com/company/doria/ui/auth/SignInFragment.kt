package com.company.doria.ui.auth

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.company.doria.R
import com.company.doria.api.Status
import com.company.doria.api.auth.LoginRequest
import com.company.doria.api.auth.LoginResponse
import com.company.doria.ui.BaseActivity
import com.company.doria.ui.MainActivity
import com.company.doria.utilities.DoriaConstants
import com.company.doria.utilities.preferences.PreferenceHelper.set
import com.company.doria.viewmodels.CustomViewModelFactory
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.sign_in_fragment.*
import timber.log.Timber
import javax.inject.Inject

class SignInFragment : DaggerFragment() {

    private lateinit var preferences: SharedPreferences
    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
        preferences = (activity as BaseActivity).preferences
        preferences[DoriaConstants.ACCESS_TOKEN] = "placeholder"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        btn_login.setOnClickListener {
            val email = ti_email.editText?.text.toString()
            val password = ti_password.editText?.text.toString()

            val usernameIsValid = validateUsername(email)
            val passwordIsValid = validatePassword(password)

            if (usernameIsValid && passwordIsValid) {
                viewModel.login(LoginRequest(email, password))
                    .observe(viewLifecycleOwner, Observer { resource ->
                        when (resource.status) {
                            Status.LOADING -> {
                                btn_login.showProgress {
                                    buttonTextRes = R.string.signing_in
                                    progressColor = Color.WHITE
                                }
                                Timber.d("loading")
                            }
                            Status.ERROR -> {
                                Timber.d("error: ${resource?.message!!}")
                                btn_login.hideProgress(R.string.sign_in)
                                (activity as BaseActivity).showSnackBar(
                                    resource.message,
                                    Snackbar.LENGTH_LONG
                                )
                            }
                            else -> {
                                Timber.d("success Created Company: ${resource.data}")
                                btn_login.hideProgress(R.string.sign_in)

                                loginUser(resource.data!!)
                            }
                        }
                    })
            }
        }
    }

    private fun validateUsername(username: String): Boolean {
        if (username.isNotEmpty()) {
            ti_email.error = null
            return true
        }
        ti_email.error = getString(R.string.invalid_email_or_password)
        return false
    }

    private fun validatePassword(password: String): Boolean {
        if (password.isNotEmpty()) {
            ti_email.error = null
            return true
        }
        ti_password.error = getString(R.string.invalid_email_or_password)
        return false
    }

    private fun loginUser(data: LoginResponse) {
        preferences[DoriaConstants.ACCESS_TOKEN] = data.data.token

        (activity as MainActivity).downloadIncidentTypes()
//        (activity as MainActivity).downloadIncidents()

        goToConsignments()
    }

    private fun goToConsignments() {
        findNavController().navigate(R.id.action_signInFragment_to_incidentsFragment)
    }

}