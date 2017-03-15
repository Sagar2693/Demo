package com.example.dinkargakkhar.loginsignup;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    EditText edId, edFname, edLname, edEmail, edPassword, edConfirmpass, edDob, edHobby, edProgram;
    RadioGroup rbProfession;
    Spinner spinDepartment, spinCampus;
    Button signupButton;
    RadioButton rbChosen;
    TextView loginLink;
    private Pattern pattern;
    private Matcher matcher;
    private static final String DATE_PATTERN =
            "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DBAdapter db = new DBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edId = (EditText) findViewById(R.id.edCentenniald);
        edFname = (EditText) findViewById(R.id.edFName);
        edLname = (EditText) findViewById(R.id.edLName);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPassword = (EditText) findViewById(R.id.edTextPassword);
        edConfirmpass = (EditText) findViewById(R.id.edTextCPassword);
        edDob = (EditText) findViewById(R.id.edDOB);
        edHobby = (EditText) findViewById(R.id.edTextHobby);
        edProgram = (EditText) findViewById(R.id.edProgram);
        spinDepartment = (Spinner) findViewById(R.id.spinDepartment);
        spinCampus = (Spinner) findViewById(R.id.spinCampus);
        rbProfession = (RadioGroup) findViewById(R.id.rgProfession);
        int selected = rbProfession.getCheckedRadioButtonId();
        rbChosen = (RadioButton) findViewById(selected);
        loginLink = (TextView) findViewById(R.id.link_login);
        signupButton = (Button) findViewById(R.id.btn_signup);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();

                signup();
                db.close();

            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

       if (!validate() /*&& !dateValidate(edDob.getText().toString())*/) {
            onSignupFailed();
            return;
        } else
        {
            signupButton.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                    R.style.AppTheme);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
            String id = ((EditText) findViewById(R.id.edCentenniald)).getText().toString();
            Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
            String fname = ((EditText) findViewById(R.id.edFName)).getText().toString();
            Toast.makeText(getApplicationContext(), fname, Toast.LENGTH_SHORT).show();
            String lname = ((EditText) findViewById(R.id.edLName)).getText().toString();
            Toast.makeText(getApplicationContext(), lname, Toast.LENGTH_SHORT).show();
            String email = ((EditText) findViewById(R.id.edEmail)).getText().toString();
            Toast.makeText(getApplicationContext(), email, Toast.LENGTH_SHORT).show();
            String password = ((EditText) findViewById(R.id.edTextPassword)).getText().toString();
            Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT).show();
            String confirmpass = ((EditText) findViewById(R.id.edTextCPassword)).getText().toString();
            Toast.makeText(getApplicationContext(), confirmpass, Toast.LENGTH_SHORT).show();
            String date = ((EditText) findViewById(R.id.edDOB)).getText().toString();
            Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
            String hobby = ((EditText) findViewById(R.id.edTextHobby)).getText().toString();
            Toast.makeText(getApplicationContext(), hobby, Toast.LENGTH_SHORT).show();
            String program = ((EditText) findViewById(R.id.edProgram)).getText().toString();
            Toast.makeText(getApplicationContext(), program, Toast.LENGTH_SHORT).show();
            String department = ((Spinner) findViewById(R.id.spinDepartment)).getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), department, Toast.LENGTH_SHORT).show();
            String campus = ((Spinner) findViewById(R.id.spinCampus)).getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), campus, Toast.LENGTH_SHORT).show();
            String profession = rbChosen.getText().toString();
            Toast.makeText(getApplicationContext(), profession, Toast.LENGTH_SHORT).show();


            DBAdapter db = new DBAdapter(this);
            db.open();
            boolean flag = db.insertUser((Integer.parseInt(id)), fname, lname, email, password, date, hobby, program, department, campus, profession);
            db.close();
            if (flag) {
                Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
            }

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
            // TODO: Implement your own signup logic here.

        }
    }
    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);

    }


    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String id = ((EditText) findViewById(R.id.edCentenniald)).getText().toString();
        String fname = ((EditText) findViewById(R.id.edFName)).getText().toString();
        String lname = ((EditText) findViewById(R.id.edLName)).getText().toString();
        String email = ((EditText) findViewById(R.id.edEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.edTextPassword)).getText().toString();
        String confirmpass = ((EditText) findViewById(R.id.edTextCPassword)).getText().toString();
        String date = ((EditText) findViewById(R.id.edDOB)).getText().toString();
        String hobby = ((EditText) findViewById(R.id.edTextHobby)).getText().toString();
        String program = ((EditText) findViewById(R.id.edProgram)).getText().toString();
        String department = ((Spinner) findViewById(R.id.spinDepartment)).getSelectedItem().toString();
        String campus = ((Spinner) findViewById(R.id.spinCampus)).getSelectedItem().toString();
        String profession = rbChosen.getText().toString();

        if (id.isEmpty() || id.length() < 9) {
            edId.setError("At least 9 digits");
            valid = false;
        } else {
            edId.setError(null);
        }


        if (fname.isEmpty() || fname.length() < 3) {
            edFname.setError("At least 3 characters");
            valid = false;
        } else {
            edFname.setError(null);
        }
        if (lname.isEmpty() || lname.length() < 3) {
            edLname.setError("At least 3 characters");
            valid = false;
        } else {
            edLname.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            edEmail.setError(null);
        }
        if (date.isEmpty()) {
            edDob.setError("Field can not be empty");
            valid = false;
        } else {
            edDob.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edPassword.setError("Password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edPassword.setError(null);
        }
        if (confirmpass.isEmpty() || !confirmpass.equals(password)) {
            edConfirmpass.setError("Password does not match");
            valid = false;
        } else {
            edConfirmpass.setError(null);
        }
        if (hobby.isEmpty()) {
            edHobby.setError("Enter your hobbies");
            valid = false;
        } else {
            edHobby.setError(null);
        }
        if (program.isEmpty()) {
            edProgram.setError("Enter your program ");
            valid = false;
        } else {
            edProgram.setError(null);
        }
        if (department.isEmpty() || department.equals("Select your department")) {
            Toast.makeText(RegisterActivity.this, "Please select your department", Toast.LENGTH_LONG).show();
            valid = false;
        }

        if (campus.isEmpty() || campus.equals("Select your campus")) {
            Toast.makeText(RegisterActivity.this, "Please select your campus", Toast.LENGTH_LONG).show();
            valid = false;
        }
        if (rbProfession.getCheckedRadioButtonId() == -1) {
            Toast.makeText(RegisterActivity.this, "Please select your profession", Toast.LENGTH_LONG).show();
            valid = false;
        }


        return valid;
    }
   /* public boolean dateValidate(final String date){
        return true;

        matcher = pattern.matcher(date);

        if(matcher.matches()){
            matcher.reset();

            if(matcher.find()){
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                }

                else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }

                else{
                    return true;
                }
            }

            else{
                return false;
            }
        }
        else{
            return false;
        }
    }*/
}


