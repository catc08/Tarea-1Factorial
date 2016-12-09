package edu.galileo.android.factorial;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputNumber)
    EditText inputNumber;
    @Bind(R.id.btnCompute)
    Button btnCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCompute)
    public void handleClickCompute(){
        hideKeyboard();
        String dato=inputNumber.getText().toString().trim();
        if (!dato.isEmpty()){
            int valor=Integer.parseInt(dato);
            int resultado=factorial(valor);

            AlertDialog.Builder alerta=new AlertDialog.Builder(this);
            alerta.setTitle("Resultado del Factorial");
            alerta.setMessage(Integer.toString(resultado));
            alerta.setPositiveButton("OK",null);
            alerta.create();
            alerta.show();
        }
    }

    //Método de calculo del factorial
    private int factorial(int dato) {
        if (dato==0)
            return 1;
        else
            return dato*factorial(dato-1);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        try{
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch(NullPointerException npe){
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }


}
