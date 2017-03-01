/**
 * Copyright 2016-2017 Symphony Integrations - Symphony LLC
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
 */

package org.symphonyoss.integration.agent.api.client;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.symphonyoss.integration.api.client.SymphonyApiClient;
import org.symphonyoss.integration.exception.MissingConfigurationException;
import org.symphonyoss.integration.model.yaml.IntegrationProperties;

/**
 * Low-level HTTP client to query Agent API.
 * Created by rsanchez on 22/02/17.
 */
@Component
public class AgentApiClient extends SymphonyApiClient {

  private static final String SERVICE_NAME = "Agent";

  private static final String REQUIRED_KEY = "agent.host";

  @Autowired
  private IntegrationProperties properties;

  public AgentApiClient() {
    super(SERVICE_NAME);
  }

  @Override
  protected String getBasePath() {
    String url = properties.getAgentUrl();

    if (StringUtils.isBlank(url)) {
      throw new MissingConfigurationException(SERVICE_NAME, REQUIRED_KEY);
    }

    return url;
  }

}
