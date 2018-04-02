package e.adin_pc.spark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;





public class Register extends AppCompatActivity {

    EditText Username,Password,Email,Birthday;
    Button Register;
    String insertURL = "http://10.0.2.2/skripte/insert_user.php";
    RequestQueue requestQueue;
    private DatabaseHandler db;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username=findViewById(R.id.UserNameReg_EditText);
        Password=findViewById(R.id.PasswordReg_EditText);
        Email=findViewById(R.id.EmailReg_EditText);
        Birthday=findViewById(R.id.BirthdayReg_EditText);
        Register=findViewById(R.id.register_Button);

        db=new DatabaseHandler(this);

         requestQueue = Volley.newRequestQueue(getApplicationContext());


        Register.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST,

                        insertURL, new Response.Listener<String>() {

                    @Override

                    public void onResponse(String response) {
                        Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();

                        //Make object
                        Users user=new Users();
                        user.setId(Integer.parseInt(response));
                       // user.setId(2);
                        user.setUserName(Username.toString());
                        user.setPassword(Password.toString());
                        user.setEmail(Email.toString());
                        user.setBirthday(Birthday.toString());
                        user.setIs_Admin(false);


                        //TODO WRITE INTO SLQLITE AND OPEN NEW ACTIVITY
                        boolean insert = db.InsertUser(user);

                        Log.i(TAG,"Uspjelo je valjda ",null);

                    }

                }, new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();




                    }

                }) {

                    @Override

                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> parameters = new HashMap<String, String>();

                        parameters.put("USERNAME", Username.getText().toString());

                        parameters.put("PASSWORD", Password.getText().toString());

                        parameters.put("EMAIL", Email.getText().toString());

                        parameters.put("BIRTHDAY", Birthday.getText().toString());



                        return parameters;

                    }

                };



                requestQueue.add(stringRequest);

            }

        });
    }
}
