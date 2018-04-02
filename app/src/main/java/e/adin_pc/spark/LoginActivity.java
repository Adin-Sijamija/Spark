package e.adin_pc.spark;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText UserName,Password;
    Button LogIn;
    TextView Register,Skip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserName=findViewById(R.id.Username_EditText);
        Password=findViewById(R.id.Password_EditText);
        LogIn=findViewById(R.id.LogIn_Button);
        Register=findViewById(R.id.Register_TextView);
        Skip=findViewById(R.id.Skip_TextView);



        LogIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO check with volley and add innto sqlite local
                Toast.makeText(LoginActivity.this, "Loggin button pressed", Toast.LENGTH_SHORT).show();






            }
        });


        Register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Register.class);
                Toast.makeText(LoginActivity.this, "Register txt pressed", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }




}

