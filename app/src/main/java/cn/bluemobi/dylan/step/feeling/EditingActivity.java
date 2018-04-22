package cn.bluemobi.dylan.step.feeling;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import cn.bluemobi.dylan.step.R;

public class EditingActivity extends AppCompatActivity implements View.OnClickListener{
    private Uri mCapturedImageURI;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int RESULT_EDITING_FEELING = 3;
    Button add_pic_button;
    Button confirm_add_pic_button;
    Button giveup_add_button;
    TextInputEditText feeling_text;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_editing_feeling);
        init_widget();
        add_listener();
    }

    public void init_widget() {
        add_pic_button = (Button) findViewById(R.id.add_pic_button);
        confirm_add_pic_button = (Button) findViewById(R.id.confirm_add_pic_buttton);
        giveup_add_button = (Button) findViewById(R.id.giveup_add_pic_button);
        feeling_text = (TextInputEditText) findViewById(R.id.all_feeling_text);
    }

    public void add_listener() {
        add_pic_button.setOnClickListener(this);
        confirm_add_pic_button.setOnClickListener(this);
        giveup_add_button.setOnClickListener(this);
    }

    public void add_pic() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.setTitle("Alert Dialog View");
        Button btnExit = (Button) dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnChoosePath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeGallery();
            }
        });
        dialog.findViewById(R.id.btnTakePhoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeTakePhoto();
            }
        });

        dialog.show();
    }

    /**
     * load a photo
     */
    private void activeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }


    /**
     * take a photo
     */
    private void activeTakePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String fileName = "temp.jpg";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            mCapturedImageURI = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_pic_button:
                add_pic();
                break;
            case R.id.confirm_add_pic_buttton:
                String feeling_string= feeling_text.getText().toString();
                Log.d("feeling", feeling_string);
                break;
            case R.id.giveup_add_pic_button:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (requestCode == RESULT_LOAD_IMAGE &&
                        resultCode == RESULT_OK && null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    MyImage new_image= new MyImage();
                    new_image.setTitle("感想");
                    //new_image.setDescription("这是今天记录的图片。");
                    String feeling_string= feeling_text.getText().toString();
                    new_image.setDescription(feeling_string);
                    new_image.setDatetime(System.currentTimeMillis());
                    new_image.setPath(picturePath);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("new_image", new_image);
                    setResult(RESULT_EDITING_FEELING, resultIntent);
                    dialog.dismiss();
                    finish();
                }
            case REQUEST_IMAGE_CAPTURE:
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(mCapturedImageURI, projection, null, null, null);
                    int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String picturePath = cursor.getString(column_index_data);
                    MyImage new_image = new MyImage();
                    new_image.setTitle("感想");

                    String feeling_string= feeling_text.getText().toString();
                    new_image.setDescription(feeling_string);
                    new_image.setDatetime(System.currentTimeMillis());
                    new_image.setPath(picturePath);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("new_image", new_image);
                    setResult(RESULT_EDITING_FEELING, resultIntent);
                    dialog.dismiss();
                    finish();
                }
        }
    }
}
