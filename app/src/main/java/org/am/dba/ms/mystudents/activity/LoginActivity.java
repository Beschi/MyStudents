package org.am.dba.ms.mystudents.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.am.dba.ms.mystudents.R;
import org.am.dba.ms.mystudents.service.LoginAPI;
import org.am.dba.ms.mystudents.util.ServiceBuilder;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_SIGNUP){
            if(resultCode == RESULT_OK){
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    private void login(){
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String user = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        //TODO implement login logic
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginAPI lapi = ServiceBuilder.createService(LoginAPI.class);
                Call<String> client = lapi.getUserValidated(user, password);
                String resp = null;
                client.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()) {
                            if (user.equals(response.body())) {
                                onLoginSuccess();
                                progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                onLoginFailed();
                            }
                        }else{
                            progressDialog.dismiss();
                            onLoginFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Error","Login Failed");
                        progressDialog.dismiss();
                    }
                });
            }
        },5000);
    }

    private boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        ///if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        if (email.isEmpty() ) {
            _emailText.setError("enter a valid username");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    private void onLoginFailed(){
        Toast.makeText(getBaseContext(),"Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);

    }

    private void onLoginSuccess(){
        _loginButton.setEnabled(true);
        finish();
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
