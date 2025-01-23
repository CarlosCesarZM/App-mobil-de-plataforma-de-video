package com.example.a01081123_a21tecfilms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PasareladePagoActivity extends AppCompatActivity {


    PaymentSheet paymentSheet;
    String paymentIntentClientSecret;
    PaymentSheet.CustomerConfiguration configuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pasarelade_pago);
        fetchApi();
        Button boton = findViewById(R.id.pago);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paymentIntentClientSecret != null)
                    paymentSheet.presentWithPaymentIntent(paymentIntentClientSecret,
                            new PaymentSheet.Configuration("Code Easy", configuration));
                else
                    Toast.makeText(getApplicationContext(), "LOADING ...." , Toast.LENGTH_SHORT).show();
                }

        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        paymentSheet = new PaymentSheet(this, this::onPaymentSheetResult);
    }

    private void onPaymentSheetResult(final PaymentSheetResult paymentSheetResult){
        if (paymentSheetResult instanceof PaymentSheetResult.Canceled){
            Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
        }
        if (paymentSheetResult instanceof PaymentSheetResult.Failed){
            Toast.makeText(this,"Fallido", Toast.LENGTH_SHORT).show();
        }
        if (paymentSheetResult instanceof PaymentSheetResult.Completed){
            Toast.makeText(this,"Completado", Toast.LENGTH_SHORT).show();
        }
    }
    public void fetchApi(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.11/stripe-payment/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonobject = new JSONObject(response);
                            configuration = new PaymentSheet.CustomerConfiguration(
                                    jsonobject.getString("customer"),
                                    jsonobject.getString("ephemeralKey")
                            );
                            paymentIntentClientSecret = jsonobject.getString("paymentIntent");
                            PaymentConfiguration.init(getApplicationContext(), jsonobject.getString("publishableKey"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("authKey", "abc");
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}