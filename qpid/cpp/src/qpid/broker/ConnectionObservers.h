#ifndef QPID_BROKER_CONNECTIONOBSERVERS_H
#define QPID_BROKER_CONNECTIONOBSERVERS_H

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

#include "ConnectionObserver.h"

namespace qpid {
namespace broker {

/**
 * A collection of connection observers.
 * Calling a ConnectionObserver function will call that function on each observer.
 */
class ConnectionObservers : public ConnectionObserver {
  public:
    // functions for managing the collection of observers
    void add(boost::shared_ptr<ConnectionObserver> observer) {
        observers.push_back(observer);
    }

    void connection(Connection& c) {
        each(boost::bind(&ConnectionObserver::connection, _1, boost::ref(c)));
    }

    void opened(Connection& c) {
        each(boost::bind(&ConnectionObserver::opened, _1, boost::ref(c)));
    }

    void closed(Connection& c) {
        each(boost::bind(&ConnectionObserver::closed, _1, boost::ref(c)));
    }

    void forced(Connection& c, const std::string& text) {
        each(boost::bind(&ConnectionObserver::forced, _1, boost::ref(c), text));
    }

  private:
    typedef std::vector<boost::shared_ptr<ConnectionObserver> > Observers;
    Observers observers;

    template <class F> void each(F f) {
        std::for_each(observers.begin(), observers.end(), f);
    }
};

}} // namespace qpid::broker

#endif  /*!QPID_BROKER_CONNECTIONOBSERVERS_H*/
