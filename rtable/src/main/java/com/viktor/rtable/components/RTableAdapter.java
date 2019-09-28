package com.viktor.rtable.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viktor.rtable.annotations.GridColumn;
import com.viktor.rtable.R;
import com.viktor.rtable.events.OnRowClicked;
import com.viktor.rtable.utils.Utils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * RecyclerAdapter which uses the list of objects, the application context,
 * class of the beans, click event by row, and the index page, for rendering
 * the sub collection of objects.
 */
public class RTableAdapter extends RecyclerView.Adapter<RTableAdapter.GridRowHolder> {
    private List<Object> beans;
    private Context ctx;
    private Class clazz;
    private OnRowClicked onRowClicked;
    private Integer indexPage;

    /**
     * Only shows the subcollection of objects.
     * @param ctx
     * @param beans
     * @param beansClazz
     */
    public RTableAdapter(Context ctx, List<Object> beans, Class beansClazz){
        this.beans = beans;
        this.ctx = ctx;
        this.clazz = beansClazz;
    }

    /**
     * Shows collection of object and also listens click events over every row.
     * @param ctx
     * @param beans
     * @param beansClazz
     * @param onRowClicked
     * @param indexPage
     */
    public RTableAdapter(Context ctx, List<Object> beans, Class beansClazz, OnRowClicked onRowClicked, int indexPage){
        this.beans = beans;
        this.ctx = ctx;
        this.clazz = beansClazz;
        this.onRowClicked = onRowClicked;
        this.indexPage = indexPage;
    }

    @NonNull
    @Override
    public GridRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.recycler_row_detail_example, parent, false);
        return new GridRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridRowHolder holder, final int position) {
        final Object rowObject = beans.get(position);
        Field[] clazzFields = clazz.getDeclaredFields();
        try {
            int index = 0;
            while(index < clazzFields.length){
                Field f = findField(index, clazzFields, rowObject);
                f.setAccessible(true);
                String value = String.valueOf(f.get(rowObject));
                TextView textView = createTextView(value, f.getAnnotation(GridColumn.class));
                holder.llayout.addView(textView);

                if(indexPage != null){
                    holder.llayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        onRowClicked.onClick(view, ((indexPage * ctx.getResources().getInteger(R.integer.rtable_rowsPerPage)) + (position+1)));
                        }
                    });
                }

                index++;
            }
        }catch (IllegalAccessException accessExp){
            System.out.println("No access to this field");
        }
    }

    /**
     * Finds the correspondent field by position, index is in between 0 - size-1.
     *
     * @param index
     * @param fields
     * @param o
     * @return
     */
    private Field findField(int index, Field[] fields, Object o) {
        Field foundField = null;
        for (Field f: fields) {
            GridColumn col = f.getAnnotation(GridColumn.class);
            if(index == col.position())
                foundField = f;
        }

        return foundField;
    }

    /**
     * Every text field is created dynamically. field is read in order to get the field value.
     * @param value
     * @param col
     * @return
     */
    private TextView createTextView(String value, GridColumn col) {
        TextView txt = new TextView(ctx.getApplicationContext());
        txt.setId(Utils.randomInt());
        txt.setText(value);
        txt.setTextColor(ctx.getResources().getColor(R.color.rtable_content_textColor));
        txt.setLayoutParams(new ViewGroup.LayoutParams(
                Utils.Conversion.dp(col.width(), ctx.getResources()),
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        return txt;
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    /**
     * Wrappes the layour which contents the TextFields that are created dynamically.
     */
    public class GridRowHolder extends RecyclerView.ViewHolder{
        LinearLayout llayout;

        public GridRowHolder(View v){
            super(v);
            llayout = v.findViewById(R.id.row_dynamic_content);
        }
    }

}
