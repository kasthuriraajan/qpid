/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.apache.qpid.server.security.access.plugins;

import org.apache.qpid.server.model.AbstractConfiguredObjectTypeFactory;
import org.apache.qpid.server.model.Broker;
import org.apache.qpid.server.model.ConfiguredObject;
import org.apache.qpid.server.plugin.AccessControlProviderFactory;
import org.apache.qpid.server.util.ResourceBundleLoader;

import java.util.Map;

public class ACLFileAccessControlProviderFactory extends AbstractConfiguredObjectTypeFactory<ACLFileAccessControlProvider> implements AccessControlProviderFactory<ACLFileAccessControlProvider>
{
    public static final String RESOURCE_BUNDLE = "org.apache.qpid.server.security.access.plugins.FileAccessControlProviderAttributeDescriptions";

    public ACLFileAccessControlProviderFactory()
    {
        super(ACLFileAccessControlProvider.class);
    }

    @Override
    public Map<String, String> getAttributeDescriptions()
    {
        return ResourceBundleLoader.getResources(RESOURCE_BUNDLE);
    }

    @Override
    public ACLFileAccessControlProvider createInstance(final Map<String, Object> attributes,
                                                       final ConfiguredObject<?>... parents)
    {
        return new ACLFileAccessControlProvider(getParent(Broker.class,parents), attributes);
    }

}
