package co.com.etn.myfirstfile;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText fileName;
    private EditText fileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        fileName = (EditText) findViewById(R.id.file_name);
        fileText = (EditText) findViewById(R.id.file_text);
    }


    public void saveFile(View view) throws IOException {


        String filename = fileName.getText().toString();
        String string = fileText.getText().toString();

        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), filename);

        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(string.getBytes());
            Toast.makeText(this, "Archivo creado en " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Archivo creado en " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } finally {
            stream.close();
        }

    }

    public void readFile(View view) throws IOException {
        String filename = fileName.getText().toString();
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), filename);
        if (file.exists()) {
            int length = (int) file.length();

            byte[] bytes = new byte[length];

            FileInputStream in = new FileInputStream(file);
            try {
                in.read(bytes);
            } finally {
                in.close();
            }
            Toast.makeText(this, new String(bytes), Toast.LENGTH_SHORT).show();
        }

    }

}
