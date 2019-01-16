package com.example.atanu.ecommerce_project_one.App;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.atanu.ecommerce_project_one.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class LandingRecyclerViewAdapter extends RecyclerView.Adapter<LandingRecyclerViewAdapter.MyViewHolder> {

    private List<MyPojo> list_data;
    private Context context;

//implementing onclick listener for recyclerview
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    public LandingRecyclerViewAdapter(List<MyPojo> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_category_item,viewGroup,
                false);




        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        MyPojo listData=list_data.get(i);

        Picasso.with(context)
                .load(listData
                        .getCimagerl())
                .into(myViewHolder.imageView);

        //holder.txtname.setText(listData.getName());
        myViewHolder.CategoryName.setText(listData.getcName());
        myViewHolder.CategoryDescription.setText(listData.getcDescription());
        myViewHolder.CategoryID.setText(listData.getcId());

    }

    @Override
    public int getItemCount() {
       return list_data.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView CategoryName,CategoryDescription, CategoryID;
        //LinearLayout linearLayout1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            CategoryDescription = itemView.findViewById(R.id.Category_description);
            CategoryName = itemView.findViewById(R.id.Category_name);
            CategoryID =itemView.findViewById(R.id.Category_id);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }

}
