package com.example.chrislagos.userlogin;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class MainActivity extends Activity  {

    private EditText usernameField,passwordField;
    private ToggleButton createUser;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);

        button1 = (Button)findViewById(R.id.button1);
        createUser = (ToggleButton)findViewById(R.id.createUser);

        createUser.setText("Existing User");
        button1.setText("Log In");

    }

    public void toggleCreateUserLable(View view){
        if(createUser.isChecked()){
            createUser.setText("New User");
            button1.setText("Create User");
        }
        else{
            createUser.setText("Existing User");
            button1.setText("Log In");
        }

    }
    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if(createUser.isChecked()){
            new LoginActivity(this).execute(username,password);
        }
        else{
            new SigninActivity(this).execute(username,password);
        }
    }
}
