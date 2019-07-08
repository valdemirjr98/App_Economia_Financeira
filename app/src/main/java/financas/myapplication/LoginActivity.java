package financas.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "SignInActivity";


    @BindView(R.id.email)
    EditText emailField;

    @BindView(R.id.password)
    EditText passwordField;

    @BindView(R.id.layout_email)
    TextInputLayout emailLayout;

    @BindView(R.id.layout_password)
    TextInputLayout passwordLayout;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
    }

    private boolean validateForm(String email, String password) {

        boolean resultValidate = true;

        emailLayout.setError(null);
        passwordLayout.setError(null);

        if (FormHelper.isEmpty(email)) {
            emailLayout.setError(getString(R.string.error_email_empty));
            resultValidate = false;
        } else if (!FormHelper.isEmailValid(email)) {
            emailLayout.setError(getString(R.string.error_email_invalid));
            resultValidate = false;
        }

        if (FormHelper.isEmpty(email)) {
            emailLayout.setError(getString(R.string.error_email_empty));
            resultValidate = false;
        } else if (!FormHelper.isEmailValid(email)) {
            emailLayout.setError(getString(R.string.error_email_invalid));
            resultValidate = false;
        }

        if (FormHelper.isEmpty(password)) {
            passwordLayout.setError(getString(R.string.error_password_empty));
            resultValidate = false;
        } else if (!FormHelper.isPasswordValid(password)) {
            passwordLayout.setError(getString(R.string.error_password_invalid));
            resultValidate = false;
        }

        return resultValidate;
    }


    private void signIn(String email, String password) {

        if (!validateForm(email, password)) {
            Log.v(TAG, "validateForm error");
            return;
        }

        showProgressDialog();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            hideProgressDialog();
                            showToast(getString(R.string.auth_failed));
                        }
                    }
                });
    }

    @OnClick(R.id.login_button)
    public void signInOnClick(View view) {
        Log.v(TAG, "signInOnClick execute");
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        signIn(email, password);
    }

    @OnClick(R.id.registrar_se_button)
    public void goToSignUpOnClick(View view) {
        Log.v(TAG, "goToSignUpOnClick execute");
        Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
        startActivity(intent);
    }



    }
