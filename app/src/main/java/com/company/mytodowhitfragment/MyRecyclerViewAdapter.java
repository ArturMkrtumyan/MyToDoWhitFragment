
package com.company.mytodowhitfragment;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<User> users;

     MyRecyclerViewAdapter(List<User> users) {
        this.users = users;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        User currentUser = users.get(position);
        String name = currentUser.getTextViewname();
        holder.name.setText(name);
        String description = currentUser.getTextViewdescription();
        holder.description.setText(description);
        Uri uri = currentUser.getImage();
        holder.imageView.setImageURI(uri);
        switch (currentUser.getImportance()) {
            case "high":
                holder.colorView.setBackgroundColor(Color.RED);
                break;
            case "medium":
                holder.colorView.setBackgroundColor(Color.YELLOW);
                break;
            case "low":
                holder.colorView.setBackgroundColor(Color.GREEN);
                break;
            default:
                break;
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeItem(position);
                return false;
            }
            private void removeItem(int position) {
                int newPosition = holder.getAdapterPosition();
                users.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, users.size());
            }
        });

    }
    @Override
    public int getItemCount() {
        return users.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        ImageView imageView;
        CustomView colorView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            description = itemView.findViewById(R.id.text_description);
            imageView = itemView.findViewById(R.id.id_image);
            colorView = itemView.findViewById(R.id.customview);
        }
    }

}