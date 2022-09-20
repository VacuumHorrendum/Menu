package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickEmail(View view) {
        Intent intent = new Intent(this, CallActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if(menu.getClass().getSimpleName()
                .equals("MenuBuilder")){
            try{
                Method m = menu.getClass()
                        .getDeclaredMethod (
                                "setOptionalIconsVisible",
                                Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            }
            catch(NoSuchMethodException e){
                System.err.println("onCreateOptionsMenu");
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        String message = "";
        Intent intent = new Intent(this, CallActivity.class);
        switch (item.getItemId()) {
            case R.id.shop:
                message = "Shop clicked";
                intent = new Intent(this, ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.call:
                message = "Call clicked";
                intent = new Intent(this, CallActivity.class);
                startActivity(intent);
                break;
            case R.id.camera:
                item.setChecked(true);
                message = "Camera clicked";
                break;
            case R.id.video:
                item.setChecked(true);
                message = "Video clicked";
                break;
            case R.id.email:
                message = "Email clicked";
                intent = new Intent(this, EmailActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                message = "Add clicked";
                break;
            case R.id.copy:
                message = "Copy clicked";
                break;
            case R.id.paste:
                message = "Paste clicked";
                break;
            case R.id.help:
                message = "Help clicked";
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
            default:
                super.onOptionsItemSelected(item);
        }
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        return true;
    }
}