package com.viktor.rtable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.viktor.rtable.components.ColumnHeader;
import com.viktor.rtable.dto.PersonDetail;
import com.viktor.rtable.events.OnRowClicked;

import java.util.Arrays;
import java.util.List;

/**
 * This example shows how to use rtable (RecyclerViewTable) component.
 *
 */
public class ExampleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout frame;
    private RecyclerViewTable recyclerTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_example_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ExampleApp");
        frame = findViewById(R.id.frame_container);
        recyclerTable = findViewById(R.id.grid_recycler);
        recyclerTable.configure(
                new ColumnHeader[]{
                        new ColumnHeader(50, "id"),
                        new ColumnHeader(200, "name"),
                        new ColumnHeader(300, "address"),
                }
                , (List) setupCollection(), PersonDetail.class, new OnRowClicked() {
                    @Override
                    public void onClick(View view, int rowIndex) {
                        Log.d("INFO", "rowIndex: "+rowIndex);
                        Toast.makeText(getApplicationContext(), "rowNumber: "+rowIndex+" was selected", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private List<PersonDetail> setupCollection() {
        return Arrays.asList(
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132"),
                new PersonDetail("1" , "Victor", "Danton, Texas, 750123"),
                new PersonDetail("2" , "ElCheniss", "San Salvador, El Salvador, 00000"),
                new PersonDetail("3" , "Joey", "Hartford, Connecticut, 06132")
        );
    }

}
