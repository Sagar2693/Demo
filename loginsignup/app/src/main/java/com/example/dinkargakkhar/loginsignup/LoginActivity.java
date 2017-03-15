package com.example.dinkargakkhar.loginsignup;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class    LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    EditText id,passwordText;
    Button loginButton;
    TextView signupLink;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        final DBAdapter db = new DBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button)findViewById(R.id.btn_login) ;
        id = (EditText)findViewById(R.id.input_id);
        passwordText =(EditText)findViewById(R.id.input_password);
        signupLink = (TextView)findViewById(R.id.link_signup);
        //  ButterKnife.inject(this);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        } else {




            DBAdapter db = new DBAdapter(this);
            String Id = id.getText().toString();
            String password = passwordText.getText().toString();
            db.open();
            Cursor cursor = db.getUser(Id, password);
            if (cursor.moveToFirst()) {
                String c1 = cursor.getString(cursor.getColumnIndex("centennialid"));
                String c2 = cursor.getString(cursor.getColumnIndex("password"));

                if (c1 == Id) {
                    if (c2 == password) {
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                        //add intent here to go to next activity
                    } else
                        Toast.makeText(LoginActivity.this, "Wrong Id/Password", Toast.LENGTH_LONG).show();


                    db.close();
                }
            }


                    // TODO: Implement your own authentication logic here.






            }
        }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }



    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = id.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty()) {
            id.setError("enter your centennial id");
            valid = false;
        } else {
            id.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
