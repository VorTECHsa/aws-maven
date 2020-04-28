/*
 * Copyright 2010-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.build.aws.maven;

import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AWSSessionCredentialsProvider;

public class SessionCredentialsProvider implements AWSSessionCredentialsProvider {
    @Override
    public AWSSessionCredentials getCredentials() {
        AWSSessionCredentials credentials = new AWSSessionCredentials() {
            @Override
            public String getSessionToken() {
                return System.getenv("AWS_SESSION_TOKEN");
            }

            @Override
            public String getAWSAccessKeyId() {
                return System.getenv("AWS_ACCESS_KEY_ID");
            }

            @Override
            public String getAWSSecretKey() {
                return System.getenv("AWS_SECRET_ACCESS_KEY");
            }
        };

        if (credentials.getAWSSecretKey() != null &&
            credentials.getAWSAccessKeyId() != null &&
            credentials.getSessionToken() != null) {
            return credentials;
        } else {
            return null;
        }
    }

    @Override
    public void refresh() {}
}
