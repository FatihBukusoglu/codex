package foreign_number.codex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.lang.String;

public class MainMenu extends AppCompatActivity{

    SearchView mSearchBar;
    TextView mDiscriptionView;
    Button mSearchButton;
    Button languageButton;
    String choosen_Word = " ",choosen_Language = " ";

    //Get Database Referance
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Get UI elements
        mDiscriptionView = (TextView) findViewById(R.id.result);
        languageButton = (Button)findViewById(R.id.language_button);
        mSearchButton = (Button) findViewById(R.id.search_button);
        mSearchBar = (SearchView) findViewById(R.id.searchView);


        //Search Bar

        mSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                choosen_Word = query.toString();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                choosen_Word = newText.toString();
                return false;
            }
        });

        //Language Menu
       languageButton.setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainMenu.this,languageButton);
                popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());

               popup.setOnMenuItemClickListener(new  PopupMenu.OnMenuItemClickListener(){

                   public boolean onMenuItemClick(MenuItem item){
                       choosen_Language = item.getTitle().toString();
                       languageButton.setText(item.getTitle().toString());
                       Toast.makeText(MainMenu.this,"Choosen Language: " +item.getTitle(),Toast.LENGTH_SHORT).show();
                       return true;
                   }
               });
               popup.show();
           }
       });

        //Search Button
        mSearchButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DatabaseReference mLeafRef = mRootRef.child(choosen_Language);
                DatabaseReference mLeaf2 = mLeafRef.child(choosen_Word);

                mLeaf2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String text = dataSnapshot.getValue(String.class);
                        mDiscriptionView.setText(text);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        String text = "Cannot found the search";
                        mDiscriptionView.setText(text);
                    }
                });
            }
        });
    }

}