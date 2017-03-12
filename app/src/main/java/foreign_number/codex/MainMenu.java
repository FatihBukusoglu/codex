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

public class MainMenu extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    Button languageButton;

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