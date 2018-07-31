package com.example.student.searchuser;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.UserModel> {
    private List<User> users;
    private Context context;
    private List<User> filteredUsers;

    public MyAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        this.filteredUsers = users;
    }

    @NonNull
    @Override
    public UserModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_model, parent, false);
        return new UserModel(view);    }

    @Override
    public void onBindViewHolder(@NonNull UserModel userHolder, int position) {
        final User user = users.get(position);
        userHolder.name.setText(user.getName());
        userHolder.description.setText(user.getDescription());
        Picasso.get().load(user.getImageUrl()).into(userHolder.image);
        userHolder.phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(user.getPhoneNumber()));
                context.startActivity(intent);
            }
        });
        userHolder.emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, user.getEmailAddress());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredUsers.size();
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    filteredUsers = users;
//                } else {
//                    List<User> fiteredUsers2 = new ArrayList<>();
//                    for (User row : users) {
//                        if (row.getmName().toLowerCase().contains(charString.toLowerCase())) {
//                            fiteredUsers2.add(row);
//                        }
//                    }
//
//                    filteredUsers = fiteredUsers2;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = filteredUsers;
//                return filterResults;
//
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                filteredUsers = (List<User>) filterResults.values;
//
//                notifyDataSetChanged();
//            }
//        };
//    }

    public class UserModel extends RecyclerView.ViewHolder {
        private CircleImageView image;
        private TextView name;
        private TextView description;
        private ImageButton phoneBtn;
        private ImageButton emailBtn;
        private RatingBar ratingBar;

        public UserModel(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.userImage);
            name = itemView.findViewById(R.id.userName);
            description = itemView.findViewById(R.id.description);
            phoneBtn = itemView.findViewById(R.id.phoneBtn);
            emailBtn = itemView.findViewById(R.id.emailBtn);
            ratingBar = itemView.findViewById(R.id.rating);
        }
    }
}
