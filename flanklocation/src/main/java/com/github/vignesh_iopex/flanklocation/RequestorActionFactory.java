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

import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.location.LocationRequest;

interface RequestorActionFactory {
  RequestorAction forStart(Context context, @NonNull Class<? extends FlankTask> clsFlankTask);

  RequestorAction forStart(@NonNull Class<? extends FlankTask> clsFlankTask,
                           @Nullable PendingIntent callback);

  RequestorAction forStop(@NonNull PendingIntent pendingIntent);

  RequestorAction forStop(Context context, Class<? extends FlankTask> clsFlankTask);
}
