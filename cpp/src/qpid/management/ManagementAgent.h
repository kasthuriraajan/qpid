#ifndef _ManagementAgent_
#define _ManagementAgent_

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

#include "ManagementObject.h"

namespace qpid {
namespace management {

class ManagementAgent
{
  public:

    virtual ~ManagementAgent () {}

    typedef boost::shared_ptr<ManagementAgent> shared_ptr;

    static shared_ptr getAgent (void);

    virtual void RegisterClass (std::string packageName,
                                std::string className,
                                uint8_t*    md5Sum,
                                ManagementObject::writeSchemaCall_t schemaCall) = 0;
    virtual void addObject (ManagementObject::shared_ptr object,
                            uint32_t                     persistId   = 0,
                            uint32_t                     persistBank = 4) = 0;
};

}}
            
#endif  /*!_ManagementAgent_*/
