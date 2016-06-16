package org.am.dba.ms.mystudents.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.am.dba.ms.mystudents.R;
import org.am.dba.ms.mystudents.adapter.ProfileArrayAdapter;
import org.am.dba.ms.mystudents.model.Student;
import org.am.dba.ms.mystudents.service.StudentAPI;
import org.am.dba.ms.mystudents.util.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final ListView profiles = (ListView)findViewById(R.id.MyStudents_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        StudentAPI sApi = ServiceBuilder.createService(StudentAPI.class);
        Call<List<Student>> client = sApi.getStudents();
        final List<Student> lstStudents = new ArrayList<>();
        client.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(response.isSuccessful()){
                    lstStudents.addAll(response.body());
                } else {
                    Log.d("Error",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "No student exist"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        profiles.setAdapter(new ProfileArrayAdapter(this, lstStudents));
        profiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),i + " "+l+" "+((TextView)view.findViewById(R.id.firstLine)).getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(ProfileActivity.this,"Settings clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
