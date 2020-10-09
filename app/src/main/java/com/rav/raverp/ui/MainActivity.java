package com.rav.raverp.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.rav.raverp.MyApplication;
import com.rav.raverp.R;
import com.rav.raverp.View.ExpandableNavigationListView;
import com.rav.raverp.data.model.ChildModel;
import com.rav.raverp.data.model.HeaderModel;
import com.rav.raverp.data.model.api.ApiResponse;
import com.rav.raverp.data.model.api.GetProfileModel;
import com.rav.raverp.data.model.api.LoginModel;
import com.rav.raverp.network.ApiClient;
import com.rav.raverp.network.ApiHelper;


import com.rav.raverp.ui.fragment.Associate.CustomerListFragment;
import com.rav.raverp.ui.fragment.Associate.DashBoardAssociateFragment;
import com.rav.raverp.ui.fragment.Associate.FollowUpLeadListFragment;
import com.rav.raverp.ui.fragment.Associate.LeadListFragment;
import com.rav.raverp.ui.fragment.Associate.MyGoalListFragment;
import com.rav.raverp.ui.fragment.Associate.PlotAvailabilityFragment;

import com.rav.raverp.ui.fragment.Operator.DashBoardOperatorFragment;
import com.rav.raverp.utils.ViewUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static String mobile="mobile";
    public static String userid="userid";

    Toolbar toolbar;
    TextView editprodile;
    DrawerLayout drawer;
    private LoginModel login;
    private ApiHelper apiHelper;
    TextView Name,Role;
    ImageView iamgeprofile;


    private ExpandableNavigationListView navigationExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);
        login = MyApplication.getLoginModel();


        navigationExpandableListView = (ExpandableNavigationListView) findViewById(R.id.expandable_navigation);


        drawer = findViewById(R.id.drawer_layouts);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        Name=(TextView)header.findViewById(R.id.name);
        iamgeprofile=(ImageView)header.findViewById(R.id.iamgeprofile);
        Role=(TextView)header.findViewById(R.id.role) ;


        apiHelper = ApiClient.getClient().create(ApiHelper.class);
        GetProfileApi();



        LoginModel login=MyApplication.getLoginModel();
        if (login.getStrRole().equalsIgnoreCase("Associate")){

            show1stDashboard();
        }
        if (login.getStrRole().equalsIgnoreCase("Operator")){
            show2ndDashboard();

        }
        if (login.getStrRole().equalsIgnoreCase("Admin")){

        }
        if (login.getStrRole().equalsIgnoreCase("")){

        }





    }

    private void GetProfileApi() {

        String loginid=login.getStrLoginID();
        Integer role=login.getIntRoleID();
        Call<ApiResponse<List<GetProfileModel>>> GetProfileCall = apiHelper.getProfile(loginid,role);
        GetProfileCall.enqueue(new Callback<ApiResponse<List<GetProfileModel>>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<List<GetProfileModel>>> call,
                                   @NonNull Response<ApiResponse<List<GetProfileModel>>> response) {

                if (response.isSuccessful()) {

                    if (response != null) {
                        if (response.body().getResponse().equalsIgnoreCase("Success")) {
                            List<GetProfileModel> login = response.body().getBody();

                            String profile = login.get(0).getStrProfilePic();
                            String email = login.get(0).getStrEmail();
                            String mobile = login.get(0).getStrPhone();
                            String role= login.get(0).getStrRole();
                            Role.setText(role);


                            String name = login.get(0).getStrDisplayName();
                            Name.setText(name);

                            Glide.with(iamgeprofile.getContext()).load("http://192.168.29.136" + profile)
                                    .placeholder(R.drawable.account)
                                    .into(iamgeprofile);






                        } else {


                        }
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse<List<GetProfileModel>>> call,
                                  @NonNull Throwable t) {
                if (!call.isCanceled()) {

                    ViewUtils.showToast(t.getLocalizedMessage());
                }
                t.printStackTrace();
            }
        });
    }

    private void show1stDashboard() {
        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel("Dashboard",R.drawable.ic_home_black_24dp))
                .addHeaderModel(
                        new HeaderModel("Plot Moduls", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Plot Availability",R.drawable.ic_baseline_arrow_forward_24)))
                .addHeaderModel(
                        new HeaderModel("Wallet", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Add Wallet",R.drawable.ic_baseline_arrow_forward_24)))
                .addHeaderModel(
                        new HeaderModel("Goal", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("My Goal",R.drawable.ic_baseline_arrow_forward_24)))
                .addHeaderModel(
                        new HeaderModel("Lead Management", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Lead List",R.drawable.ic_baseline_arrow_forward_24))
                                .addChildModel(new ChildModel("Follow UP Lead List",R.drawable.ic_baseline_arrow_forward_24)))
                .addHeaderModel(
                        new HeaderModel("Site Visit", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Site Visit Request",R.drawable.ic_baseline_arrow_forward_24))
                                .addChildModel(new ChildModel("Site Visit Request Status",R.drawable.ic_baseline_arrow_forward_24)))

                .addHeaderModel(
                        new HeaderModel("Customer Management", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Add Customer",R.drawable.ic_baseline_arrow_forward_24)))



                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                        navigationExpandableListView.setSelected(groupPosition);
                        //drawer.closeDrawer(GravityCompat.START);
                        if (id == 0) {
                            toolbar.setTitle("Dashboard");
                            Fragment fragment=new DashBoardAssociateFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.homepage, fragment,"MYFRAGMENT");
                            transaction.addToBackStack("MYFRAGMENT").commit();
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {


                        } else if (id == 2) {

                        } else if (id == 3) {

                        }
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition, childPosition);

                        if (groupPosition==1){
                            if (id == 0) {
                                toolbar.setTitle("Plot Available");
                                Fragment fragment=new PlotAvailabilityFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.homepage, fragment);
                                transaction.addToBackStack(null).commit();
                                drawer.closeDrawer(GravityCompat.START);
                            }
                        }else if (groupPosition==2){
                            if (id == 0) {

                            }
                        }if (groupPosition==3){
                            if (id == 0) {
                                toolbar.setTitle("Goal List");
                                Fragment fragment=new MyGoalListFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.homepage, fragment);
                                transaction.addToBackStack(null).commit();
                                drawer.closeDrawer(GravityCompat.START);
                            }
                        }
                        if (groupPosition==4){
                            if (id == 0) {
                                toolbar.setTitle("Lead List");
                                Fragment fragment=new LeadListFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.homepage, fragment);
                                transaction.addToBackStack(null).commit();
                                drawer.closeDrawer(GravityCompat.START);
                            }
                            if (id == 1) {
                                toolbar.setTitle("Follow UP Lead List");
                                Fragment fragment=new FollowUpLeadListFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.homepage, fragment);
                                transaction.addToBackStack(null).commit();
                                drawer.closeDrawer(GravityCompat.START);


                            }
                        }else if (groupPosition==5){
                            if (id == 0) {

                            }

                            if (id == 1) {

                            }
                        }
                        else if (groupPosition==6){
                            if (id == 0) {
                                toolbar.setTitle("Customer List");
                                Fragment fragment=new CustomerListFragment();
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.homepage, fragment);
                                transaction.addToBackStack(null).commit();
                                drawer.closeDrawer(GravityCompat.START);
                            }

                            }






                        return false;
                    }
                });

        Fragment fragment=new DashBoardAssociateFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homepage, fragment,"MYFRAGMENT");
        transaction.addToBackStack("MYFRAGMENT").commit();
        navigationExpandableListView.setSelected(0);
    }

    private void show2ndDashboard() {
        navigationExpandableListView
                .init(this)
                .addHeaderModel(new HeaderModel("Dashboard",R.drawable.ic_home_black_24dp))
                .addHeaderModel(
                        new HeaderModel("Lead Management", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Lead List",R.drawable.ic_baseline_arrow_forward_24))
                                .addChildModel(new ChildModel("Follow UP Lead List",R.drawable.ic_baseline_arrow_forward_24)))
                .addHeaderModel(
                        new HeaderModel("Site Visit", R.drawable.ic_baseline_my_location_24, true)
                                .addChildModel(new ChildModel("Site Visit Request",R.drawable.ic_baseline_arrow_forward_24))
                                .addChildModel(new ChildModel("Site Visit Request Status",R.drawable.ic_baseline_arrow_forward_24)))

                .build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                        navigationExpandableListView.setSelected(groupPosition);
                        //drawer.closeDrawer(GravityCompat.START);
                        if (id == 0) {
                            toolbar.setTitle("Dashboard");
                            Fragment fragment=new DashBoardOperatorFragment();
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.homepage, fragment,"MYFRAGMENT");
                            transaction.addToBackStack("MYFRAGMENT").commit();
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {


                        } else if (id == 2) {

                        } else if (id == 3) {

                        }
                        return false;
                    }
                })
                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition, childPosition);

                        if (groupPosition==1){
                            if (id == 0) {

                            }
                            if (id == 1) {

                            }
                        }else if (groupPosition==2){
                            if (id == 0) {

                            }

                            if (id == 1) {


                            }
                        }

                        return false;
                    }
                });

        Fragment fragment=new DashBoardOperatorFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homepage, fragment,"MYFRAGMENT");
        transaction.addToBackStack("MYFRAGMENT").commit();
        navigationExpandableListView.setSelected(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layouts);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {


        onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       if (id == R.id.change_password) {
            toolbar.setTitle("Change Password");

            Intent intent=new Intent(MainActivity.this,ChangePasswordActivity.class);
            startActivity(intent);

            return true;
        }else if (id == R.id.activity_log){

            toolbar.setTitle("Activity Log");


        }else if (id == R.id.profile){


            toolbar.setTitle("Profile");

        }else if (id == R.id.signout){


                ViewUtils.showConfirmationDialog(MainActivity.this, getString(R.string.msg_dialog_logout),
                        new DialogActionCallback() {
                            @Override
                            public void okAction() {
                                PrefsHelper.remove(MainActivity.this, AppConstants.LOGIN);
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        });
        }

        return super.onOptionsItemSelected(item);
    }*/

}
