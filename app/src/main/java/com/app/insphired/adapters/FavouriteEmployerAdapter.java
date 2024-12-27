package com.app.insphired.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.insphired.apimodel.GetFavouriteModelList;
import com.app.insphired.EmployerActivity.SelectSlotsActivity;
import com.app.insphired.R;
import com.app.insphired.retrofit.Api_Client;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavouriteEmployerAdapter extends RecyclerView.Adapter<FavouriteEmployerAdapter.ViewHolder> {
    Context context;
    List<GetFavouriteModelList>getFavouriteModelLists;
    private String strFirstName;

    public FavouriteEmployerAdapter(Context context, List<GetFavouriteModelList> getFavouriteModelLists) {
        this.context = context;
        this.getFavouriteModelLists = getFavouriteModelLists;
    }

    @NonNull
    @Override
    public FavouriteEmployerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View view  = LayoutInflater.from(context).inflate(R.layout.recycelerfavouriteemployee,viewGroup,Boolean.parseBoolean("false"));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteEmployerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(Api_Client.BASE_URL_IMAGES + getFavouriteModelLists.get(position).getEmpImage()).into(holder.imageFavouriteGet);
        strFirstName =  getFavouriteModelLists.get(position).getFirstName();
        holder.nameFavGet.setText(strFirstName);
        holder.profileNameFavGet.setText(getFavouriteModelLists.get(position).getCatName());
        holder.startDateFavEmp.setText(getFavouriteModelLists.get(position).getStartDate());
        holder.endDateFavEmp.setText(getFavouriteModelLists.get(position).getEndDate());
        float myFloat = getFavouriteModelLists.get(position).getRating();
        holder.rating_barFavGet.setRating(Math.round(myFloat));
        holder.priceFavEmp.setText(getFavouriteModelLists.get(position).getDailyRate());
        holder.locationFavEmp.setText(getFavouriteModelLists.get(position).getAddress());

        holder.BookBtnFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Id = String.valueOf(getFavouriteModelLists.get(position).getEmpId());
                Intent intent = new Intent(context, SelectSlotsActivity.class);
                intent.putExtra("EmpId",Id);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return getFavouriteModelLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageFavouriteGet;
        TextView nameFavGet,profileNameFavGet,startDateFavEmp,endDateFavEmp,priceFavEmp,locationFavEmp;
        AppCompatRatingBar rating_barFavGet;
        AppCompatButton BookBtnFavList;
        public ViewHolder(View itemView) {
            super(itemView);

            imageFavouriteGet = itemView.findViewById(R.id.imageFavouriteGet);
            nameFavGet = itemView.findViewById(R.id.nameFavGet);
            profileNameFavGet = itemView.findViewById(R.id.profileNameFavGet);
            startDateFavEmp = itemView.findViewById(R.id.startDateFavEmp);
            endDateFavEmp = itemView.findViewById(R.id.endDateFavEmp);
            priceFavEmp = itemView.findViewById(R.id.priceFavEmp);
            locationFavEmp = itemView.findViewById(R.id.locationFavEmp);
            rating_barFavGet = itemView.findViewById(R.id.rating_barFavGet);
            BookBtnFavList = itemView.findViewById(R.id.BookBtnFavList);
        }
    }
}
