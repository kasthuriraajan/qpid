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
package org.apache.qpid.agent.binding;

import java.util.HashMap;
import java.util.Map;

import org.apache.qpid.transport.codec.Encoder;

/**
 * Metadata for mapping a method argument to a QMF schema
 */
public class ParameterBinding
{
    private final String name;
    private final TypeBinding type;
    private final boolean in;
    private final boolean out;

    public ParameterBinding(String name, TypeBinding type, boolean in,
            boolean out)
    {
        this.name = name;
        this.type = type;
        this.in = in;
        this.out = out;
    }

    public String getName()
    {
        return name;
    }

    public TypeBinding getType()
    {
        return type;
    }

    public boolean isIn()
    {
        return in;
    }

    public boolean isOut()
    {
        return out;
    }

    void encode(Encoder enc)
    {
        Map map = new HashMap();
        map.put("name", name);
        map.put("type", type.getCode());
        map.put("dir", (in ? "I" : "") + (out ? "O" : ""));
        if (!type.isNative())
        {
            map.put("refClass", type.getRefClass());
            map.put("refPackage", type.getRefPackage());
        }
        enc.writeMap(map);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (in ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (out ? 1231 : 1237);
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParameterBinding other = (ParameterBinding) obj;
        if (in != other.in)
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (out != other.out)
            return false;
        if (type == null)
        {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
