package financas.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;

public class RegistrarActivity extends BaseActivity {


    @BindView(R.id.name)
    EditText nameEditText;

    @BindView(R.id.email)
    EditText emailEditText;

    @BindView(R.id.password)
    EditText passowordEditText;

    @BindView(R.id.confirm_password)
    EditText confirm_passwordEditText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

    }
}
