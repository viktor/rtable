package com.viktor.rtable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.viktor.rtable.components.ColumnHeader;
import com.viktor.rtable.components.FixedGridLayoutManager;
import com.viktor.rtable.components.RTableAdapter;
import com.viktor.rtable.events.OnRowClicked;
import com.viktor.rtable.utils.Pagination;
import com.viktor.rtable.utils.Utils;

import java.util.List;

/**
 *
 * Component which uses a RecyclerView, ScrollView (header) and layout with footer.
 *
 * RecyclerView renders the information, the scrollview auto scrolls to the correspondent
 * position of the elements that are been showed, footer contains arrows to move between pages.
 *
 *
 * HOW TO USE:
 *
 *
 * Object can be used as simple layout in this way:
 *
     <com.viktor.rtable.RecyclerViewTable
         android:id="@+id/grid_recycler"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         />
 *
 * After that in the activity write ur configure method:
 *
 *         recyclerTable = findViewById(R.id.grid_recycler);
 *         recyclerTable.configure(
                 new ColumnHeader[]{
                          new ColumnHeader(50, "id"),
                          new ColumnHeader(200, "name"),
                          new ColumnHeader(300, "address"),
                  }
                  , (List) setupCollection(), MyJavaBean.class, new OnRowClicked() {
                      @Override
                      public void onClick(View view, int rowIndex) {
                          Log.d("INFO", "rowIndex: "+rowIndex);
                      }
                  })
 *
 *
 *  OnRowClicked is optional.
 *
 *  MyJavaBean.class should be mapped in the following way:
 *
         public class MyJavaBean {
            @GridColumn(width = 50, position = 0)
            private String id;
            @GridColumn(width = 200, position = 1)
            private String name;
            @GridColumn(width = 300, position = 2)
            private String address;
 *
 * width will be transform to dp units, positon is the respect position in the table, starts from 0.
 *
 */
public class RecyclerViewTable extends LinearLayout {
    private RecyclerView recyclerDetail;
    private HorizontalScrollView scrollView;
    private Pagination<Object> pagination;
    private RTableAdapter adapter;
    private int scrollX = 0;
    private Class clazz;
    private ColumnHeader[] headers;
    private OnRowClicked onRowClicked;
    private TextView textViewNoRows;

    public RecyclerViewTable(Context context) {
        super(context);
        init(context);
    }

    public RecyclerViewTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context ctx){
        init(ctx, null);
    }

    private void init(Context ctx, AttributeSet attrs){
        inflate(ctx, R.layout.layout_rtable_component, this);
        this.textViewNoRows = findViewById(R.id.rtable_no_data_msg);
        this.recyclerDetail = findViewById(R.id.grid_recycler_content);
    }

    private void setUpRecyclerView(){
        FixedGridLayoutManager gridLayoutManager = new FixedGridLayoutManager();
        gridLayoutManager.setTotalColumnCount(1);

        if(onRowClicked == null){
            adapter = new RTableAdapter(getContext(), pagination.pages(), clazz);
        }else{
            adapter = new RTableAdapter(getContext(), pagination.pages(), clazz, onRowClicked, pagination.getPageIndex());
        }

        recyclerDetail.setLayoutManager(gridLayoutManager);
        recyclerDetail.setAdapter(adapter);
        recyclerDetail.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollX += dx;
                scrollView.scrollTo(scrollX, 0);
            }
        });

        findViewById(R.id.click_dtable_before_).setOnClickListener(beforeEvent);
        findViewById(R.id.click_dtable_after).setOnClickListener(afterEvent);
    }


    private View.OnClickListener beforeEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            List<Object> list = pagination.prev();

            if(onRowClicked == null){
                adapter = new RTableAdapter(getContext(), list, clazz);
            }else{
                adapter = new RTableAdapter(getContext(), list, clazz, onRowClicked, pagination.getPageIndex());
            }

            recyclerDetail.swapAdapter(adapter, false);
            scrollX = 0;
            scrollView.scrollTo(0,0);
        }
    };

    private View.OnClickListener afterEvent = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            List<Object> list = pagination.next();

            if(onRowClicked == null){
                adapter = new RTableAdapter(getContext(), list, clazz);
            }else{
                adapter = new RTableAdapter(getContext(), list, clazz, onRowClicked, pagination.getPageIndex());
            }

            recyclerDetail.swapAdapter(adapter, false);
            scrollX = 0;
            scrollView.scrollTo(0,0);
        }
    };

    /**
     *
     * @param collection
     * @param clazz
     */
    public void configure(ColumnHeader[] headers, List<Object> collection, Class clazz){
        if(!collection.isEmpty()){
            setupGridHeader(headers);
            this.clazz = clazz;
            pagination = new Pagination<>(collection, getResources().getInteger(R.integer.rtable_rowsPerPage));
            setUpRecyclerView();
            showRecycler();
        }
    }

    /**
     *
     * @param collection
     * @param clazz
     */
    public void configure(ColumnHeader[] headers, List<Object> collection, Class clazz, OnRowClicked onRowClicked){
        if(!collection.isEmpty()){
            setupGridHeader(headers);
            this.clazz = clazz;
            this.onRowClicked = onRowClicked;
            pagination = new Pagination<>(collection, getResources().getInteger(R.integer.rtable_rowsPerPage));
            setUpRecyclerView();
            showRecycler();
        }
    }

    private void setupGridHeader(ColumnHeader[] headers) {
        this.headers = headers;
        scrollView = findViewById(R.id.grid_recycler_header);
        LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.scrollv_header_detail_emp, scrollView, false);
        int i = 0;

        while (i < this.headers.length){
            view.addView(createTextView(headers[i]));
            i++;
        }

        scrollView.addView(view);
    }

    private TextView createTextView(ColumnHeader header) {
        TextView txt = new TextView(getContext());
        txt.setId(Utils.randomInt());
        txt.setText(header.getHeaderText());
        txt.setTextColor(getResources().getColor(R.color.rtable_header_textColor));
        txt.setLayoutParams(new ViewGroup.LayoutParams(
                Utils.Conversion.dp(header.getWidth(), getResources()),
                    ViewGroup.LayoutParams.MATCH_PARENT
        ));

        return txt;
    }

    private void showRecycler(){
        recyclerDetail.setVisibility(VISIBLE);
        textViewNoRows.setVisibility(GONE);
    }

}
