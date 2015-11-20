package com.github.vignesh_iopex.example;

import android.app.NotificationManager;
import android.location.Location;
import android.support.v4.app.NotificationCompat;

import com.github.vignesh_iopex.flanklocation.FlankTask;
import com.github.vignesh_iopex.flanklocation.annotations.Periodic;
import com.google.android.gms.common.ConnectionResult;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

@Periodic(interval = 3000, priority = PRIORITY_HIGH_ACCURACY, fastestInterval = 2000)
public class BgTask extends FlankTask {

  public BgTask() {
    super("BackgroundLocationTask");
  }

  @Override protected void onConnectionError(ConnectionResult connectionResult) {

  }

  @Override protected void onNextLocation(Location location) {
    String content = "No location";
    if (location != null)
      content = location.toString();
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
    mBuilder.setSmallIcon(R.drawable.notification_template_icon_bg)
        .setContentTitle("Location Update")
        .setContentText(content);
    NotificationManager notifyManager = (NotificationManager) getSystemService(
        NOTIFICATION_SERVICE);
    notifyManager.notify(1, mBuilder.build());
  }
}
