/*
 _   _       _     _
| | | | __ _| |__ (_) ___ _   _ ___
| |_| |/ _` | '_ \| |/ __| | | / __|
|  _  | (_| | |_) | | (__| |_| \__ \
|_| |_|\__,_|_.__/|_|\___|\__,_|___/

 * This file is part of the Habicus Core Platform (https://github.com/Habicus/Habicus-Core).
 * Copyright (c) 2018 Habicus Core
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.habicus.core.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import org.springframework.http.HttpStatus;

public class ControllerUtil {

  /**
   * Easily create response objects to log back to client from a controller
   *
   * @param message
   * @param object
   * @param status
   * @return
   */
  public static ImmutableMap<String, String> createResponse(
      String message, String object, HttpStatus status) {
    return new Builder<String, String>()
        .put("message", message)
        .put("object", object)
        .put("status", String.valueOf(status))
        .build();
  }

  public static ImmutableMap<String, String> createResponse(String message, HttpStatus status) {
    return new Builder<String, String>()
        .put("message", message)
        .put("status", String.valueOf(status))
        .build();
  }
}
