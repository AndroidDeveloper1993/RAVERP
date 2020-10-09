package com.rav.raverp.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.rav.raverp.MyApplication;
import com.rav.raverp.R;
import com.rav.raverp.data.interfaces.DialogActionCallback;
import com.rav.raverp.databinding.DialogConfirmationBinding;
import com.rav.raverp.databinding.DialogErrorBinding;
import com.rav.raverp.databinding.DialogInfoBinding;
import com.rav.raverp.databinding.DialogOfflineBinding;
import com.rav.raverp.databinding.DialogSuccessBinding;


public class ViewUtils {

    public static ProgressDialog progressDoalog = null;

    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void showProgress(final boolean show,
                                    final View mFormView,
                                    final View mProgressView) {
        int shortAnimTime = 200;
        mFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public static void showToast(String message) {
        Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showConfirmationDialog(Context mContext, String message,
                                              final DialogActionCallback dialogActionCallback) {
        final Dialog dialog = new Dialog(mContext);
        DialogConfirmationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_confirmation, null,false);
        dialog.setContentView(binding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 50);
        binding.setMessage(message);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(inset);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
        binding.yesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActionCallback.okAction();
                dialog.dismiss();
            }
        });
        binding.noTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void showInfoDialog(Context mContext, String title, String message,
                                      final DialogActionCallback dialogActionCallback) {
        final Dialog dialog = new Dialog(mContext);
        DialogInfoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_info,null,false);
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        binding.setTitle(title);
        binding.setMessage(message);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 70);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(inset);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
        binding.okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActionCallback.okAction();
                dialog.dismiss();
            }
        });
    }

    public static void showErrorDialog(Context mContext, String errorString,
                                       final DialogActionCallback dialogActionCallback) {
        final Dialog dialog = new Dialog(mContext);
        DialogErrorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_error,null,false);
        dialog.setContentView(binding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 70);
        binding.setErrorMessage(errorString);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(inset);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
        binding.tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActionCallback.okAction();
                dialog.dismiss();
            }
        });
    }

    public static void showSuccessDialog(Context mContext, String message,
                                         final DialogActionCallback dialogActionCallback) {
        final Dialog dialog = new Dialog(mContext);
        DialogSuccessBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_success,null,false);
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        binding.setSuccessMessage(message);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 70);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(inset);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActionCallback.okAction();
                dialog.dismiss();
            }
        });
    }

    public static void showAlertDialog(Activity activity, String title, String msg, String positiveButton,
                                       String negativeButton, final DialogActionCallback dialogActionCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialogActionCallback.okAction();
            }
        });
        if (!CommonUtils.isNullOrEmpty(negativeButton)) {
            builder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
       /* Window window = dialog.getWindow();
        if (window != null) {
            TextView textView = window.findViewById(android.R.id.message);
            TextView alertTitle = window.findViewById(R.id.alertTitle);
            Button button1 = window.findViewById(android.R.id.button1);
            Button button2 = window.findViewById(android.R.id.button2);
            textView.setTypeface(ResourcesCompat.getFont(activity, R.font.googlesans_regular));
            alertTitle.setTypeface(ResourcesCompat.getFont(activity, R.font.googlesans_bold));
            button1.setTypeface(ResourcesCompat.getFont(activity, R.font.googlesans_medium));
            button2.setTypeface(ResourcesCompat.getFont(activity, R.font.googlesans_medium));
        }*/
        dialog.show();
    }

    public static void showStoragePermissionDialog(Activity activity, String title, String msg,
                                                   final DialogActionCallback dialogActionCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(activity.getString(R.string.action_allow),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialogActionCallback.okAction();
                    }
                });
        builder.create().show();
    }

    public static void showOfflineDialog(Context mContext,
                                         final DialogActionCallback dialogActionCallback){
        final Dialog dialog = new Dialog(mContext);
        DialogOfflineBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.dialog_offline,null,false);
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 70);
        Window window = dialog.getWindow();
        if (window != null){
            window.setBackgroundDrawable(inset);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
        binding.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogActionCallback.okAction();
                dialog.dismiss();
            }
        });
    }

    public static void startProgressDialog(Context activity) {
        // Set up progress before call
        try {
            progressDoalog = new ProgressDialog(activity);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Loading....");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            progressDoalog.setCancelable(false);
            progressDoalog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void endProgressDialog() {
        try {
            if (progressDoalog != null)
                progressDoalog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
