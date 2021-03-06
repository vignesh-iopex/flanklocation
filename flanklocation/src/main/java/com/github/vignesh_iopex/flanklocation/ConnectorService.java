/*
 * Copyright 2015 Vignesh Periasami
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.vignesh_iopex.flanklocation;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;

public final class ConnectorService extends IntentService {
  private final ActionQueue actionQueue;
  private PlayServiceAdapter playServiceAdapter;

  public ConnectorService() {
    this("FlankLocationService", ActionQueue.DEFAULT_QUEUE);
  }

  @VisibleForTesting ConnectorService(String name, ActionQueue actionQueue) {
    super(name);
    this.actionQueue = actionQueue;
  }

  @Override protected void onHandleIntent(Intent intent) {
    if (playServiceAdapter == null)
      playServiceAdapter = new DefaultPlayServiceAdapter(this);
    if (!playServiceAdapter.isConnected())
      playServiceAdapter.connect();
    actionQueue.informAll(playServiceAdapter);
  }

  @Override public void onDestroy() {
    if (playServiceAdapter != null)
      playServiceAdapter.disconnect();
    super.onDestroy();
  }
}
