package com.example.rguleria.controllerapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Button tl,tr,ml,mr,bl,br;
    String bPressed = "";
    byte[] sendData = new byte[1024];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tl = (Button)findViewById(R.id.tl);
        tr = (Button)findViewById(R.id.tr);
        ml = (Button)findViewById(R.id.ml);
        mr = (Button)findViewById(R.id.mr);
        bl = (Button)findViewById(R.id.bl);
        br = (Button)findViewById(R.id.br);

        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPressed = "tl";
                sendToServer();

            }
        });

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPressed = "tr";
                sendToServer();
            }
        });

        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPressed = "ml";
                sendToServer();
            }
        });

        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPressed = "mr";
                sendToServer();
            }
        });

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPressed = "bl";
                sendToServer();
            }
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bPressed = "br";
                sendToServer();
            }
        });

    }

    private void sendToServer() {
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket(9876);
            InetAddress IPAddress = InetAddress.getByName("192.168.1.2");

            sendData = bPressed.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData,bPressed.length(),IPAddress,9876);
            clientSocket.send(sendPacket);
            Log.d("Sent Packet",bPressed);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(clientSocket!=null){
                clientSocket.close();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
