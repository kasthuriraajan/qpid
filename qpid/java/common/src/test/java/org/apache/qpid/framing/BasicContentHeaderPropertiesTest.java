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
package org.apache.qpid.framing;

import org.apache.mina.common.ByteBuffer;

import java.util.HashMap;

import junit.framework.TestCase;


public class BasicContentHeaderPropertiesTest extends TestCase {

    BasicContentHeaderProperties _testProperties;
    PropertyFieldTable _testTable;
    String _testString = "This is a test string";
    int _testint = 666;

    /**
     * Currently only test setting/getting String, int and boolean props
     */
    public BasicContentHeaderPropertiesTest() {
         _testProperties = new BasicContentHeaderProperties();
    }

    public void setUp()
    {
        HashMap _testMap = new HashMap(10);
        _testMap.put("TestString",_testString);
        _testMap.put("Testint",_testint);
        _testTable = new PropertyFieldTable();
        _testTable.putAll(_testMap);
        _testProperties = new BasicContentHeaderProperties();
        _testProperties.setHeaders(_testTable);
    }

    public void testGetPropertyListSize() {
        //needs a better test but at least we're exercising the code !
        int size = _testProperties.getPropertyListSize();
        assertEquals(83,size);
    }

    public void testGetSetPropertyFlags() {
        _testProperties.setPropertyFlags(99);
        assertEquals(99,_testProperties.getPropertyFlags());
    }

    public void testWritePropertyListPayload() {
        ByteBuffer buf = ByteBuffer.allocate(300);
        _testProperties.writePropertyListPayload(buf);
    }

    public void testPopulatePropertiesFromBuffer() throws Exception {
        ByteBuffer buf = ByteBuffer.allocate(300);
        _testProperties.populatePropertiesFromBuffer(buf,99,99);
    }

    public void testSetGetContentType() {
        String contentType = "contentType";
        _testProperties.setContentType(contentType);
        assertEquals(contentType,_testProperties.getContentType());
    }

    public void testSetGetEncoding() {
        String encoding = "encoding";
        _testProperties.setEncoding(encoding);
        assertEquals(encoding,_testProperties.getEncoding());
    }

    public void testSetGetHeaders() {
        _testProperties.setHeaders(_testTable);
        assertEquals(_testTable,_testProperties.getHeaders());
    }

    public void testSetGetDeliveryMode() {
        byte deliveryMode = 1;
        _testProperties.setDeliveryMode(deliveryMode);
        assertEquals(deliveryMode,_testProperties.getDeliveryMode());
    }

    public void testSetGetPriority() {
        byte priority = 1;
        _testProperties.setPriority(priority);
        assertEquals(priority,_testProperties.getPriority());
    }

    public void testSetGetCorrelationId() {
        String correlationId = "correlationId";
        _testProperties.setCorrelationId(correlationId);
        assertEquals(correlationId,_testProperties.getCorrelationId());
    }

    public void testSetGetReplyTo() {
        String replyTo = "replyTo";
        _testProperties.setReplyTo(replyTo);
        assertEquals(replyTo,_testProperties.getReplyTo());
    }

    public void testSetGetExpiration() {
        long expiration = 999999999;
        _testProperties.setExpiration(expiration);
        assertEquals(expiration,_testProperties.getExpiration());
    }

    public void testSetGetMessageId() {
        String messageId = "messageId";
        _testProperties.setMessageId(messageId);
        assertEquals(messageId,_testProperties.getMessageId());
    }

    public void testSetGetTimestamp() {
        long timestamp = 999999999;
        _testProperties.setTimestamp(timestamp);
        assertEquals(timestamp,_testProperties.getTimestamp());
    }

    public void testSetGetType() {
        String type = "type";
        _testProperties.setType(type);
        assertEquals(type,_testProperties.getType());
    }

    public void testSetGetUserId() {
        String userId = "userId";
        _testProperties.setUserId(userId);
        assertEquals(userId,_testProperties.getUserId());
    }

    public void testSetGetAppId() {
        String appId = "appId";
        _testProperties.setAppId(appId);
        assertEquals(appId,_testProperties.getAppId());
    }

    public void testSetGetClusterId() {
        String clusterId = "clusterId";
        _testProperties.setClusterId(clusterId);
        assertEquals(clusterId,_testProperties.getClusterId());
    }

}
