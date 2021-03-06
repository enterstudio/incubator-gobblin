/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.gobblin.util;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import org.apache.commons.lang.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class JvmUtils {
  private static final Joiner JOINER = Joiner.on(" ").skipNulls();

  private static final PortUtils PORT_UTILS = new PortUtils();

  private JvmUtils() {
  }

  /**
   * Gets the input arguments passed to the JVM.
   * @return The input arguments.
   */
  public static String getJvmInputArguments() {
    RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
    List<String> arguments = runtimeMxBean.getInputArguments();
    return String.format("JVM Input Arguments: %s", JOINER.join(arguments));
  }

  /**
   * Formats the specified jvm arguments such that any tokens are replaced with concrete values;
   * @param jvmArguments
   * @return The formatted jvm arguments.
   */
  public static String formatJvmArguments(Optional<String> jvmArguments) {
    if (jvmArguments.isPresent()) {
      return PORT_UTILS.replacePortTokens(jvmArguments.get());
    }
    return StringUtils.EMPTY;
  }
}
