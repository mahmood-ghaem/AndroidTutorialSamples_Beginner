package nl.mahmood.photoimportdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author Sayed Mahmood Ghaem Maghami
 * Tutorial 9 in Android Tutorial Samples for Beginner
 * https://github.com/mahmood-ghaem/AndroidTutorialSamples_Beginner
 */
public class MainActivity extends AppCompatActivity
{
    private static final int CAMERA_REQUEST = 10;
    private static final int STORAGE_REQUEST = 20;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int MY_Storage_PERMISSION_CODE = 200;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonCameraClick(View view)
    {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }
        else
        {
            getPhoto(1);
        }
    }

    public void buttonLoadPictureClick(View view)
    {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_Storage_PERMISSION_CODE);
        }
        else
        {
            getPhoto(2);
        }
    }

    public void getPhoto(int buttonID)
    {
        if (buttonID == 1)
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
        else if (buttonID == 2)
        {
            Intent intentStorage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentStorage, STORAGE_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                getPhoto(1);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode == MY_Storage_PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Storage permission granted", Toast.LENGTH_LONG).show();
                getPhoto(2);
            }
            else
            {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK && data != null)
        {
            Bitmap myBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(myBitmap);
        }
        else if (requestCode == STORAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null)
        {
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
