package org.apache.cordova;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.EditText;

public class CordovaDialogsHelper {
    private final Context context;
    private AlertDialog lastHandledDialog;

    public interface Result {
        void gotResult(boolean z, String str);
    }

    public CordovaDialogsHelper(Context context2) {
        this.context = context2;
    }

    public void showAlert(String str, final Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(str);
        builder.setTitle("Alert");
        builder.setCancelable(true);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                result.gotResult(true, (String) null);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                result.gotResult(false, (String) null);
            }
        });
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return true;
                }
                result.gotResult(true, (String) null);
                return false;
            }
        });
        this.lastHandledDialog = builder.show();
    }

    public void showConfirm(String str, final Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(str);
        builder.setTitle("Confirm");
        builder.setCancelable(true);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                result.gotResult(true, (String) null);
            }
        });
        builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                result.gotResult(false, (String) null);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                result.gotResult(false, (String) null);
            }
        });
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return true;
                }
                result.gotResult(false, (String) null);
                return false;
            }
        });
        this.lastHandledDialog = builder.show();
    }

    public void showPrompt(String str, String str2, final Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(str);
        final EditText editText = new EditText(this.context);
        if (str2 != null) {
            editText.setText(str2);
        }
        builder.setView(editText);
        builder.setCancelable(false);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                result.gotResult(true, editText.getText().toString());
            }
        });
        builder.setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                result.gotResult(false, (String) null);
            }
        });
        this.lastHandledDialog = builder.show();
    }

    public void destroyLastDialog() {
        AlertDialog alertDialog = this.lastHandledDialog;
        if (alertDialog != null) {
            alertDialog.cancel();
        }
    }
}
