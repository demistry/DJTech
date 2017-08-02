package com.kryptkode.cyberman.djtech;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kryptkode.cyberman.djtech.adapters.TeamRecyclerAdapter;
import com.kryptkode.cyberman.djtech.utils.ItemDivider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamMembersActivity extends AppCompatActivity {
    private RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;
    private TeamRecyclerAdapter teamRecyclerAdapter;
    private JSONArray jsonArray;
    private List<DummyClass> list = new ArrayList<>();
    int [] images = {R.drawable.david,R.drawable.john_esan,R.drawable.paul,R.drawable.marvex,
            R.drawable.isaiah,R.drawable.udoka,R.drawable.mirabel, R.drawable.stone};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_members_layout);
        try {
            jsonArray = new JSONArray(loadJsonFromRaw());
            for(int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DummyClass dummyClass = new DummyClass();
                dummyClass.names= jsonObject.getString("name");
                dummyClass.position = jsonObject.getString("position");
                dummyClass.profile = jsonObject.getString("profile");
                dummyClass.number = jsonObject.getString("phone_number");
                dummyClass.email = jsonObject.getString("email");
                list.add(dummyClass);
            }
        }
        catch (JSONException js){

        }
        rv= (RecyclerView) findViewById(R.id.team_members_rv);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        teamRecyclerAdapter = new TeamRecyclerAdapter(list, images, new TeamRecyclerAdapter.TeamListener() {
            @Override
            public void onProfilePictureClicked(View v, int position) {
                openProfilePicture(position);
            }

            @Override
            public void onFacebookClicked(View v, int position){
                openFacebookProfile(position);
            }

            @Override
            public void onTwitterClicked(View v, int position) {
                openTwitterProfile(position);
            }

            @Override
            public void onInstagramClicked(View v, int position) {
                openInstagramProfile(position);
            }
        });
        rv.addItemDecoration(new ItemDivider(this));
        rv.setFitsSystemWindows(true);
        rv.smoothScrollToPosition(0);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(teamRecyclerAdapter);

    }


    //method for profile picture
    public void openProfilePicture(int profile){
        View view = getLayoutInflater().from(this).inflate(R.layout.image_dialog_view, null);
        ImageView profilePic =(ImageView) view.findViewById(R.id.alertDialogImage);
        AlertDialog.Builder alert = new AlertDialog.Builder(TeamMembersActivity.this);
        alert.setCancelable(true);
        switch (profile){
            case 0:
                profilePic.setImageResource(R.drawable.david);
                alert.setView(view);
                break;
            case 1:
                profilePic.setImageResource(R.drawable.john_esan);
                alert.setView(view);
                break;
            case 2:
                profilePic.setImageResource(R.drawable.paul);
                alert.setView(view);
                break;
            case 3: profilePic.setImageResource(R.drawable.marvex);
                alert.setView(view);
                break;
            case 4: profilePic.setImageResource(R.drawable.isaiah);
                alert.setView(view);
                break;
            case 5:
                profilePic.setImageResource(R.drawable.udoka);
                alert.setView(view);
                break;
            case 6:
                profilePic.setImageResource(R.drawable.mirabel);
                alert.setView(view);
                break;
            case 7:
                profilePic.setImageResource(R.drawable.stone);
                alert.setView(view);
                break;
        }
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    //create method for facebook intents
    public void openFacebookProfile(int profile) {
        Intent intent;
        Uri uri;
        switch(profile){
            case 0:
                    try {
                        uri= Uri.parse("fb://profile/100003716695633");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.setPackage("com.facebook.katana");
                        startActivity(intent);
                    }
                    catch (ActivityNotFoundException e){
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/dilenwabor")));
                    } break;
            case 1:
                try {
                    uri= Uri.parse("fb://profile/100002138187550");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/john.ehikwe")));
                }   break;
            case 2:
                try {
                    uri= Uri.parse("fb://profile/100002264017941");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/paul.patricks1")));
                }   break;
            case 3:
                try {
                    uri= Uri.parse("fb://profile/100002159457825");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/donmarvex")));
                }   break;
            case 4:
                try {
                    uri= Uri.parse("fb://profile/100002698629833");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/Docsweet2")));
                }   break;
            case 5:

                try {
                    uri= Uri.parse("fb://profile/100002376548920");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/gabriel.onyebuolise1")));
                }   break;
            case 6:
                try {
                    uri= Uri.parse("fb://profile/100001830500940");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/mirabelle.nwokoye")));
                }   break;
            case 7:
                try {
                    uri= Uri.parse("fb://profile/100003928803041");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.facebook.katana");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.facebook.com/imonitie.livingstone")));
                }   break;
        }
    }




    //method for twitter intents
    public void openTwitterProfile(int profile){
        Intent intent;
        Uri uri;
        switch(profile){
            case 0:
                try {
                    uri= Uri.parse("twitter://user?screen_name=davidemi07");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/davidemi07")));
                }   break;
            case 1:
                try {
                    uri= Uri.parse("twitter://user?screen_name=_johnesan");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/_johnesan")));
                }   break;
            case 2:
                try {
                    uri= Uri.parse("twitter://user?screen_name=kyle_krammer");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/kyle_krammer")));
                }   break;
            case 3:
            try {
                uri= Uri.parse("twitter://user?screen_name=DonMarvex");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.twitter.android");
                startActivity(intent);
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DonMarvex")));
            }   break;
            case 4:
                try {
                    uri= Uri.parse("twitter://user?screen_name=osebakare");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/osebakare")));
                }   break;
            case 5:
                try {
                    uri= Uri.parse("twitter://user?screen_name=Ud_Gab");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Ud_Gab")));
                }   break;
            case 6:
                try {
                    uri= Uri.parse("twitter://user?screen_name=Mirabelleuche");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Mirabelleuche")));
                }   break;
            case 7:
                try {
                    uri= Uri.parse("twitter://user?screen_name=stoneimonitie");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.twitter.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/stoneimonitie")));
                }   break;
        }

    }



    //method for instagram intents
    public void openInstagramProfile(int profile){
        Intent intent;
        Uri uri;
        switch(profile){
            case 0:
                try {
                    uri= Uri.parse("instagram://user?username=davidemi07");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/davidemi07")));
                } break;
            case 1:
                try {
                    uri= Uri.parse("instagram://user?username=_johnesan");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/_johnesan")));
                }   break;
            case 2:
                try {
                    uri= Uri.parse("instagram://user?username=kyle_krammer");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=kyle_krammer")));
                }   break;
            case 3:
                try {
                    uri= Uri.parse("instagram://user?username=donmarvex");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=donmarvex")));
                }   break;
            case 4:
                try {
                    uri= Uri.parse("instagram://user?username=izbakare");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=izbakare")));
                }   break;
            case 5:
                try {
                    uri= Uri.parse("instagram://user?username=udokagabriel");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=udokagabriel")));
                }   break;
            case 6:
                try {
                    uri= Uri.parse("instagram://user?username=mirabelleuche");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=mirabelleuche")));
                }   break;
            case 7:
                try {
                    uri= Uri.parse("instagram://user?username=stoneimonitie");
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://user?username=stoneimonitie")));
                }   break;
        }
    }



    //read JSON data from file
    public String loadJsonFromRaw(){
        String json;
        try {
            InputStream input =getResources().openRawResource(R.raw.team_json_file);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            json = new String(buffer,"UTF-8");


        }
        catch (IOException io){
            io.printStackTrace();
            return null;
        }
        return json;
    }
}
