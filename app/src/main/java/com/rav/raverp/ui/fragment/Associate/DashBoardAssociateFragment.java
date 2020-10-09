package com.rav.raverp.ui.fragment.Associate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.data.local.prefs.PrefsHelper;
import com.rav.raverp.ui.ChangePasswordActivity;
import com.rav.raverp.ui.EditProfileActivity;
import com.rav.raverp.ui.LoginActivity;
import com.rav.raverp.utils.AppConstants;
import com.rav.raverp.utils.ViewUtils;

public class DashBoardAssociateFragment extends Fragment {

    View view;
    androidx.appcompat.widget.Toolbar toolbar;


    public DashBoardAssociateFragment() {

    }

    public DashBoardAssociateFragment(androidx.appcompat.widget.Toolbar toolbar) {
        this.toolbar=toolbar;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.dashboard_associate_fragment, container, false);

       return  view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    //On click of option menu
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
      if (id == R.id.change_password) {

         Intent intent =new Intent(getContext(), ChangePasswordActivity.class);
          startActivity(intent);

            return true;
        }else if (id == R.id.activity_log){
            //activity log fragment



        }else if (id == R.id.profile){

            //
          Intent intent =new Intent(getContext(), EditProfileActivity.class);
          startActivity(intent);




      }else if (id == R.id.signout){

                ViewUtils.showConfirmationDialog(getActivity(), getString(R.string.msg_dialog_logout),
                        new DialogActionCallback() {
                            @Override
                            public void okAction() {
                                PrefsHelper.remove(getActivity(), AppConstants.LOGIN);
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        });
        }
        return super.onOptionsItemSelected(item);
    }
}
