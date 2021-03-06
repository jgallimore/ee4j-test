/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id$
 */

package com.sun.ts.tests.ejb30.bb.mdb.callback.method.ejbcreate;

import javax.ejb.EJBContext;
import javax.ejb.MessageDrivenContext;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import com.sun.ts.tests.ejb30.common.callback.MDBCallbackBeanBase;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

public class CallbackBean0 extends MDBCallbackBeanBase
    implements MessageListener {
  @Resource(name = "mdc")
  private MessageDrivenContext mdc;

  @Resource(name = "qFactory")
  private QueueConnectionFactory qFactory;

  @Resource(name = "replyQueue")
  private Queue replyQueue;

  public EJBContext getEJBContext() {
    return this.mdc;
  }

  // ================= callback methods ====================================
  // @PostConstruct
  // @PostConstruct not needed. ejbCreate is treated as PostConstruct method
  public void ejbCreate() {
    this.setPostConstructCalled(true);
    if (this.getEJBContext() != null && qFactory != null
        && replyQueue != null) {
      this.setInjectionDone(true);
    }
  }

  @PreDestroy
  protected void ejbRemove() throws RuntimeException {
    this.setPreDestroyCalled(true);
  }

}
