package com.ktdid.hellosummit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * This is activity for the first screen the user will see.
 * To change this, you can move the intent filter in the AndroidManifest.xml to the activity you want to launch first.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // This method is the start of the activity lifecycle.
    // Initialize and set OnClickListeners for your buttons and other widgets here.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnImageLoader = (Button) findViewById(R.id.btn_launch_image_loader);
        btnImageLoader.setOnClickListener(this);

        Button btnRecyclerView = (Button) findViewById(R.id.btn_launch_recycler_view);
        btnRecyclerView.setOnClickListener(this);
    }

    // This method adds the menu to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // This method handles what to do if a menu item is selected.
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

    // Use a switch statement to easily set the function of your buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_launch_image_loader:
                launchImageLoaderActivity();
                break;
            case R.id.btn_launch_recycler_view:
                launchRecyclerViewActivity();
                break;
        }
    }

    private void launchImageLoaderActivity() {
        // Intents are how we determine which activity to launch next and pass information from one activity to another.
        Intent intent = new Intent(this, ImageDownloadActivity.class);
        // You can do something like the following to send data to the next activity.
        // In the onCreate of the next activity, you will need to do intent.getStringExtra() to retrieve this data.
        // intent.putExtra("This is the TAG", "This is your data");

        startActivity(intent);
    }

    private void launchRecyclerViewActivity() {
        Intent intent = new Intent(this, SummiteerActivity.class);
        startActivity(intent);
        Log.d("MainActivity", "launchRecyclerViewActivity");
    }
}
