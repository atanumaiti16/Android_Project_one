package com.example.atanu.ecommerce_project_one.subCategoryFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atanu.ecommerce_project_one.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class subcategoryItemAdapter extends RecyclerView.Adapter<subcategoryItemAdapter.subCategoryItemViewHolder> {

    private List<subCategoryPojo> subCategoryPojoList;
    private Context context;

    public subcategoryItemAdapter(List<subCategoryPojo> subCategoryPojoList, Context context) {
        this.subCategoryPojoList = subCategoryPojoList;
        this.context = context;
    }

    @NonNull
    @Override
    public subCategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_category_itemview,viewGroup,
                false);
        return new subCategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subCategoryItemViewHolder subCategoryItemViewHolder, int i) {

        subCategoryPojo subCategoryPojoItem = subCategoryPojoList.get(i);

        Picasso.with(context)
                .load(subCategoryPojoItem
                        .getSubCategoryItemImageUrl())
                .into(subCategoryItemViewHolder.SubCategoryImageView);

        subCategoryItemViewHolder.SubcategoryItemName.setText(subCategoryPojoItem.getSubCategoryItemName());
        subCategoryItemViewHolder.SubCategoryItemId.setText(subCategoryPojoItem.getSubCategoryItemId());
        subCategoryItemViewHolder.SubCategoryItemDescription.setText(subCategoryPojoItem.getSubCategoryItemDescription());


        }

    @Override
    public int getItemCount() {
        return subCategoryPojoList.size();
    }

    public class subCategoryItemViewHolder extends RecyclerView.ViewHolder {

        ImageView SubCategoryImageView;
        TextView SubCategoryItemId, SubcategoryItemName, SubCategoryItemDescription;

        public subCategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            SubCategoryImageView = itemView.findViewById(R.id.SubcategoryImageView);
            SubCategoryItemDescription = itemView.findViewById(R.id.SubCategory_Description);
            SubCategoryItemId = itemView.findViewById(R.id.SubCategoryId);
            SubcategoryItemName = itemView.findViewById(R.id.Subcategory_Name);
        }
    }
}
