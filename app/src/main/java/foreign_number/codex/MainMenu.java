package foreign_number.codex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    Button languageButton;

    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + value);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



       languageButton = (Button)findViewById(R.id.language_button);

       languageButton.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainMenu.this,languageButton);
                popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());

               popup.setOnMenuItemClickListener(new  PopupMenu.OnMenuItemClickListener(){
                   public boolean onMenuItemClick(MenuItem item){
                       Toast.makeText(MainMenu.this,"Search Results For: " +item.getTitle(),Toast.LENGTH_SHORT).show();
                       return true;
                   }
               });
               popup.show();
           }
       });
    }
};