package org.totschnig.myexpenses;

import android.app.Activity;
import android.app.Dialog;

public class ProtectedActivity extends Activity {
  private Dialog pwDialog;
  @Override
  protected void onPause() {
    super.onPause();
    MyApplication app = MyApplication.getInstance();
    if (app.isLocked)
      pwDialog.dismiss();
    else {
      app.setmLastPause();
    }
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    MyApplication.getInstance().setmLastPause();
  }
  @Override
  protected void onResume() {
    super.onResume();
    MyApplication app = MyApplication.getInstance();
    if (app.shouldLock()) {
      if (pwDialog == null)
        pwDialog = Utils.passwordDialog(this);
      Utils.showPasswordDialog(this,pwDialog);
    }
  }
}
