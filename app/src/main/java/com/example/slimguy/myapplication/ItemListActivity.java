package com.example.slimguy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.slimguy.myapplication.R;

import java.util.List;


public class ItemListActivity extends AppCompatActivity implements Animation.AnimationListener{

    RecyclerView listitem;


    //Declaring a shared preferences
    SharedPreferences sp;


    String id [] ={"1","2","3","4","5"};
    String s1[];
    int images [] = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,};

    // Animations
    Animation animatefade, animatemove, animatebounce,animatezoomo;

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        listitem = findViewById(R.id.item_list);

        s1 = getResources().getStringArray(R.array.pics);

        //MyAdapter myAdapter = new MyAdapter(this,s1,images);

/*        load the animation
        animatefade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animatemove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        animatezoomo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);*/

        animatebounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);


        //set animation listener
        //animatefade.setAnimationListener(this);
        //animatezoomo.setAnimationListener(this);
        //animatemove.setAnimationListener(this);
        animatebounce.setAnimationListener(this);

        //instantiating the declared SharedPreferences
        sp = getSharedPreferences("MySelectionPrefs", Context.MODE_PRIVATE);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //removing sharedpreferences

                SharedPreferences settings = getSharedPreferences("MySelectionPrefs", Context.MODE_PRIVATE);
                settings.edit().remove("PicName").commit();


                Snackbar.make(view, "SharedPreference Cleared", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this,id, s1, images , mTwoPane));
        //start animation
        //recyclerView.startAnimation(animatefade);
        recyclerView.startAnimation(animatebounce);
        //recyclerView.startAnimation(animatemove);
        //recyclerView.startAnimation(animatezoomo);
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
    @Override
    public void onAnimationEnd(Animation animation) {
    }
    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        String data1[];
        int images[];

        private final ItemListActivity mParentActivity;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String picname = view.getTag().toString();
                    //start animation
                //view.startAnimation(animatefade);
//                view.startAnimation(animatemove);

                //saving the pic name in the sharedpreferences
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("PicName", picname);
                editor.commit();

                Toast.makeText(ItemListActivity.this, "Preferrence Information saved", Toast.LENGTH_LONG).show();

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM, picname);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM, picname);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,String idn[], String s1[], int img[], boolean twoPane) {
            id = idn;
            mParentActivity = parent;
            mTwoPane = twoPane;
            data1 = s1;
            images = img;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            String picturename = data1[position];

            sp = getApplicationContext().getSharedPreferences("MySelectionPrefs", Context.MODE_PRIVATE);
            String picaname = sp.getString("PicName", "");

            if(picaname.equals(picturename)){
                holder.mpicNameView.setBackgroundColor(Color.rgb(200,0,0));
                holder.mIdView.setText(id[position]);
                holder.mpicNameView.setText(data1[position]);
                holder.mImageView.setImageResource(images[position]);
                holder.itemView.setTag(data1[position]);
                holder.itemView.setOnClickListener(mOnClickListener);
            }else{
                holder.mIdView.setText(id[position]);
                holder.mpicNameView.setText(data1[position]);
                holder.mImageView.setImageResource(images[position]);
                holder.itemView.setTag(data1[position]);
                holder.itemView.setOnClickListener(mOnClickListener);
            }
        }

        @Override
        public int getItemCount() {
            return images.length;
        }
        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mpicNameView;
            final ImageView mImageView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mpicNameView = (TextView) view.findViewById(R.id.content);
                mImageView = (ImageView) view.findViewById(R.id.pic);
            }

        }
    }
}
