package com.designsrich.mapapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    public class MyJavaScriptInterface {
        Context mContext;

        MyJavaScriptInterface(Context c) {
            mContext = c;
        }

        public void showToast(String toast){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        public void openAndroidDialog(String msg){
                AlertDialog.Builder myDialog
                        = new AlertDialog.Builder(MainActivity.this);
                myDialog.setTitle("Text From JavaScript");
                myDialog.setMessage(msg);
                myDialog.setPositiveButton("OK", null);
                myDialog.show();
        }
    }

    WebView webView;
    EditText editText;
    EditText editTex2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //webview initialization
        webView = (WebView)findViewById(R.id.webView);
        final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(this);
        webView.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("http://designsrich.com");

        //sent data from android to javascript
        editText = (EditText)findViewById(R.id.editText);
        editTex2 = (EditText)findViewById( R.id.editText3);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                String msgToSend = editText.getText().toString();
                String msgToSend2 = editTex2.getText().toString();
                webView.loadUrl("javascript:map.marker1([" + msgToSend+","+msgToSend2+ "])");
                System.out.println(msgToSend);

            }
        });
    }
}
